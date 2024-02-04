package ComandosUsuarios;

import Commands.Command;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Avatar extends Command {

    public Avatar(DiscordApi api){

        super(api);

        api.addMessageCreateListener(event ->
                ShowAvatar(super.getCanal(), super.getMensaje()));
    }

    public void ShowAvatar(TextChannel canal, Message mensaje){
        String msj[] = mensaje.getContent().split(" ");
        if (msj.length == 1 && msj[0].equalsIgnoreCase("$avatar")){
            canal.sendMessage("Para obtener el avatar de un usuario escriba, $avatar [nombre]");
        }
        else if (msj.length == 2 && msj[0].equalsIgnoreCase("$avatar")) {

            if (mensaje.getMentionedUsers().isEmpty()){
                canal.sendMessage("Por favor mencione a un Usuario");
                return;
            }

            OffsetDateTime fechaActual = OffsetDateTime.now();
            DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = fechaActual.format(formatear);

            User usuarioMencionado = mensaje.getMentionedUsers().get(0);
            Icon avatar = usuarioMencionado.getAvatar();
            EmbedBuilder avatarEmbed = new EmbedBuilder()
                    .setTitle(usuarioMencionado.getName() + " Avatar:")
                    .setImage(avatar)
                    .setFooter(fechaFormateada);
            canal.sendMessage(avatarEmbed);
        }

    }
}
