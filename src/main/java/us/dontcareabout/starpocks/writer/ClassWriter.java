package us.dontcareabout.starpocks.writer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.dontcareabout.starpocks.util.ClassUtil;

public class ClassWriter implements IClassWriter {
	private IFieldWriter fieldWriter = new FieldWriter();
	private ICtorWriter ctorWriter = new CtorWriter();
	private IMethodWriter methodWriter = new MethodWriter();

	@Override
	public String write(Class<?> clazz) {
		StringBuilder result = new StringBuilder("class ");
		result.append(clazz.getSimpleName());
		result.append(" {\n");
		result.append(
			Stream.of(member(clazz).split("\n"))
				.filter(str -> !str.isEmpty())
				.collect(Collectors.joining("\n\t", "\t", ""))
		);
		result.append("\n}");
		return result.toString();
	}

	protected String member(Class<?> clazz) {
		return Stream.of(
			fieldWriter.write(ClassUtil.publicField(clazz, true)),
			fieldWriter.write(ClassUtil.publicField(clazz, false)),
			ctorWriter.write(ClassUtil.publicCtor(clazz)),
			methodWriter.write(ClassUtil.publicMethod(clazz, false)),
			methodWriter.write(ClassUtil.publicMethod(clazz, true))
		).collect(Collectors.joining("\n"));
	}
}
