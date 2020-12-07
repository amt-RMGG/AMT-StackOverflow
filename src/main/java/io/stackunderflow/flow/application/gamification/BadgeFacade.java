package io.stackunderflow.flow.application.gamification;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jdk.jfr.Name;
import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Name("BadgeFacade")
public class BadgeFacade {

    private int PORT = 8090;
    private String ADRESS = "localhost";
    private String apiURL = "http://" + ADRESS + ":" + PORT;
    private String apiKey = "fcf02ac4-372e-4923-a0d3-7f375dec06d0";

    public List<Badge> getAvailaibleBadges(){

        HttpURLConnection con = getApiConnexion(apiURL + "/badges/");
        List<Badge> badges = fetchAndParseBadges(con);
        return badges;
    }

    /**
     * Get the json content (list of badges) from an url and parse it
     * TODO : Maybe we want to have something more generic ?
     * @param con http connection
     * @return Arraylist of badges
     */
    private ArrayList<Badge> fetchAndParseBadges(HttpURLConnection con){
        ArrayList<Badge> badges = new ArrayList<>();

        // Fetchin
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Parsing
            Gson gson = new Gson();
            Type badgeListType = new TypeToken<ArrayList<Badge>>(){}.getType();
            badges = gson.fromJson(response.toString(), badgeListType);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return badges;
    }

    private HttpURLConnection getApiConnexion(String _url){
        try {
            URL url = new URL(_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("x-api-key", apiKey);
            con.setDoOutput(true);
            return con;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
