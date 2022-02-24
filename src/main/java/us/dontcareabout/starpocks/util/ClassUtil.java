package us.dontcareabout.starpocks.util;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassUtil {
	private static final Predicate<Member> publicFilter = f -> Modifier.isPublic(f.getModifiers());
	private static final Predicate<Member> protectedFilter = f -> Modifier.isProtected(f.getModifiers());
	private static final Predicate<Member> packageFilter = f -> isPackage(f.getModifiers());
	private static final Predicate<Member> privateFilter = f -> Modifier.isPrivate(f.getModifiers());

	public static boolean isPackage(int modifier) {
		return !isPublic(modifier) && !isProtected(modifier) && !isPrivate(modifier);
	}

	////////////////

	public static List<Field> publicField(Class<?> clazz, boolean isStatic) {
		return member(clazz.getDeclaredFields(), isStatic, publicFilter);
	}

	public static List<Field> protectedField(Class<?> clazz, boolean isStatic) {
		return member(clazz.getDeclaredFields(), isStatic, protectedFilter);
	}

	public static List<Field> packageField(Class<?> clazz, boolean isStatic) {
		return member(clazz.getDeclaredFields(), isStatic, packageFilter);
	}

	public static List<Field> privateField(Class<?> clazz, boolean isStatic) {
		return member(clazz.getDeclaredFields(), isStatic, privateFilter);
	}

	////////////////

	private static <M extends Member> List<M> member(M[] members, boolean isStatic, Predicate<Member> visibilityFilter) {
		return Stream.of(members)
			.filter(visibilityFilter)
			.filter(m -> isStatic == Modifier.isStatic(m.getModifiers()))
			.sorted(Comparator.comparing(Member::getName))
			.collect(Collectors.toList());
	}
}
