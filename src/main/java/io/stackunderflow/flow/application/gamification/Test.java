package io.stackunderflow.flow.application.gamification;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.*;
import io.stackunderflow.flow.application.gamification.dto.Badge;
import io.stackunderflow.flow.application.gamification.dto.User;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static Gson gson = new Gson();
    private static OkHttpClient client = new OkHttpClient();


    final private static String PORT = "8090";
    final private static String ADRESS = "localhost";
    final private static String apiURL = "http://" + ADRESS + ":" + PORT;
    final private static String apiKey = "ff1165b6-fc1a-44a8-a228-7a2fe9756c18";

    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test(){

        Request request = new Request.Builder()
                .url(apiURL + "/top/10")
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

        for (User u : topUsers){
            System.out.println(u.getUsername());
            System.out.println(u.getExperienceValue());
        }

     /*
        //Parse the response
        try{
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            topUsers = gson.fromJson(respBody, userListType);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static Optional<Badge> sendEvent(String username, int eventTypeId){
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

    public static ArrayList<Badge> getBadges(){
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

    private class Config {
        protected String ADRESS;
        protected String PORT;
        protected String APIKEY;
        protected String APIURL = "http://" + ADRESS + ":" + PORT;
      /*  public Config(String adress, String port, String apiKey){
            ADRESS = adress;
            PORT = port;
            APIKEY = apiKey;
            APIURL = "http://" + ADRESS + ":" + PORT;
        }*/
    }
}
