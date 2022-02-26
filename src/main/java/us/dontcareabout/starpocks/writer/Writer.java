package us.dontcareabout.starpocks.writer;

import java.util.List;
import java.util.stream.Collectors;

public interface Writer<T> {
	String write(T t);

	default String write(List<T> list) {
		return list.stream().map(t -> write(t))
			.collect(Collectors.joining("\n"));
	}
}
