package io.stackunderflow.flow.application.gamification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.squareup.okhttp.*;
import com.sun.tools.jconsole.JConsoleContext;
import io.github.cdimascio.dotenv.Dotenv;
import jdk.jfr.Name;
import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Name("BadgeFacade")
public class GamificationFacade {

    // TODO : Mettre tout ça dans un fichier de config (.env ?)
    final private int PORT = 8090;
    final private String ADRESS = "localhost";
    final private String apiURL = "http://" + ADRESS + ":" + PORT;
    final private String apiKey = "fcf02ac4-372e-4923-a0d3-7f375dec06d0";

    final private OkHttpClient client = new OkHttpClient();
    final private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    final private Gson gson = new Gson();

    /**
     * Get all the registered badges
     * @return List of Badge
     */
    public List<Badge> getAvailaibleBadges() throws IOException {
        Request request = new Request.Builder()
                .url(apiURL + "/badges/")
                .header("x-api-key", apiKey)
                .build();

        ArrayList<Badge> badges = new ArrayList<>();
        Call call = client.newCall(request);
        try{
            //Send the request
            Response response = call.execute();
            //Parse the response
            Type badgeListType = new TypeToken<ArrayList<Badge>>(){}.getType();
            badges = gson.fromJson(response.body().string(), badgeListType);

        }catch (IOException e){
            e.printStackTrace();
        }
        return badges;
    }

    /**
     * Save badge - user association into our db
     * @param userId id of the user
     */
 /*   public List<Badge> getUserBadges(int userId){
            // TODO
    }*/

    public Optional<Badge> sendEvent(int userId, int eventTypeId){
        RequestBody body = RequestBody.create(JSON,
                "{\"userid\": "+ userId +",\"eventTypeId\":"+eventTypeId+"}");

        Request request = new Request.Builder()
                .url(apiURL + "/events/")
                .header("x-api-key", apiKey)
                .post(body)
                .build();

        Call call = client.newCall(request);

        try{
            //Send the request
            Response response = call.execute();
            if(response.code() != 200){
                throw new IllegalArgumentException();
            }
            // For some reason, putting the response in a string before check it
            // change the behavior of directly access it
            String respBody = response.body().string();
            if(!respBody.equals("")){
                Type badgeType = new TypeToken<Badge>(){}.getType();
                Badge badge = gson.fromJson(respBody, badgeType);
                return Optional.of(badge);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
