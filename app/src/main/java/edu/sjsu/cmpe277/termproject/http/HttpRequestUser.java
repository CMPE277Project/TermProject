package edu.sjsu.cmpe277.termproject.http;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import edu.sjsu.cmpe277.termproject.models.User;

/**
 * Created by emy on 12/1/15.
 */
public class HttpRequestUser extends AsyncTask<User, Void, String> {

    String urlparameter="";

  HttpURLConnection httpURLConnection = null;
  URL url =null;
  DataOutputStream dataOutputStream=null;
    BufferedReader reader;
    String output="";

    @Override
    protected String doInBackground(User... params) {
        try {
            url = new URL("http://79.170.44.117/rajat-bansal.com/skiBuddy/userInfo.php");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");

            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            JSONObject jsonObject = new JSONObject();

            User user = params[0];
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("image_id", user.getImageFile());

            dataOutputStream.writeBytes(jsonObject.toString());
            dataOutputStream.flush();
            dataOutputStream.close();

            output= reader(httpURLConnection.getInputStream());
            Log.d("User id: ", output);

        }
        catch(MalformedURLException ex){

        }
        catch(IOException ex) {

        }
        catch(JSONException ex){

        }
        finally {
            try {
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();
        }
        return null;
    }

    private String reader(InputStream inputStream) {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String output="";
            while((output=bufferedReader.readLine())!=null) {
                stringBuilder.append(output);
            }
        }
        catch(IOException ex){
              ex.printStackTrace();
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
