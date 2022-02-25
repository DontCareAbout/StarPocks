package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterWriter {
	public String write(Parameter p) {
		return p.getName() + ":" + p.getType().getSimpleName();
	}

	public String write(List<Parameter> param) {
		return param.stream().map(p -> write(p)).collect(Collectors.joining(", "));
	}
}