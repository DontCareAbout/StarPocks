package us.dontcareabout.starpocks.mermaid;

import java.lang.reflect.Field;

import us.dontcareabout.starpocks.writer.IFieldWriter;

public class FieldWriter implements IFieldWriter {
	@Override
	public String write(Field field) {
		StringBuilder result = new StringBuilder();
		result.append(MermaidUtil.visibility(field));
		result.append(field.getType().getSimpleName() + " " + field.getName());
		result.append(MermaidUtil.static_(field));
		return result.toString();
	}
}
