package edu.sjsu.cmpe277.termproject.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by davchen on 11/30/15.
 */
public class ApiServer {
    public ApiServer() {}

    public JSONObject postRequest(String endpoint, String params) {
        JSONObject jsonResponse = new JSONObject();

        try {
            // Set up URL Connection
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Write to Connection Stream
            DataOutputStream os = new DataOutputStream(connection.getOutputStream());
            os.writeBytes(params);
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { //success

                // Handle Response
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                jsonResponse = new JSONObject(response.toString());
            }

            if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) { // error
                // Handle Response
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                jsonResponse = new JSONObject(response.toString());

                throw new Exception(response.toString());
            }


        } catch (Exception e) {
            System.out.println(e.toString());
            return jsonResponse;
        }
        return jsonResponse;
    }

    public JSONObject getRequest(String endpoint) {
        JSONObject jsonResponse = new JSONObject();

        try {
            // Set up URL Connection
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            // Handle Response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                jsonResponse = new JSONObject(response.toString());
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return jsonResponse;
    }

    public JSONArray getRequestArray(String endpoint) {
        JSONArray jsonResponse = new JSONArray();

        try {
            // Set up URL Connection
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            // Handle Response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                    System.out.println("ApiServer response is: " + response);
                }
                in.close();

                jsonResponse = new JSONArray(response.toString());
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("ApiServer jsonResponse is: " + jsonResponse);
        return jsonResponse;

    }
}
