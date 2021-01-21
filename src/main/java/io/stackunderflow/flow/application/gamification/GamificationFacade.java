package io.stackunderflow.flow.application.gamification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.squareup.okhttp.*;
import io.stackunderflow.flow.application.gamification.dto.Badge;
import io.stackunderflow.flow.application.gamification.dto.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class GamificationFacade {

    // TODO : trouver un moyen "propre" de déclarer ces infos dans un fichier xml, json ou qqch comme ça ?
    final private int PORT = ServerInformation.PORT;
    final private String ADRESS = ServerInformation.ADRESS;
    final private String apiURL = ServerInformation.apiURL;
    final private String apiKey = ServerInformation.apiKey;

    final private OkHttpClient client = new OkHttpClient();
    final private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    final private Gson gson = new Gson();

    /**
     * Get all the registered badges
     * @return List of Badge
     */
    public List<Badge> getAvailaibleBadges(){
        return getListOfBadges("");
    }

    /**
     * Save badge - user association into our db
     * @param username id of the user
     */
    public List<Badge> getUserBadges(String username){
        return getListOfBadges("user/" + username);
    }

    /**
     * Generic call to /badges
     * @param additionalRequest : additional url request, eg. "/user/toto"
     * @return List of badges
     */
    public List<Badge> getListOfBadges(String additionalRequest){
        Request request = new Request.Builder()
                .url(apiURL + "/badges/" + additionalRequest)
                .header("x-api-key", apiKey)
                .build();

        ArrayList<Badge> badges = new ArrayList<>();
        Call call = client.newCall(request);
        try{
            //Send the request
            Response response = call.execute();
            if(response.code() == 404){
                return badges;
            }
            //Parse the response
            Type badgeListType = new TypeToken<ArrayList<Badge>>(){}.getType();
            badges = gson.fromJson(response.body().string(), badgeListType);

        }catch (Exception e){
            e.printStackTrace();
        }
        return badges;
    }

    /**
     *
     * @param username
     * @param eventTypeId
     * @return Optional<Badge>
     */
    public Optional<Badge> sendEvent(String username, int eventTypeId){
        RequestBody body = RequestBody.create(JSON,
                "{\"username\": \""+ username +"\",\"eventTypeId\":"+eventTypeId+"}");

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


    /**
     * Get the top users by experience they got with their badges
     * @param limit lenght of the top (5, 10, 100 ...)
     * @return Arraylist of users
     */
    public List<User> getTopUsersChart(int limit){
        Request request = new Request.Builder()
                .url(apiURL + "/top/" + limit)
                .header("x-api-key", apiKey)
                .build();

        Call call = client.newCall(request);
        List<User> topUsers = new ArrayList<>();
        try{
            //Send the request
            Response response = call.execute();
            if(response.code() != 200){
                throw new IllegalArgumentException();
            }
            // For some reason, putting the response in a string before check it
            // change the behavior of directly access it
            String respBody = response.body().string();

            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            topUsers = gson.fromJson(respBody, userListType);

        }catch (IOException e){
            e.printStackTrace();
        }

        return topUsers;
    }

}
