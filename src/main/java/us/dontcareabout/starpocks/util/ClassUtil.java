package us.dontcareabout.starpocks.util;

import static java.lang.reflect.Modifier.isPrivate;
import static java.lang.reflect.Modifier.isProtected;
import static java.lang.reflect.Modifier.isPublic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
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

	/**
	 * 排序順序：
	 * <ol>
	 * 	<li>{@linkplain Executable#getName() 名字}</li>
	 * 	<li>{@linkplain Executable#getParameterCount() 參數數量}</li>
	 * </ol>
	 */
	private static final Comparator<Executable> methodComparator = (o1, o2) -> {
		int nameCompare = o1.getName().compareTo(o2.getName());

		if (nameCompare != 0) { return nameCompare; }

		return Integer.compare(o1.getParameterCount(), o2.getParameterCount());
	};

	public static boolean isPackage(int modifier) {
		return !isPublic(modifier) && !isProtected(modifier) && !isPrivate(modifier);
	}

	////////////////

	public static List<Constructor<?>> publicCtor(Class<?> clazz) {
		return method(clazz.getDeclaredConstructors(), false, publicFilter);
	}

	public static List<Constructor<?>> protectedCtor(Class<?> clazz) {
		return method(clazz.getDeclaredConstructors(), false, protectedFilter);
	}

	public static List<Constructor<?>> packageCtor(Class<?> clazz) {
		return method(clazz.getDeclaredConstructors(), false, packageFilter);
	}

	public static List<Constructor<?>> privateCtor(Class<?> clazz) {
		return method(clazz.getDeclaredConstructors(), false, privateFilter);
	}

	public static List<Method> publicMethod(Class<?> clazz, boolean isStatic) {
		return method(clazz.getDeclaredMethods(), false, publicFilter);
	}

	public static List<Method> protectedMethod(Class<?> clazz, boolean isStatic) {
		return method(clazz.getDeclaredMethods(), false, protectedFilter);
	}

	public static List<Method> packageMethod(Class<?> clazz, boolean isStatic) {
		return method(clazz.getDeclaredMethods(), false, packageFilter);
	}

	public static List<Method> privateMethod(Class<?> clazz, boolean isStatic) {
		return method(clazz.getDeclaredMethods(), false, privateFilter);
	}

	//method / ctor 有 overload，所以無法像 field 一樣單純用名稱排序
	private static <M extends Executable> List<M> method(M[] members, boolean isStatic, Predicate<Member> visibilityFilter) {
		return Stream.of(members)
			.filter(visibilityFilter)
			.filter(m -> isStatic == Modifier.isStatic(m.getModifiers()))
			.sorted(methodComparator)
			.collect(Collectors.toList());
	}

	////////////////

	public static List<Field> publicField(Class<?> clazz, boolean isStatic) {
		return field(clazz.getDeclaredFields(), isStatic, publicFilter);
	}

	public static List<Field> protectedField(Class<?> clazz, boolean isStatic) {
		return field(clazz.getDeclaredFields(), isStatic, protectedFilter);
	}

	public static List<Field> packageField(Class<?> clazz, boolean isStatic) {
		return field(clazz.getDeclaredFields(), isStatic, packageFilter);
	}

	public static List<Field> privateField(Class<?> clazz, boolean isStatic) {
		return field(clazz.getDeclaredFields(), isStatic, privateFilter);
	}

	//field 沒有 overload 的狀況，所以用名字排序即可
	private static List<Field> field(Field[] members, boolean isStatic, Predicate<Member> visibilityFilter) {
		return Stream.of(members)
			.filter(visibilityFilter)
			.filter(m -> isStatic == Modifier.isStatic(m.getModifiers()))
			.sorted(Comparator.comparing(Field::getName))
			.collect(Collectors.toList());
	}
}
