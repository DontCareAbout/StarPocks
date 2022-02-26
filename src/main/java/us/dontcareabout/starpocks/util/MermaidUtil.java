package us.dontcareabout.starpocks.util;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class MermaidUtil {
	public static String visibility(Member member) {
		int modifier = member.getModifiers();
		if (isPublic(modifier)) { return "+"; }
		if (isProtected(modifier)) { return "#"; }
		if (isPrivate(modifier)) { return "-"; }
		return "~";
	}

	public static String static_(Member member) {
		int modifier = member.getModifiers();
		return Modifier.isStatic(modifier) ? "$" : "";
	}
}
