import org.javacord.api.entity.message.Message;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import java.lang.Math;

import java.util.concurrent.TimeUnit;

public class Pet implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        double randomMessageDecider = Math.random();
        String response;
        if(randomMessageDecider < 0.25){
            response = "Bark! Bark! Bark!";
        }else if(randomMessageDecider >= 0.25 && randomMessageDecider <0.5){
            response = "*rolls over*";
        }else if(randomMessageDecider >= 0.5 && randomMessageDecider <0.75){
            response = "*licks your face*";
        }else{
            response = "*looks at you*";
        }

        Message message = event.getMessage();
        if(message.getContent().equalsIgnoreCase(DoggoBot.commandPrefix+"pet")) {
            event.getChannel().sendMessage(response).thenAccept(message1 -> {
                message1.addReactionAddListener(reactionEvent -> {
                    if(reactionEvent.getEmoji().equalsEmoji("\uD83D\uDD2B")) {
                        reactionEvent.deleteMessage();
                        reactionEvent.getChannel().sendMessage("*BITE*");
                    }
                }).removeAfter(60, TimeUnit.MINUTES);
            });
        }
    }
}
