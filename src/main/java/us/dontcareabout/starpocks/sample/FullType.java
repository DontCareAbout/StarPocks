package us.dontcareabout.starpocks.sample;

@SuppressWarnings("unused")
public class FullType {
	public byte public_byte;
	public Byte public_Byte;

	protected short protected_short;
	protected Short protected_Short;

	int package_int;
	Integer package_Integer;

	private long private_long;
	private Long private_Long;

	public static Object staticPublic;
	protected static Object staticProtected;
	static Object staticPackage;
	private static Object staticPrivate;

	public FullType(String[] args) {}
	public FullType(Object qoo, String... args) {}
	public FullType() {}
	protected FullType(short value) {}
	FullType(int value) {}
	private FullType(long value) {}
}
