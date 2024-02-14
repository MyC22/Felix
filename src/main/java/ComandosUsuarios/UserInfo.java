package ComandosUsuarios;

import Commands.Command;
import org.apache.commons.lang3.StringUtils;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import java.awt.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


public class UserInfo extends Command {

    public UserInfo(DiscordApi api){
        super(api);

        api.addMessageCreateListener(event ->
                userInfoResponse(super.getChannel(), super.getMessage()));
    }

    public void userInfoResponse(TextChannel channel, Message message) {
        String[] msj = message.getContent().split(" ");
        if (!StringUtils.equalsIgnoreCase(String.valueOf(message.getContent().charAt(0)),("$")))
            return;
        if (msj.length == 1 && msj[0].equalsIgnoreCase("$user")) {
            channel.sendMessage("Para obtener la informacion de un usuario escriba, $user [nombre]");
        }
        else if (msj.length == 2 && msj[0].equalsIgnoreCase("$user")) {
            String mencionUsuario = msj[1];

            if (message.getMentionedUsers().isEmpty()) {
                channel.sendMessage("Por favor mencione a alguien");
                return;
            }

            OffsetDateTime fechaActual = OffsetDateTime.now();
            DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = fechaActual.format(formatear);

            User usuarioMencionado = message.getMentionedUsers().get(0);
            EmbedBuilder infoEmbed = new EmbedBuilder()
                    .setTitle(usuarioMencionado.getName() + " Info:")
                    .setColor(Color.MAGENTA)
                    .addField("Nombre:", mencionUsuario, true)
                    .addField("Discriminator", usuarioMencionado.getDiscriminator(), true)
                    .addField("Fch. Creación", usuarioMencionado.getCreationTimestamp().toString(), true)
                    .setThumbnail(usuarioMencionado.getAvatar().getUrl().toString())
                    .setFooter(fechaFormateada);
            channel.sendMessage(infoEmbed);
        }
    }
}

