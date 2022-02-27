package us.dontcareabout.starpocks;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import us.dontcareabout.starpocks.Relation.Type;

public class RelationAnalysis {
	private Set<Class<?>> allClass = new HashSet<>();
	private Set<Relation> relation = new HashSet<>();

	public RelationAnalysis(Class<?> clazz) {
		analyse(clazz);
	}

	public RelationAnalysis(List<Class<?>> classList) {
		for (Class<?> clazz : classList) {
			analyse(clazz);
		}
	}

	public List<Class<?>> getAllClass() {
		return allClass.stream().collect(Collectors.toList());
	}

	public List<Relation> getRelation() {
		return relation.stream().collect(Collectors.toList());
	}

	private void analyse(Class<?> clazz) {
		if (isNullOrObject(clazz)) { return; }	//理論上不用，不過還是預防擋一下 XD
		if (allClass.contains(clazz)) { return; }

		allClass.add(clazz);

		Class<?> parent = clazz.getSuperclass();
		//interface 的 super class 會是 null

		if (!isNullOrObject(parent)) {
			relation.add(new Relation(clazz, Type.extend, parent));
			analyse(parent);
		}

		for (Class<?> face : clazz.getInterfaces()) {
			relation.add(
				new Relation(
					clazz,
					clazz.isInterface() ? Type.extend : Type.implement,
					face
				)
			);
			analyse(face);
		}
	}

	private static boolean isNullOrObject(Class<?> clazz) {
		return clazz == null || clazz == Object.class;
	}
}
