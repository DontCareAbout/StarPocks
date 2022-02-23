package us.dontcareabout.starpocks.util;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassUtil {
	public static boolean isPackage(int modifier) {
		return !isPublic(modifier) && !isProtected(modifier) && !isPrivate(modifier);
	}

	public static List<Field> publicField(Class<?> clazz, boolean isStatic) {
		return field(clazz, isStatic, f -> Modifier.isPublic(f.getModifiers()));
	}

	public static List<Field> protectedField(Class<?> clazz, boolean isStatic) {
		return field(clazz, isStatic, f -> Modifier.isProtected(f.getModifiers()));
	}

	public static List<Field> packageField(Class<?> clazz, boolean isStatic) {
		return field(clazz, isStatic, f -> isPackage(f.getModifiers()));
	}

	public static List<Field> privateField(Class<?> clazz, boolean isStatic) {
		return field(clazz, isStatic, f -> Modifier.isPrivate(f.getModifiers()));
	}

	private static List<Field> field(Class<?> clazz, boolean isStatic, Predicate<Field> p) {
		return Stream.of(clazz.getDeclaredFields())
			.filter(p)
			.filter(f -> isStatic == Modifier.isStatic(f.getModifiers()))
			.sorted(Comparator.comparing(Field::getName))
			.collect(Collectors.toList());
	}
}
