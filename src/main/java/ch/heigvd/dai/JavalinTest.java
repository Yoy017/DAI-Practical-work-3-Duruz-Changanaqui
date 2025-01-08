package ch.heigvd.dai;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class JavalinTest {
    public static final int PORT = 8080;
    public static final String HOME = "home.jte";

    public static void main(String[] args) {
        DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src/main/resources/view/"));
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);

        // Create the Javalin app
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
        });

        // Redirect to the home page (default)
        app.get("/", ctx -> {
            ctx.redirect("/home");
        });

        // Display the home page
        app.get("/home", ctx -> {
            ctx.render(HOME);
        });

        // Start the app
        app.start(PORT);
    }
}