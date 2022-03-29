package us.dontcareabout.starpocks.mermaid;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MediatorTest {
	private static final String foo1 = "a.b.c.d.Foo";
	private static final String foo2_1 = "x.y.z.Foo";
	private static final String foo2_2 = "x.y.f.Foo";
	private static final String foo2_3 = "x.t.f.Foo";
	private static final String foo2_4 = "w.t.f.Foo";
	private static final String foo2_final = "w.t.z.Foo";
	private static final String foo3_1 = "us.dontcareabout.starpocks.Foo";
	private static final String foo3_2 = "dontcareabout.starpocks.Foo";
	private static final String foo3_3 = "starpocks.Foo";
	private static final String foo3_4 = "Foo";

	@Test
	void finalSolution() {
		Mediator mediator = new Mediator(
			Arrays.asList(foo2_1, foo2_2, foo2_3, foo2_4, foo2_final)
		);
		Assertions.assertEquals(
			"z_Foo",
			mediator.getResult().get(foo2_1)
		);
		Assertions.assertEquals(
			"f_Foo",
			mediator.getResult().get(foo2_2)
		);
		Assertions.assertEquals(
			"t__Foo",
			mediator.getResult().get(foo2_3)
		);
		Assertions.assertEquals(
			"w___Foo",
			mediator.getResult().get(foo2_4)
		);
		Assertions.assertEquals(
			"Foo1",
			mediator.getResult().get(foo2_final)
		);
	}

	@Test
	void shortFirst() {
		Mediator mediator = new Mediator(
			Arrays.asList(foo3_1, foo3_2, foo3_3, foo3_4)
		);
		Assertions.assertEquals(
			"us___Foo",
			mediator.getResult().get(foo3_1)
		);
		Assertions.assertEquals(
			"dontcareabout__Foo",
			mediator.getResult().get(foo3_2)
		);
		Assertions.assertEquals(
			"starpocks_Foo",
			mediator.getResult().get(foo3_3)
		);
		Assertions.assertEquals(
			"Foo",
			mediator.getResult().get(foo3_4)
		);
	}

	@Test
	void basic() {
		Mediator mediator = new Mediator(
			Arrays.asList(foo1, foo2_1, foo3_1)
		);
		Assertions.assertEquals(
			"d_Foo",
			mediator.getResult().get(foo1)
		);
		Assertions.assertEquals(
			"z_Foo",
			mediator.getResult().get(foo2_1)
		);
		Assertions.assertEquals(
			"starpocks_Foo",
			mediator.getResult().get(foo3_1)
		);
	}
}
