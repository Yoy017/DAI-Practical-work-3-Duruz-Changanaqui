package ch.heigvd.dai;

import io.javalin.Javalin;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JavalinTest {
    public static final int PORT = 8080;
    public static final String HOME = "src/main/view/home.jte";

    public static void main(String[] args) {
        // Create the Javalin app
        Javalin app = Javalin.create();

        // Redirect to the home page (default)
        app.get("/", ctx -> {
            ctx.redirect("/home");
        });

        // Display the home page
        app.get("/home", ctx -> {
            String htmlContent = new String(Files.readAllBytes(Paths.get(HOME)));
            ctx.html(htmlContent);
        });

        // Start the app
        app.start(PORT);

//        Javalin app = Javalin.create();
//        AtomicReference<String> htmlContent = new AtomicReference<>();
//
//        // Redirect to the home page (default)
//        app.get("/", ctx -> {
//            ctx.redirect("/home");
//        });
//
//        // Display the home page
//        app.get("/home", ctx -> {
//            htmlContent.set(new String(Files.readAllBytes(Paths.get("src/main/view/home.jte"))));
//            ctx.html(htmlContent.get());
//        });
//
//        // Display the login page
//        app.get("/login", ctx -> {
//            htmlContent.set(new String(Files.readAllBytes(Paths.get("src/main/view/login.html"))));
//            ctx.html(htmlContent.get());
//        });
//
//        // Display the inventory
//
//        app.start(PORT);
    }
}