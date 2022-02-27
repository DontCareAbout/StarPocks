package us.dontcareabout.starpocks.mermaid;

import java.lang.reflect.Method;

import us.dontcareabout.starpocks.writer.IMethodWriter;

public class MethodWriter implements IMethodWriter {
	private ParameterWriter parameterWriter = new ParameterWriter();

	@Override
	public String write(Method method) {
		StringBuilder result = new StringBuilder();
		result.append(MermaidUtil.visibility(method));
		result.append(method.getName());
		result.append("(");
		result.append(parameterWriter.write(method.getParameters()));
		result.append(")");
		result.append(MermaidUtil.static_(method));
		result.append(
			method.getReturnType().equals(Void.TYPE) ? "" :
			" " + method.getReturnType().getSimpleName()
		);
		return result.toString();
	}
}
