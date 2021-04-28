package mx.itesm.luisbodart_gerareyes;

import android.os.Message;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JuegoRequest extends Thread {

    private String url;
    private Handler handler;

    public JuegoRequest(String url, Handler handler){
        this.url = url;
        this.handler = handler;
    }

    public void run(){

        try {

            URL address = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();

            int code = connection.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sBuilder = new StringBuilder();
                String currentLine;

                while((currentLine = br.readLine()) != null){
                    sBuilder.append(currentLine);
                }

                String messageResult = sBuilder.toString();
                JSONArray JSONArray = new JSONArray(messageResult);
                Message message = new Message();
                message.obj = messageResult;
                handler.sendMessage(message);


            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }
}