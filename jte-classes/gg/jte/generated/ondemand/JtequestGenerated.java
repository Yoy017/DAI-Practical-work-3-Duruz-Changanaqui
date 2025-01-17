package gg.jte.generated.ondemand;
import ch.heigvd.dai.model.entity.Quest;
import ch.heigvd.dai.model.entity.Player;
import java.util.List;
@SuppressWarnings("unchecked")
public final class JtequestGenerated {
	public static final String JTE_NAME = "quest.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,4,15,15,15,15,19,19,19,21,25,25,27,27,28,28,32,32,32,33,33,33,35,35,37,37,37,37,37,37,37,37,37,40,40,42,42,44,44,44,45,45,46,46,46,47,47,49,49,50,50,54,58,58,60,60,61,61,65,65,65,66,66,66,68,68,68,72,72,72,72,72,72,72,72,72,76,76,76,76,76,76,76,76,76,81,81,82,82,86,90,90,92,92,93,93,97,97,97,98,98,98,101,101,101,101,101,101,101,101,101,105,105,105,107,107,108,108,118,118,118,4,5,6,7,7,7,7};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Player player, List<Quest> followedQuests, List<Quest> availableQuests, List<Quest> completedQuests) {
		jteOutput.writeContent("\r\n<!DOCTYPE html>\r\n<html lang=\"en\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(player.getName());
		jteOutput.writeContent("'s Quests Journal</title>\r\n</head>\r\n<body class=\"bg-light\">\r\n<div class=\"container my-5\">\r\n    <h1 class=\"text-center mb-4\">");
		jteOutput.setContext("h1", null);
		jteOutput.writeUserContent(player.getName());
		jteOutput.writeContent("'s Quests Journal</h1>\r\n    <div class=\"row\">\r\n        ");
		jteOutput.writeContent("\r\n        <div class=\"col-md-4\">\r\n            <h2>Available Quests</h2>\r\n            <ul class=\"list-group\">\r\n                ");
		if (availableQuests.isEmpty()) {
			jteOutput.writeContent("\r\n                    <li class=\"list-group-item\">No quests available for now.</li>\r\n                ");
		} else {
			jteOutput.writeContent("\r\n                    ");
			for (Quest quest : availableQuests) {
				jteOutput.writeContent("\r\n                        <li class=\"list-group-item\">\r\n                            <div class=\"d-flex justify-content-between align-items-center\">\r\n                                <div>\r\n                                    <h5>");
				jteOutput.setContext("h5", null);
				jteOutput.writeUserContent(quest.getName());
				jteOutput.writeContent("</h5>\r\n                                    <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(quest.getDescription());
				jteOutput.writeContent("</p>\r\n                                </div>\r\n                                ");
				if (player.canAcceptQuest(quest)) {
					jteOutput.writeContent("\r\n                                    <form action=\"/quest/accept\" method=\"post\" class=\"ms-3\">\r\n                                        <input type=\"hidden\" name=\"questName\"");
					var __jte_html_attribute_0 = quest.getName();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" value=\"");
						jteOutput.setContext("input", "value");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("input", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">\r\n                                        <button type=\"submit\" class=\"btn btn-success btn-sm\">Accept</button>\r\n                                    </form>\r\n                                ");
				} else {
					jteOutput.writeContent("\r\n                                    <button class=\"btn btn-secondary btn-sm\" disabled>Unavailable</button>\r\n                                ");
				}
				jteOutput.writeContent("\r\n                            </div>\r\n                            <small class=\"text-muted\">Requires level ");
				jteOutput.setContext("small", null);
				jteOutput.writeUserContent(quest.getRequiredLevel());
				jteOutput.writeContent("</small>\r\n                            ");
				if (quest.getRequiredQuest() != null) {
					jteOutput.writeContent("\r\n                                <small class=\"text-danger d-block\">Dependency: ");
					jteOutput.setContext("small", null);
					jteOutput.writeUserContent(quest.getRequiredQuest().getName());
					jteOutput.writeContent("</small>\r\n                            ");
				}
				jteOutput.writeContent("\r\n                        </li>\r\n                    ");
			}
			jteOutput.writeContent("\r\n                ");
		}
		jteOutput.writeContent("\r\n            </ul>\r\n        </div>\r\n\r\n        ");
		jteOutput.writeContent("\r\n        <div class=\"col-md-4\">\r\n            <h2>Followed Quests</h2>\r\n            <ul class=\"list-group\">\r\n                ");
		if (followedQuests.isEmpty()) {
			jteOutput.writeContent("\r\n                    <li class=\"list-group-item\">No quests are currently being followed.</li>\r\n                ");
		} else {
			jteOutput.writeContent("\r\n                    ");
			for (Quest quest : followedQuests) {
				jteOutput.writeContent("\r\n                        <li class=\"list-group-item\">\r\n                            <div class=\"d-flex justify-content-between align-items-center\">\r\n                                <div>\r\n                                    <h5>");
				jteOutput.setContext("h5", null);
				jteOutput.writeUserContent(quest.getName());
				jteOutput.writeContent("</h5>\r\n                                    <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(quest.getDescription());
				jteOutput.writeContent("</p>\r\n                                </div>\r\n                                <span class=\"badge bg-primary\">Level ");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(quest.getRequiredLevel());
				jteOutput.writeContent("</span>\r\n                            </div>\r\n                            <div class=\"mt-2\">\r\n                                <form action=\"/quest/abandon\" method=\"post\" class=\"d-inline\">\r\n                                    <input type=\"hidden\" name=\"questName\"");
				var __jte_html_attribute_1 = quest.getName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\r\n                                    <button type=\"submit\" class=\"btn btn-danger btn-sm\">Abandon</button>\r\n                                </form>\r\n                                <form action=\"/quest/complete\" method=\"post\" class=\"d-inline\">\r\n                                    <input type=\"hidden\" name=\"questName\"");
				var __jte_html_attribute_2 = quest.getName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_2);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\r\n                                    <button type=\"submit\" class=\"btn btn-success btn-sm\">Complete</button>\r\n                                </form>\r\n                            </div>\r\n                        </li>\r\n                    ");
			}
			jteOutput.writeContent("\r\n                ");
		}
		jteOutput.writeContent("\r\n            </ul>\r\n        </div>\r\n\r\n        ");
		jteOutput.writeContent("\r\n        <div class=\"col-md-4\">\r\n            <h2>Completed Quests</h2>\r\n            <ul class=\"list-group\">\r\n                ");
		if (completedQuests.isEmpty()) {
			jteOutput.writeContent("\r\n                    <li class=\"list-group-item\">No quests have been completed.</li>\r\n                ");
		} else {
			jteOutput.writeContent("\r\n                    ");
			for (Quest quest : completedQuests) {
				jteOutput.writeContent("\r\n                        <li class=\"list-group-item\">\r\n                            <div class=\"d-flex justify-content-between align-items-center\">\r\n                                <div>\r\n                                    <h5>");
				jteOutput.setContext("h5", null);
				jteOutput.writeUserContent(quest.getName());
				jteOutput.writeContent("</h5>\r\n                                    <p>");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(quest.getDescription());
				jteOutput.writeContent("</p>\r\n                                </div>\r\n                                <form action=\"/quest/delete\" method=\"post\" class=\"ms-3\">\r\n                                    <input type=\"hidden\" name=\"questName\"");
				var __jte_html_attribute_3 = quest.getName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_3);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\r\n                                    <button type=\"submit\" class=\"btn btn-danger btn-sm\">Delete</button>\r\n                                </form>\r\n                            </div>\r\n                            <small class=\"text-muted\">Requires level ");
				jteOutput.setContext("small", null);
				jteOutput.writeUserContent(quest.getRequiredLevel());
				jteOutput.writeContent("</small>\r\n                        </li>\r\n                    ");
			}
			jteOutput.writeContent("\r\n                ");
		}
		jteOutput.writeContent("\r\n            </ul>\r\n        </div>\r\n    </div>\r\n    <a href=\"/home\" class=\"btn btn-link mt-4\">Back to Home</a>\r\n</div>\r\n\r\n<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"></script>\r\n</body>\r\n</html>\r\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Player player = (Player)params.get("player");
		List<Quest> followedQuests = (List<Quest>)params.get("followedQuests");
		List<Quest> availableQuests = (List<Quest>)params.get("availableQuests");
		List<Quest> completedQuests = (List<Quest>)params.get("completedQuests");
		render(jteOutput, jteHtmlInterceptor, player, followedQuests, availableQuests, completedQuests);
	}
}
