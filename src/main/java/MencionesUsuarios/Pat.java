package MencionesUsuarios;

import ComandosUsuarios.Gif;
import Commands.Command;
import Managment.BotInfo;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;

import java.util.List;

public class Pat extends Command {

    public Pat(DiscordApi api){
        super(api);

        api.addMessageCreateListener(event ->
                patCommand(super.getChannel(), super.getMessage(), super.getAuthor(), super.getArg()));
    }

    public void patCommand(TextChannel channel, Message message, MessageAuthor author, List<String> args){
        String msj[] = message.getContent().split(" ");
        // Verificar si el mensaje comienza con "$hug"
        if (!msj[0].equalsIgnoreCase("$hug")) {
            return; // Si no comienza con "$hug", salir del método
        }

        if (msj.length == 1 && msj[0].equalsIgnoreCase("$hug")) {
            channel.sendMessage("Para abrazar a alguien escriba, $hug [@nombre]");
            return;
        }

        if (message.getMentionedUsers().get(0).getIdAsString().equals(author.getIdAsString())) {
            channel.sendMessage("Realmente quieres darte un abrazo solo eso es muy triste :c");
        }
        if (message.getMentionedUsers().get(0).getIdAsString().equals(BotInfo.getBotId())) {
            channel.sendMessage("Oh quieres darme un abrazo?, Gracias c:");
        }
//        channel.sendMessage(Pat.buildEmbed(author, message.getMentionedUsers().get(0), Gif.searchGif("Anime-Hug"), "Abrazado"))
//                .exceptionally(e -> {
//                    channel.sendMessage("no se pudo enviar el mensaje");
//                    return null;
//                });
    }
}
