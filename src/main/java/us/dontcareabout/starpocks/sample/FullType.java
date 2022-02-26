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

	public void publicMethod(String a, String b) {}
	public void publicMethod(String... a) {}
	public void publicMethod() {}
	public void publicMethod(int a, int b, int c) {}
	//用「a」開頭，以確定 constructor 會在最前面
	public String aMethod(int index) { return null; }
	public String aMethod(Integer index, int[] array) { return ""; }
	protected void protectedMethod() {}
	void packageMethod() {}
	private void privateMethod() {}


	public static String staticMethod(int value) { return ""; }
	protected static String staticMethod(long value) { return ""; }
	static String staticMethod(float value) { return ""; }
	private static String staticMethod(double value) { return ""; }
}
