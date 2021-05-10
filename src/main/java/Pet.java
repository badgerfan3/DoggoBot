import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.MessageDecoration;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.io.File;
import java.lang.Math;

import java.util.concurrent.TimeUnit;

public class Pet implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        double randomMessageDecider = Math.random();
        String response;
        if(randomMessageDecider < 0.25){
            response = "says \"Bark! Bark! Bark!\"";
        }else if(randomMessageDecider >= 0.25 && randomMessageDecider <0.5){
            response = "*rolls over*";
        }else if(randomMessageDecider >= 0.5 && randomMessageDecider <0.75){
            response = "*licks your face*";
        }else{
            response = "*looks at you*";
        }

        Message message = event.getMessage();
        if(message.getContent().equalsIgnoreCase(DoggoBot.commandPrefix+"pet")) {
            long messageAuthor = message.getAuthor().getId();
            String messageAuthorName = message.getAuthor().getName();
            long messageGuildID = message.getChannel().getId();
            SQLiteDBInsert.insert(messageAuthor, messageAuthorName, messageGuildID);
            TextChannel channel = message.getChannel();

            new MessageBuilder()
                    .append("Doggo Bot " + response)
                    .append("\n"+messageAuthorName + " you have pet the dog ")
                    .append(SQLiteQuery.query(messageAuthor,messageGuildID) + "", MessageDecoration.BOLD, MessageDecoration.UNDERLINE)
                    .append(" times!")
                    .send(channel);
            }
        }
}
