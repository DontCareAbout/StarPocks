package us.dontcareabout.starpocks.mermaid;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import us.dontcareabout.starpocks.Relation;
import us.dontcareabout.starpocks.Relation.Type;

public class RelationWriterTest {
	@Test
	void test() {
		RelationWriter writer = new RelationWriter();
		Assertions.assertEquals(
			"java.util.AbstractList <|-- java.util.ArrayList",
			writer.write(
				new Relation(ArrayList.class, Type.extend, AbstractList.class)
			)
		);
		Assertions.assertEquals(
			"java.util.List <|.. java.util.ArrayList",
			writer.write(
				new Relation(ArrayList.class, Type.implement, List.class)
			)
		);
	}
}
