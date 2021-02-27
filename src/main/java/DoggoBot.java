
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import io.github.cdimascio.dotenv.*;

public class DoggoBot {
    static String commandPrefix;

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("BOT_TOKEN");

        commandPrefix = dotenv.get("BOT_PREFIX");

        DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();

        api.addListener(new Ping());
        api.addListener(new Pet());

        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
    }
}
