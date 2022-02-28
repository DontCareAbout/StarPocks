package us.dontcareabout.starpocks.mermaid;

import java.util.Arrays;
import java.util.List;

import us.dontcareabout.starpocks.RelationAnalysis;

public class ClassDiagram {
	private ClassWriter classWriter = new ClassWriter();
	private RelationWriter relationWriter = new RelationWriter();

	public String write(Class<?>... clazz) {
		return write(Arrays.asList(clazz));
	}

	public String write(List<Class<?>> clazzList) {
		RelationAnalysis ra = new RelationAnalysis(clazzList);

		StringBuilder result = new StringBuilder("classDiagram\n");
		result.append(classWriter.write(ra.getAllClass()));
		result.append("\n");
		result.append(relationWriter.write(ra.getRelation()));
		return result.toString();
	}
}