package Listener;

import Commands.Command;
import lombok.SneakyThrows;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.server.Server;

public class ToxicResponse extends Command {

    public ToxicResponse(DiscordApi api){
        super(api);

        api.addMessageCreateListener(event ->
                responseToxic(super.getChannel(), super.getServer(), super.getMessage(), super.getAuthor()));
    }

    @SneakyThrows
    public void responseToxic(TextChannel channel, Server server, Message message, MessageAuthor author){
        if (message.getContent().equalsIgnoreCase("este bot es horrible")){
            channel.sendMessage("Oye no digas esas cosas en este servidor si no quieres tener problemas");
            channel.sendMessage("Si te estoy hablando a ti" + "<@" + author.getIdAsString()+">");
            Thread.sleep(1000);
        }
    }
}
