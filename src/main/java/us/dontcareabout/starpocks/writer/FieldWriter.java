package us.dontcareabout.starpocks.writer;

import java.lang.reflect.Field;

public class FieldWriter implements IFieldWriter {
	@Override
	public String write(Field field) {
		StringBuilder result = new StringBuilder();
		result.append(ModifierNotation.visibility(field.getModifiers()));
		result.append(field.getType().getSimpleName() + " " + field.getName());
		result.append(ModifierNotation.static_(field.getModifiers()));
		return result.toString();
	}
}
