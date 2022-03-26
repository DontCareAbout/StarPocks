package us.dontcareabout.starpocks.mermaid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 以 mermaid.js 允許的方式解決多個 class 的 {@link Class#getSimpleName()} 衝突問題。
 * <p>
 * 因為 mermaid.js 的 class name 只允許 underscore 這個符號（連中文全形都不吃），
 * 所以這裡的主要解決手段是前面加上 package 中某一節 component 與數個 underscore 來避免。
 * 例如：<code>a.b.c.d.Foo</code> 與 <code>x.y.z.Foo</code> 就會分別更名為
 * <code>d_Foo</code> 與 <code>z_Foo</code>
 */
public class Mediator {
	private Map<String, String> result = new HashMap<>();
	private int finalSolution = 1;

	/**
	 * @param classNameList {@link Class#getName()} 的 list
	 */
	//處理 List<String> 而非 List<Class> / List<Package> 的原因：test 比較好製造... XD
	public Mediator(List<String> classNameList) {
		//轉換成 ClassName 的 List，並排序
		List<ClassName> list = classNameList.stream()
			.map(n -> new ClassName(n))
			//變成 Stream<ClassName>
			.sorted().collect(Collectors.toList());

		//因為 size() 大的有比較多代換的可能性
		//所以由小的先開始，先搶先贏
		//XXX stream 還不熟細節機制，所以用傳統 for 以防萬一
		for (ClassName className : list) {
			process(className);
		}
	}

	public Map<String, String> getResult() {
		return result;
	}

	private void process(ClassName className) {
		if (className.isRootClass()) {
			//理論上只會有一個，所以也不用試了，直接塞
			result.put(className.name, className.newName(0));
			return;
		}

		for (int i = 0; i <= className.size() - ClassName.MIN_SIZE; i++) {
			if (check(className.name, className.newName(i))) { return; }
		}

		result.put(className.name, className.simpleName() + finalSolution);
		finalSolution++;
	}

	/**
	 * 判斷 candidate 是否依然會造成 conflict。
	 * 如果不會造成 conflict，則會添加到 {@link #result} 當中。
	 */
	private boolean check(String className, String candidate) {
		if (result.values().contains(candidate)) {
			return false;
		}

		result.put(className, candidate);
		return true;
	}

	private class ClassName implements Comparable<ClassName> {
		static final int MIN_SIZE = 2;
		final String name;
		final String[] components;

		ClassName(String className) {
			this.name = className;
			this.components = className.split("\\.");
		}

		int size() { return components.length; }

		boolean isRootClass() { return size() == 1; }

		String simpleName() {
			return components[components.length - 1];
		}

		String newName(int index) {
			//根本沒 package，回傳的就是 class name
			if (isRootClass()) { return simpleName(); }

			if (index > size() - MIN_SIZE) { throw new IllegalArgumentException(); }

			StringBuilder result = new StringBuilder(components[components.length - MIN_SIZE - index]);

			for (int i = 0; i <= index; i++) {
				result.append("_");
			}

			result.append(components[components.length - 1]);
			return result.toString();
		}

		@Override
		public int compareTo(ClassName o) {
			return Integer.compare(size(), o.size());
		}
	}
}