import io.javalin.Javalin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        Javalin app = Javalin.create();
        AtomicReference<String> htmlContent = new AtomicReference<>();

        app.get("/", ctx -> {
            ctx.redirect("/home");
        });

        // Display the home page
        app.get("/home", ctx -> {
            htmlContent.set(new String(Files.readAllBytes(Paths.get("src/main/view/home.html"))));
            ctx.html(htmlContent.get());
        });

        app.start(PORT);
    }
}