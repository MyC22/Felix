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
                responseToxic(super.getCanal(), super.getServidor(), super.getMensaje(), super.getMensajeAutor()));
    }

    @SneakyThrows
    public void responseToxic(TextChannel canal, Server servidor, Message mensaje, MessageAuthor mensajeAutor){
        if (mensaje.getContent().equalsIgnoreCase("bot de mierda")){
            canal.sendMessage("A quien le dices bot de mierda tu maldita kgada");
            canal.sendMessage("Si te estoy hablando a ti" + "<@" + mensajeAutor.getIdAsString()+">");
            Thread.sleep(1000);
        }
    }
}
