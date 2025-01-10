package gg.jte.generated.ondemand;
import ch.heigvd.dai.model.entity.Player;
import java.util.LinkedList;
@SuppressWarnings("unchecked")
public final class JtehomeGenerated {
	public static final String JTE_NAME = "home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,23,23,23,25,25,25,26,26,26,27,27,27,28,28,28,30,30,42,42,42,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, LinkedList<Player> players) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"en\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>BDR x DAI</title>\n</head>\n<body>\n<h1>MMO GAME Project</h1>\n<p>Welcome to this first template of the MMO GAME Project application.</p>\n<p>Here is the current ranking for all Players registered.</p>\n<table>\n    <tr>\n        <th>Name</th>\n        <th>Experience</th>\n        <th>Balance</th>\n        <th>Champion</th>\n    </tr>\n    ");
		for (Player player : players) {
			jteOutput.writeContent("\n        <tr>\n            <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getName());
			jteOutput.writeContent("</td>\n            <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getExperience());
			jteOutput.writeContent("</td>\n            <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getBalance());
			jteOutput.writeContent("</td>\n            <td>");
			jteOutput.setContext("td", null);
			jteOutput.writeUserContent(player.getChampion());
			jteOutput.writeContent("</td>\n        </tr>\n    ");
		}
		jteOutput.writeContent("\n</table>\n\n<p>Click here to start the game</p>\n<button>Play</button>\n\n<p>Create a New Character</p>\n<form action=\"/register\" method=\"get\">\n    <button type=\"submit\">Register</button>\n</form>\n\n</body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		LinkedList<Player> players = (LinkedList<Player>)params.get("players");
		render(jteOutput, jteHtmlInterceptor, players);
	}
}
