package us.dontcareabout.starpocks;

import java.util.Objects;

/**
 * 記錄兩個 {@link Class} 之間的關係。
 * <p>
 * 「A 繼承 B (A extends B)」，
 * A 是主詞（{@link #subject}）、B 是副詞（{@link #object}）
 */
public class Relation {
	public final Class<?> subject;
	public final Type type;
	public final Class<?> object;

	public Relation(Class<?> subject, Type type, Class<?> object) {
		this.subject = subject;
		this.type = type;
		this.object = object;
	}

	@Override
	public int hashCode() {
		return Objects.hash(object, subject, type);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) { return true; }
		if(!(obj instanceof Relation)) { return false; }
		Relation other = (Relation)obj;
		return Objects.equals(object, other.object) && Objects.equals(subject, other.subject) && type == other.type;
	}

	public enum Type {
		extend,
		implement,
		;
	}
}
