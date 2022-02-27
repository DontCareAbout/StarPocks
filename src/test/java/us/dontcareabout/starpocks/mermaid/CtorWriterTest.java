package us.dontcareabout.starpocks.mermaid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.dontcareabout.starpocks.sample.FullType;
import us.dontcareabout.starpocks.util.ClassUtil;

public class CtorWriterTest {
	final Class<FullType> clazz = FullType.class;

	@Test
	void ctor() {
		CtorWriter writer = new CtorWriter();
		Assertions.assertEquals(
			"+FullType()\n" +
			"+FullType(arg0:String[])\n" +
			"+FullType(arg0:Object, arg1:String[])",
			writer.write(ClassUtil.publicCtor(clazz))
		);
		Assertions.assertEquals(
			"#FullType(arg0:short)",
			writer.write(ClassUtil.protectedCtor(clazz))
		);
		Assertions.assertEquals(
			"~FullType(arg0:int)",
			writer.write(ClassUtil.packageCtor(clazz))
		);
		Assertions.assertEquals(
			"-FullType(arg0:long)",
			writer.write(ClassUtil.privateCtor(clazz))
		);
	}
}
