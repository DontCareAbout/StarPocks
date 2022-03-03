package us.dontcareabout.starpocks.mermaid;

import java.lang.reflect.Modifier;

import us.dontcareabout.starpocks.writer.IStereotypeWriter;

public class StereotypeWriter implements IStereotypeWriter {
	@Override
	public String write(Class<?> clazz) {
		int modifier = clazz.getModifiers();
		//isInterface 必須在 isAbstract 之前，因為 interface 一定符合 isAbstract
		if (Modifier.isInterface(modifier)) {
			return "<<interface>>\n";
		}
		if (Modifier.isAbstract(modifier)) {
			return "<<abstract>>\n";
		}
		return "";
	}
}
