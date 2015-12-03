package edu.sjsu.cmpe277.termproject.http;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import edu.sjsu.cmpe277.termproject.models.User;

/**
 * Created by emy on 11/30/15.
 */
public class PostUserInfo extends AsyncTask<User, Void, String> {

    public PostUserInfo() {
        Log.d("Hello: ", "constructor" );
    }


    @Override
    protected String doInBackground(User... params) {

        HttpURLConnection httpsURLConnection = null;
        OutputStream outputStream = null;
        DataOutputStream writer;


        try{
            URL url = new URL("");
            httpsURLConnection = (HttpURLConnection)url.openConnection();
          //  httpsURLConnection.setDoInput(true);


            //set header
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            httpsURLConnection.setRequestProperty("Accept", "application/json");
            httpsURLConnection.setDoOutput(true);
           // int urlResponseCode = httpsURLConnection.getResponseCode();

            User user = params[0];


            JSONObject jsonObject = new JSONObject();
            Log.d("Message: ", user.getLastName());
            jsonObject.put("firstName",user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("image_Id", user.getImageFile());


//            outputStream = new DataOutputStream(httpsURLConnection.getOutputStream());
//            outputStream.write(jsonObject.toString().getBytes("UTF-8"));
//            outputStream.flush();
            //outputStream.close();
            writer = new DataOutputStream(httpsURLConnection.getOutputStream());
           // httpsURLConnection.getOutputStream().write(jsonObject.toString().getBytes());
            writer.writeBytes(jsonObject.toString());
            writer.flush();
            writer.close();


            if(httpsURLConnection.getResponseCode()== HttpURLConnection.HTTP_OK){
                throw new RuntimeException("Lol: HTTP error code: "+httpsURLConnection.getResponseCode());
            }
//            reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
//            StringBuilder stringBuilder = new StringBuilder();
//
//            while((output=reader.readLine())!=null) {
//                stringBuilder
//            }

        }
        catch(MalformedURLException ex) {
            Log.d("Error message: ", ex.getLocalizedMessage());
        }
        catch(Exception ex) {
            Log.d("Error Message", ex.getLocalizedMessage());
        }
        finally {
                //outputStream.close();
                httpsURLConnection.disconnect();

        }
        return null;
    }

}
