import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class Ping implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        Message message = event.getMessage();
        if(message.getContent().equalsIgnoreCase(DoggoBot.commandPrefix+"ping")) {
            event.getChannel().sendMessage("Pong!");
        }
    }
}
