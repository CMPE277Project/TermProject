package edu.sjsu.cmpe277.termproject.http;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.sjsu.cmpe277.termproject.models.Event;

/**
 * Created by emy on 12/1/15.
 */
public class HttpPostEvent extends AsyncTask<Event, Void, String> {


    @Override
    protected String doInBackground(Event... params) {
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        DataOutputStream dataOutputStream = null;
        try
        {

            url = new URL("");
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setRequestProperty("Content-Type", "application/json");
//            httpURLConnection.setRequestProperty("Accept", "application/json");
            Event event = params[0];


            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", event.getTitle());
            jsonObject.put("description", event.getDescription());
            jsonObject.put("", event.getStartTime());
            jsonObject.put("", event.getEndTime());
            //jsonObject.put("", event.getUser().getUserId());

            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(jsonObject.toString());
            dataOutputStream.flush();
            dataOutputStream.close();

        }
        catch(MalformedURLException ex) {

        }
        catch(IOException ex) {

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
