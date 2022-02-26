package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Constructor;

public class CtorWriter implements Writer<Constructor<?>> {
	private ParameterWriter parameterWriter = new ParameterWriter();

	@Override
	public String write(Constructor<?> ctor) {
		StringBuilder result = new StringBuilder();
		result.append(ModifierNotation.visibility(ctor.getModifiers()));
		result.append(ctor.getDeclaringClass().getSimpleName());
		result.append("(");
		result.append(parameterWriter.write(ctor.getParameters()));
		result.append(")");
		return result.toString();
	}
}
