package us.dontcareabout.starpocks.mermaid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 提供 {@link Class#getName()} 的縮寫轉換。
 * 預設使用 {@link Class#getSimpleName()}，
 * 若多個 class 名稱相同產生 conflict，則由 {@link Mediator} 處理。
 */
public class NameImprover {
	private Map<String, String> mapping;
	private Map<String, String> conflict = new HashMap<>();

	public NameImprover(Class<?>... clazz) {
		this(Stream.of(clazz).collect(Collectors.toList()));
	}

	public NameImprover(List<Class<?>> list) {
		this.mapping = list.stream().collect(
			Collectors.toMap(c -> c.getName(), c -> c.getSimpleName())
		);

		buildConflict().values().stream()
			//撞同一個名字的 list 各自獨立處理
			//其實這樣會有個炸點：假設 Foo 有撞名
			//有一個 a.Foo 修正完之後成為 a_Foo
			//然後跟本來就叫做 a_Foo 的 class 又撞名了...
			//不過機率實在太小，所以不考慮...... [逃]
			.forEach(d -> mediate(d));
	}

	/**
	 * 縮寫對應表。
	 * key 值為 {@link Class#getName()}，
	 * value 值為 unique 的縮寫（預設為 {@link Class#getSimpleName()}。
	 */
	public Map<String, String> getResult() {
		return mapping;
	}

	/**
	 * 造成 conflict 的對應表。
	 * key 值為 {@link Class#getName()}，
	 * value 值為 unique 的縮寫（預設為 {@link Class#getSimpleName()}。
	 */
	public Map<String, String> getConflict() {
		return conflict;
	}

	private Map<String, List<String>> buildConflict() {
		//先建立完整的 name -> fullName[] 對照
		Map<String, List<String>> complete = new HashMap<>();

		for (String fullName : mapping.keySet()) {
			String name = mapping.get(fullName);
			List<String> fullNameList = complete.get(name);

			if (fullNameList == null) {
				fullNameList = new ArrayList<>();
				complete.put(name, fullNameList);
			}

			fullNameList.add(fullName);
		}

		//過濾掉 name 只有對應一個 fullName，才是真正 duplicate 的部份
		Map<String, List<String>> result = new HashMap<>();
		complete.keySet().stream()
			.filter(name ->  complete.get(name).size() > 1)
			.collect(Collectors.toSet())
			//現在是 Set
			.forEach(name -> result.put(name, complete.get(name)));

		return result;
	}

	private void mediate(List<String> list) {
		Map<String, String> result = new Mediator(list).getResult();
		list.stream().forEach(
			name -> {
				String newName = result.get(name);
				mapping.put(name, newName);
				conflict.put(name, newName);
			}
		);
	}
}