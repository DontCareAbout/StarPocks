package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Parameter;
import java.util.Arrays;

public interface IParameterWriter extends Writer<Parameter> {
	default String write(Parameter[] array) {
		return write(Arrays.asList(array));
	}
}
