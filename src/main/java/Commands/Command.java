package Commands;

import lombok.Getter;
import lombok.Setter;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Command {

    private DiscordApi api;
    private TextChannel channel;
    private Message message;
    private MessageAuthor author;
    private User user;
    private Server server;
    private String key;
    private String comando;
    private List<String> arg;

    public Command(DiscordApi api){
        this.api = api;

        api.addMessageCreateListener(event -> {
           message = event.getMessage();
           channel = event.getChannel();
           server = event.getServer().get();
           author = event.getMessageAuthor();
           key = message.getContent().split("\\s")[0];
            arg = new ArrayList<>();
        });
    }

    public boolean obCommand(DiscordApi api, Message message, Server server , ArrayList arg){
        comando = Thread.currentThread().getStackTrace()[3].getClassName().split("\\.")[1];
        api.addMessageCreateListener(event -> {
            setServer(event.getServer().get());
        });

        for (String argumento: message.getContent().split("\\s")) {
            if (argumento.equals(key)){
                continue;
            }
            arg.add(argumento);
        }

        return true;
    }

    public boolean obCommand(){
        return obCommand(this.api, this.message, this.server, (ArrayList) this.arg);
    }


}
