package ComandosUsuarios;

import Commands.Command;
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
                userInfoResponse(super.getCanal(), super.getMensaje()));
    }

    public void userInfoResponse(TextChannel canal, Message mensaje) {
        String[] msj = mensaje.getContent().split(" ");

        if (msj.length == 1 && msj[0].equalsIgnoreCase("$user")) {
            canal.sendMessage("Para obtener la informacion de un usuario escriba, $user [nombre]");
        }
        else if (msj.length == 2 && msj[0].equalsIgnoreCase("$user")) {
            String mencionUsuario = msj[1];

            if (mensaje.getMentionedUsers().isEmpty()) {
                canal.sendMessage("Por favor mencione a alguien");
                return;
            }

            OffsetDateTime fechaActual = OffsetDateTime.now();
            DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaFormateada = fechaActual.format(formatear);

            User usuarioMencionado = mensaje.getMentionedUsers().get(0);
            EmbedBuilder infoEmbed = new EmbedBuilder()
                    .setTitle(usuarioMencionado.getName() + " Info:")
                    .setColor(Color.MAGENTA)
                    .addField("Nombre:", mencionUsuario, true)
                    .addField("Discriminator", usuarioMencionado.getDiscriminator(), true)
                    .addField("Fch. Creación", usuarioMencionado.getCreationTimestamp().toString(), true)
                    .setThumbnail(usuarioMencionado.getAvatar().getUrl().toString())
                    .setFooter(fechaFormateada);
            canal.sendMessage(infoEmbed);
        }
    }
}

