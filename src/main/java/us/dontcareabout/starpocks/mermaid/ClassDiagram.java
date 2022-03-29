package us.dontcareabout.starpocks.mermaid;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import us.dontcareabout.starpocks.RelationAnalysis;

public class ClassDiagram {
	private ClassWriter classWriter = new ClassWriter();
	private RelationWriter relationWriter = new RelationWriter();

	public String write(Class<?>... clazz) {
		return write(Arrays.asList(clazz));
	}

	public String write(List<Class<?>> clazzList) {
		RelationAnalysis ra = new RelationAnalysis(clazzList);
		NameImprover ni = new NameImprover(ra.getAllClass());

		StringBuilder rawContent = new StringBuilder();
		rawContent.append(classWriter.write(ra.getAllClass()));
		rawContent.append("\n");
		rawContent.append(relationWriter.write(ra.getRelation()));

		//把原本 Class.getName() 替換成 NameImprover 提供的縮寫
		//懶得搞 StringBuilder.replace()，JVM 請原諒我(?)
		String safeContext = rawContent.toString();
		for (String name : ni.getResult().keySet()) {
			//要一直替換 instance 所以無法用 stream
			safeContext = safeContext.replaceAll(name, ni.getResult().get(name));
		}

		StringBuilder result = new StringBuilder("classDiagram\n");
		//把有 conflict 的 class 用註解的方式標示原始資訊
		Map<String, String> conflict = ni.getConflict();
		conflict.keySet().stream().forEach(
			name -> result.append("%% " + conflict.get(name) + " : " + name + "\n")
		);
		result.append(safeContext);

		return result.toString();
	}
}