package com.example.owner.github_repos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Owner on 16-Mar-17.
 */

public class asyncClass extends AsyncTask<String, Void, ArrayList<RepoDetails>> {

    public interface onResponse {
        public void onSuccess(ArrayList<RepoDetails> RD);
    }


    asyncClass.onResponse listen;
    private Context context;
    ArrayList<RepoDetails> Data = new ArrayList<RepoDetails>();

    public asyncClass(Context cont, asyncClass.onResponse listen) {
        this.context = cont;
        this.listen = listen;
    }


    @Override
    protected ArrayList<RepoDetails> doInBackground(String... params) {
        String JSONstr = "";
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(connection.getInputStream());//InputStream Returns an estimated number of bytes && BufferedInputStream create buffer by bytes
            BufferedReader reader = new BufferedReader(new InputStreamReader(in)); //reader just create buffer && InputStreamReader just decode byte into chars
            String line = "";

            try {
                while ((line = reader.readLine()) != null) {
                    JSONstr += line;
                }
                in.close();
            } catch (Exception ex) {

            }

            try {
                JSONArray JArr = new JSONArray(JSONstr);

                for (int i = 0; i < JArr.length(); ++i) {
                    JSONObject JObject = JArr.getJSONObject(i);
                    int id = JObject.getInt("id");
                    String name = JObject.getString("name");
                    String RHtml_Url = JObject.getString("html_url");
                    String description = JObject.getString("description");
                    JSONObject inObject = JObject.getJSONObject("owner");
                    String usrname = inObject.getString("login");
                    String img = inObject.getString("avatar_url");
                    String OHtml_Url = inObject.getString("html_url");

                    Data.add(new RepoDetails(id, name, usrname, img, description, OHtml_Url, RHtml_Url));
                }

            } catch (JSONException e) {
                e.printStackTrace(); // handle error of JsonArray
            }


        } catch (MalformedURLException e) {
            e.printStackTrace(); // handle error of problem in URL
        } catch (IOException e) {
            e.printStackTrace(); // handle error of opening connection
        }
        return Data;
    }

    @Override
    protected void onPostExecute(final ArrayList<RepoDetails> repoDetailses) {
        listen.onSuccess(repoDetailses);
    }
}
