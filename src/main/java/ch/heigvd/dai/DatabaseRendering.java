package ch.heigvd.dai;

import ch.heigvd.dai.controller.*;
import ch.heigvd.dai.database.PostgresDatabaseConnection;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;

public class DatabaseRendering {
    public static void main(String[] args) {
        DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src/main/resources/view/"));
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);

        // Create the Javalin app
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
        });

        // Initialisation de la connexion à la base de données
        PostgresDatabaseConnection conn = new PostgresDatabaseConnection("jdbc:postgresql://database:5432/bdr", "bdr", "bdr");

        // Initialisation des repository, services et contrôleurs
        HomeController homeController = new HomeController(app, conn);
        ArmoryController armoryController = new ArmoryController(app, conn);
        ItemsController itemsController = new ItemsController(app, conn);
        RegisterController reindexController = new RegisterController(app, conn);
        QuestController questController = new QuestController(app, conn);

        app.get("/", ctx ->  ctx.redirect("/home"));

        // Démarrer l'application
        app.start(8080);
    }
}