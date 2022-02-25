package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CtorWriter {
	private ParameterWriter parameterWriter = new ParameterWriter();

	public String write(Constructor<?> ctor) {
		StringBuilder result = new StringBuilder();
		result.append(ModifierNotation.visibility(ctor.getModifiers()));
		result.append(ctor.getDeclaringClass().getSimpleName());
		result.append("(");
		result.append(parameterWriter.write(Arrays.asList(ctor.getParameters())));
		result.append(")");
		return result.toString();
	}

	public String write(List<Constructor<?>> ctorList) {
		return ctorList.stream()
			.map(f -> write(f))
			.collect(Collectors.joining("\n", "", ""));
	}
}
