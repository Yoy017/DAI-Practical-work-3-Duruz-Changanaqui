package gg.jte.generated.ondemand;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JteregisterGenerated {
	public static final String JTE_NAME = "register.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,19,19,19,20,20,20,21,21,26,26,27,27,27,27,27,27,27,27,27,27,27,27,28,28,36,36,36,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, List<String> classes, String errorMessage) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n    <title>Create New Character</title>\r\n</head>\r\n<body>\r\n<h1>Create a New Character</h1>\r\n<p>Fill out the form below to create a new character for the game.</p>\r\n\r\n<form action=\"/register\" method=\"post\">\r\n    <label for=\"character-name\">Character Name:</label>\r\n    <input type=\"text\" id=\"character-name\" name=\"name\" required>\r\n    ");
		if (errorMessage != null) {
			jteOutput.writeContent("\r\n        <span style=\"color: red;\">");
			jteOutput.setContext("span", null);
			jteOutput.writeUserContent(errorMessage);
			jteOutput.writeContent("</span>\r\n    ");
		}
		jteOutput.writeContent("\r\n    <br><br>\r\n\r\n    <label for=\"character-class\">Choose a Profession:</label>\r\n    <select id=\"character-class\" name=\"profession\" required>\r\n        ");
		for (String className : classes) {
			jteOutput.writeContent("\r\n        <option");
			var __jte_html_attribute_0 = className;
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" value=\"");
				jteOutput.setContext("option", "value");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("option", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">");
			jteOutput.setContext("option", null);
			jteOutput.writeUserContent(className);
			jteOutput.writeContent("</option>\r\n        ");
		}
		jteOutput.writeContent("\r\n    </select>\r\n    <br><br>\r\n\r\n    <button type=\"submit\">Create Character</button>\r\n</form>\r\n\r\n</body>\r\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		List<String> classes = (List<String>)params.get("classes");
		String errorMessage = (String)params.get("errorMessage");
		render(jteOutput, jteHtmlInterceptor, classes, errorMessage);
	}
}
