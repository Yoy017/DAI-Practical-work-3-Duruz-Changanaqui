package gg.jte.generated.ondemand;
import ch.heigvd.dai.model.entity.Player;
import java.util.LinkedList;
@SuppressWarnings("unchecked")
public final class JtehomeGenerated {
	public static final String JTE_NAME = "home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,10,10,34,34,36,36,36,36,36,36,36,37,37,37,38,38,38,39,39,39,41,43,43,43,43,43,43,43,43,43,48,48,58,61,61,61,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, LinkedList<Player> players) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    ");
		jteOutput.writeContent("\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n    <title>MMO GAME Project</title>\n</head>\n<body>\n<div class=\"container mt-5\">\n    <div class=\"text-center\">\n        <h1 class=\"display-4\">MMO GAME Project</h1>\n        <p class=\"lead\">Welcome to this first template of the MMO GAME Project application.</p>\n        <p>Here is the current ranking for all Players registered.</p>\n    </div>\n\n    <div class=\"table-responsive\">\n        <table class=\"table table-striped table-hover\">\n            <thead class=\"table-dark\">\n            <tr>\n                <th scope=\"col\">Name</th>\n                <th scope=\"col\">Experience</th>\n                <th scope=\"col\">Balance</th>\n                <th scope=\"col\">Champion</th>\n                <th scope=\"col\">Actions</th>\n            </tr>\n            </thead>\n            <tbody>\n            ");
		for (Player player : players) {
			jteOutput.writeContent("\n                <tr>\n                    <td><a href=\"/armory?player=");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(player.getName());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\" class=\"text-decoration-none\">");
			jteOutput.setContext("a", null);
			jteOutput.writeUserContent(player.getName());
			jteOutput.writeContent("</a></td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getExperience());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getBalance());
			jteOutput.writeContent("</td>\n                    <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getChampion());
			jteOutput.writeContent("</td>\n                    <td>\n                        ");
			jteOutput.writeContent("\n                        <form action=\"/delete-player\" method=\"post\" style=\"display:inline;\">\n                            <input type=\"hidden\" name=\"playerName\"");
			var __jte_html_attribute_0 = player.getName();
			if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
				jteOutput.writeContent(" value=\"");
				jteOutput.setContext("input", "value");
				jteOutput.writeUserContent(__jte_html_attribute_0);
				jteOutput.setContext("input", null);
				jteOutput.writeContent("\"");
			}
			jteOutput.writeContent(">\n                            <button type=\"submit\" class=\"btn btn-danger btn-sm\">Delete</button>\n                        </form>\n                    </td>\n                </tr>\n            ");
		}
		jteOutput.writeContent("\n            </tbody>\n        </table>\n    </div>\n    <p>Create a New Character</p>\n    <form action=\"/register\" method=\"get\">\n        <button type=\"submit\">Register</button>\n    </form>\n</div>\n\n");
		jteOutput.writeContent("\n<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-whK5zvMVD5FnOFtmZKtbRjBT07C0UjGvd3ycmTSHxnuGk5EmRXf3RZpLNM9z3KeJ\" crossorigin=\"anonymous\"></script>\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		LinkedList<Player> players = (LinkedList<Player>)params.get("players");
		render(jteOutput, jteHtmlInterceptor, players);
	}
}
