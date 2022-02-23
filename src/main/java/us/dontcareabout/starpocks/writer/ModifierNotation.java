package us.dontcareabout.starpocks.writer;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Modifier;

public class ModifierNotation {
	public static String visibility(int modifier) {
		if (isPublic(modifier)) { return "+"; }
		if (isProtected(modifier)) { return "#"; }
		if (isPrivate(modifier)) { return "-"; }
		return "~";
	}

	public static String static_(int modifier) {
		return Modifier.isStatic(modifier) ? "$" : "";
	}
}
