package ComandosUsuarios;

import Commands.Command;
import org.apache.commons.lang3.StringUtils;
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
                ShowAvatar(super.getChannel(), super.getMessage()));
    }

    public void ShowAvatar(TextChannel channel, Message message){
        String msj[] = message.getContent().split(" ");
        if (!StringUtils.equalsIgnoreCase(String.valueOf(message.getContent().charAt(0)),("$")))
            return;
        if (msj.length == 1 && msj[0].equalsIgnoreCase("$avatar")){
            channel.sendMessage("Para obtener el avatar de un usuario escriba, $avatar [nombre]");
        }
        else if (msj.length == 2 && msj[0].equalsIgnoreCase("$avatar")) {
            if (message.getMentionedUsers().isEmpty()){
                channel.sendMessage("Por favor mencione a un Usuario");
                return;
            }

            OffsetDateTime fechaActual = OffsetDateTime.now();
            DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = fechaActual.format(formatear);

            User usuarioMencionado = message.getMentionedUsers().get(0);
            Icon avatar = usuarioMencionado.getAvatar();
            EmbedBuilder avatarEmbed = new EmbedBuilder()
                    .setTitle(usuarioMencionado.getName() + " Avatar:")
                    .setImage(avatar)
                    .setFooter(fechaFormateada);
            channel.sendMessage(avatarEmbed);
        }

    }
}
