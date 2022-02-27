package us.dontcareabout.starpocks.mermaid;

import java.lang.reflect.Constructor;

import us.dontcareabout.starpocks.writer.ICtorWriter;

public class CtorWriter implements ICtorWriter {
	private ParameterWriter parameterWriter = new ParameterWriter();

	@Override
	public String write(Constructor<?> ctor) {
		StringBuilder result = new StringBuilder();
		result.append(MermaidUtil.visibility(ctor));
		result.append(ctor.getDeclaringClass().getSimpleName());
		result.append("(");
		result.append(parameterWriter.write(ctor.getParameters()));
		result.append(")");
		return result.toString();
	}
}
