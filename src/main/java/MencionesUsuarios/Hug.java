package MencionesUsuarios;

import Commands.Command;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;

import java.util.List;

public class Hug extends Command {


    public Hug(DiscordApi api){
        super(api);

        api.addMessageCreateListener(event ->
            hugComando(super.getCanal(), super.getMensaje(), super.getMensajeAutor(), super.getArg()));
    }

    public void hugComando(TextChannel canal, Message mensaje, MessageAuthor autorMensaje, List<String> argumentos){


    }




}
