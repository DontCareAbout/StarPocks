import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.dontcareabout.starpocks.sample.FullType;
import us.dontcareabout.starpocks.util.ClassUtil;
import us.dontcareabout.starpocks.writer.MethodWriter;

public class MethodWriterTest {
	final Class<FullType> clazz = FullType.class;

	@Test
	void instance() {
		MethodWriter writer = new MethodWriter();
		Assertions.assertEquals(
			"+aMethod(arg0:int) String\n" +
			"+aMethod(arg0:Integer, arg1:int[]) String\n" +
			"+publicMethod()\n" +
			"+publicMethod(arg0:String[])\n" +
			"+publicMethod(arg0:String, arg1:String)\n" +
			"+publicMethod(arg0:int, arg1:int, arg2:int)",
			writer.write(ClassUtil.publicMethod(clazz, false))
		);
		Assertions.assertEquals(
			"#protectedMethod()",
			writer.write(ClassUtil.protectedMethod(clazz, false))
		);
		Assertions.assertEquals(
			"~packageMethod()",
			writer.write(ClassUtil.packageMethod(clazz, false))
		);
		Assertions.assertEquals(
			"-privateMethod()",
			writer.write(ClassUtil.privateMethod(clazz, false))
		);
	}

	@Test
	void static_() {
		MethodWriter writer = new MethodWriter();
		Assertions.assertEquals(
			"+staticMethod(arg0:int)$ String",
			writer.write(ClassUtil.publicMethod(clazz, true))
		);
		Assertions.assertEquals(
			"#staticMethod(arg0:long)$ String",
			writer.write(ClassUtil.protectedMethod(clazz, true))
		);
		Assertions.assertEquals(
			"~staticMethod(arg0:float)$ String",
			writer.write(ClassUtil.packageMethod(clazz, true))
		);
		Assertions.assertEquals(
			"-staticMethod(arg0:double)$ String",
			writer.write(ClassUtil.privateMethod(clazz, true))
		);
	}
}
