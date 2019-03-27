package com.example.a2praktiskaismape;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class IegutDatus extends AsyncTask<Void,Void,Void> {

    String data = "";
    private int lenght = 0;
    private String[] dataParsed = new String[100];

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL ("https://api.github.com/users/valtersrauls/repos");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONArray JA = new JSONArray(data);
            for(int i = 0; i <JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);
                String singleParsed = "Repozitorijas nosaukums: " + JO.get("name") + "\n";
                JSONObject owner = JO.getJSONObject("owner");
                String ownerParsed = "Autors: " + owner.getString("login");
                dataParsed[i] = singleParsed + ownerParsed + "\n";
                lenght += 1;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

    String[] returnStringArray() {
        return dataParsed;
    }
    Integer returnLength() { return lenght; }
}
