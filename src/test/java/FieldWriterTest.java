import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.dontcareabout.starpocks.sample.FullType;
import us.dontcareabout.starpocks.util.ClassUtil;
import us.dontcareabout.starpocks.writer.FieldWriter;

public class FieldWriterTest {
	final Class<FullType> clazz = FullType.class;

	@Test
	void instance() {
		FieldWriter writer = new FieldWriter();
		Assertions.assertEquals(
			"+Byte public_Byte\n" +
			"+byte public_byte",
			writer.write(ClassUtil.publicField(clazz, false))
		);
		Assertions.assertEquals(
			"#Short protected_Short\n" +
			"#short protected_short",
			writer.write(ClassUtil.protectedField(clazz, false))
		);
		Assertions.assertEquals(
			"~Integer package_Integer\n" +
			"~int package_int",
			writer.write(ClassUtil.packageField(clazz, false))
		);
		Assertions.assertEquals(
			"-Long private_Long\n" +
			"-long private_long",
			writer.write(ClassUtil.privateField(clazz, false))
		);
	}

	@Test
	void static_() {
		FieldWriter writer = new FieldWriter();
		Assertions.assertEquals(
			"+Object staticPublic$",
			writer.write(ClassUtil.publicField(clazz, true))
		);
		Assertions.assertEquals(
			"#Object staticProtected$",
			writer.write(ClassUtil.protectedField(clazz, true))
		);
		Assertions.assertEquals(
			"~Object staticPackage$",
			writer.write(ClassUtil.packageField(clazz, true))
		);
		Assertions.assertEquals(
			"-Object staticPrivate$",
			writer.write(ClassUtil.privateField(clazz, true))
		);
	}
}
