package us.dontcareabout.starpocks.mermaid;

import us.dontcareabout.starpocks.Relation;
import us.dontcareabout.starpocks.writer.IRelationWriter;

public class RelationWriter implements IRelationWriter {
	@Override
	public String write(Relation r) {
		//因為 Mermaid 會把左方的 class 擺在上面
		//所以把 Relation.object（super class）擺在左邊
		StringBuilder result = new StringBuilder(r.object.getSimpleName());
		switch(r.type) {
		case extend: result.append(" <|-- "); break;
		case implement: result.append(" <|.. "); break;
		}
		result.append(r.subject.getSimpleName());
		return result.toString();
	}
}
