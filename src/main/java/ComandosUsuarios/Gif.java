package ComandosUsuarios;

import Commands.Command;
import Managment.BotInfo;
import Managment.Tenor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.List;

@Getter
public class Gif extends Command {

    public Gif(DiscordApi api){
        super(api);

        api.addMessageCreateListener(event ->
                userGifSearch(super.getChannel(), super.getAuthor(), super.getArg())
        );
    }

    private void userGifSearch(TextChannel channel, MessageAuthor author, List<String> arg){
        if (arg.size() == 0){
            channel.sendMessage("Por favor escriba un termino de búsqueda");
            return;
        }
        StringBuilder term = new StringBuilder();
        for (String s: arg){
            term.append(s + " ");
        }

        channel.sendMessage(builderEmbed(term.toString(), author)).exceptionally(e -> {
            channel.sendMessage("Gif no encontrado");
            return null;
        });


    }

    public EmbedBuilder builderEmbed(String url, MessageAuthor author){
        EmbedBuilder gifEmbed = new EmbedBuilder()
                .setTitle("Toma un Gif")
                .setAuthor(author.getName(), author.getAvatar().getUrl().toString(), author.getAvatar())
                .setColor(Color.MAGENTA)
                .setImage(url)
                .setFooter(BotInfo.getBotName(), BotInfo.getBotImage())
                .setTimestampToNow();
        return gifEmbed;
    }

    @SneakyThrows
    public static String searchGif(String term){
        JSONObject searchResult = Tenor.getSearchResults(term, 13);
        JSONArray results;
        JSONObject selectGif;
        System.err.println(searchResult);

        if(ObjectUtils.isNotEmpty((searchResult))){
            System.err.println("Funciono");
            results = searchResult.getJSONArray("results");
            selectGif = (JSONObject) results.get((int) Math.floor(Math.random() * results.length()));
        }else {
            System.err.println("No vino nadie");
            return null;
        }

        JSONObject selectMediaType = (JSONObject) selectGif.getJSONArray("media").getJSONObject(0).get("gif");

        return String.valueOf(selectMediaType.get("url"));
    }
}
