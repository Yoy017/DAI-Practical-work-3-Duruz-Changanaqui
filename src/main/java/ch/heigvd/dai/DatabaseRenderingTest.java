package ch.heigvd.dai;

import ch.heigvd.dai.controller.ArmoryController;
import ch.heigvd.dai.controller.HomeController;
import ch.heigvd.dai.controller.ItemsController;
import ch.heigvd.dai.model.repository.ItemsRepository;
import ch.heigvd.dai.model.service.HomeService;
import ch.heigvd.dai.model.repository.HomeRepository;
import ch.heigvd.dai.database.PostgresDatabaseConnection;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.DirectoryCodeResolver;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;

import java.nio.file.Path;

public class DatabaseRenderingTest {
    private static final String name = "Darkphoenix";

    public static void main(String[] args) {
        DirectoryCodeResolver codeResolver = new DirectoryCodeResolver(Path.of("src/main/resources/view/"));
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);

        // Create the Javalin app
        Javalin app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(templateEngine));
        });

        // Initialisation de la connexion à la base de données
        PostgresDatabaseConnection conn = new PostgresDatabaseConnection("jdbc:postgresql://localhost:5432/", "bdr", "bdr");

        // Initialisation des repository, services et contrôleurs
        HomeController homeController = new HomeController(new HomeService(new HomeRepository(conn)));
        ArmoryController armoryController = new ArmoryController(app, conn);
        ItemsController itemsController = new ItemsController(app, conn);

        app.get("/", ctx ->  ctx.redirect("/home") );

        // Enregistrer les routes
        app.get("/home", homeController::getAllPlayers);

        // Démarrer l'application
        app.start(8080);
    }
}