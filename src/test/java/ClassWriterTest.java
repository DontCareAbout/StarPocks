import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.dontcareabout.starpocks.sample.FullType;
import us.dontcareabout.starpocks.writer.ClassWriter;

public class ClassWriterTest {
	@Test
	void test() {
		ClassWriter writer = new ClassWriter();

		Assertions.assertEquals(
			"class FullType {\n"
			+ "\t+Object staticPublic$\n"
			+ "\t+Byte public_Byte\n"
			+ "\t+byte public_byte\n"
			+ "\t+FullType()\n"
			+ "\t+FullType(arg0:String[])\n"
			+ "\t+FullType(arg0:Object, arg1:String[])\n"
			+ "\t+aMethod(arg0:int) String\n"
			+ "\t+aMethod(arg0:Integer, arg1:int[]) String\n"
			+ "\t+publicMethod()\n"
			+ "\t+publicMethod(arg0:String[])\n"
			+ "\t+publicMethod(arg0:String, arg1:String)\n"
			+ "\t+publicMethod(arg0:int, arg1:int, arg2:int)\n"
			+ "\t+staticMethod(arg0:int)$ String\n"
			+ "}",
			writer.write(FullType.class)
		);
	}
}
