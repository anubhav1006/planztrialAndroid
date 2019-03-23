package com.planzappdemo.Connection;


import android.content.Context;
import android.widget.Toast;

import com.planzappdemo.Constants.PlanzConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class LocalHostConnection {

   /* HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet('localhost:8080');
    HttpResponse response = client.execute(request);
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));*/

    static PlanzConstants planzConstants;
    static URL obj;

    static {
        try {
            obj = new URL(planzConstants.url+"/create");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private Context context;

    public LocalHostConnection(Context context) {
        this.context = context;
    }

    public void POSTRequest(Map<String, String> keyVal) throws IOException {

        final String POST_PARAMS = "{\n" +
                "    \"name\": "+"\""+keyVal.get("name")+"\""+",\r\n" +

                "    \"username\": "+"\""+keyVal.get("username")+"\""+",\r\n" +

                "    \"password\": "+"\""+keyVal.get("password")+"\""+",\r\n" +

                "    \"age\": "+"\""+keyVal.get("age")+"\""+",\r\n" +

                "    \"address\": "+"\""+keyVal.get("address")+"\""+",\r\n" +

                "    \"lat\": "+"\""+keyVal.get("lat")+"\""+",\r\n" +

                "    \"lon\": "+"\""+keyVal.get("lon")+"\""+",\r\n" +

                "    \"pin\": "+"\""+keyVal.get("pin")+"\""+",\r\n" +

                "\n}";

        //System.out.println(POST_PARAMS);
        Toast.makeText(context, POST_PARAMS, Toast.LENGTH_LONG);


        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

        postConnection.setRequestMethod("POST");

        //postConnection.setRequestProperty("userId", "a1bcdefgh");

        postConnection.setRequestProperty("Content-Type", "application/json");

        postConnection.setDoOutput(true);

        OutputStream os = postConnection.getOutputStream();

        os.write(POST_PARAMS.getBytes());

        os.flush();

        os.close();

        int responseCode = postConnection.getResponseCode();

        //System.out.println("POST Response Code :  " + responseCode);

        Toast.makeText(context, "POST Response Code :  " + responseCode , Toast.LENGTH_SHORT);
        //
        // System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        Toast.makeText(context, "POST Response Message : " + postConnection.getResponseMessage(), Toast.LENGTH_SHORT);

        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success

            BufferedReader in = new BufferedReader(new InputStreamReader(

                    postConnection.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {

                response.append(inputLine);

            } in .close();

            // print result

            //System.out.println(response.toString());
            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT);

        } else {

            //System.out.println("POST NOT WORKED");
            Toast.makeText(context, "POST NOT WORKED", Toast.LENGTH_SHORT);

        }

    }

}
