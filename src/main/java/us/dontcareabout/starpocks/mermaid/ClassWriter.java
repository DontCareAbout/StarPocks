package us.dontcareabout.starpocks.mermaid;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.dontcareabout.starpocks.util.ClassUtil;
import us.dontcareabout.starpocks.writer.IClassWriter;
import us.dontcareabout.starpocks.writer.ICtorWriter;
import us.dontcareabout.starpocks.writer.IFieldWriter;
import us.dontcareabout.starpocks.writer.IMethodWriter;
import us.dontcareabout.starpocks.writer.IStereotypeWriter;

public class ClassWriter implements IClassWriter {
	private IStereotypeWriter stereotypeWriter = new StereotypeWriter();
	private IFieldWriter fieldWriter = new FieldWriter();
	private ICtorWriter ctorWriter = new CtorWriter();
	private IMethodWriter methodWriter = new MethodWriter();

	@Override
	public String write(Class<?> clazz) {
		StringBuilder result = new StringBuilder("class ");
		result.append(clazz.getName());
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
			stereotypeWriter.write(clazz),
			fieldWriter.write(ClassUtil.publicField(clazz, true)),
			fieldWriter.write(ClassUtil.publicField(clazz, false)),
			ctorWriter.write(ClassUtil.publicCtor(clazz)),
			methodWriter.write(ClassUtil.publicMethod(clazz, false)),
			methodWriter.write(ClassUtil.publicMethod(clazz, true))
		).collect(Collectors.joining("\n"));
	}
}
