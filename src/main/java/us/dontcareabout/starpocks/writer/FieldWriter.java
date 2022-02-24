package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class FieldWriter {
	public String write(Field field) {
		StringBuilder result = new StringBuilder();
		result.append(ModifierNotation.visibility(field.getModifiers()));
		result.append(field.getType().getSimpleName() + " " + field.getName());
		result.append(ModifierNotation.static_(field.getModifiers()));
		return result.toString();
	}

	public String write(List<Field> fieldList) {
		return fieldList.stream()
			.map(f -> write(f))
			.collect(Collectors.joining("\n", "", ""));
	}
}
