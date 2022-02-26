package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Constructor;

import us.dontcareabout.starpocks.util.MermaidUtil;

public class CtorWriter implements Writer<Constructor<?>> {
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
