package com.vb.ups.async;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.vb.ups.adapters.NotificationsAdapter;
import com.vb.ups.objects.Notification;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by berna on 11/17/2016.
 */

public class NotificationManager extends AsyncTask<Notification, Void, Notification> {
    @Override
    protected Notification doInBackground(Notification... notifications) {
        while(true){
            for(int i=0; i<NotificationsAdapter.notificationArrayList.size(); i++){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat timeFormat = new SimpleDateFormat("h:mm a");
                Log.v("NotificationManager",(NotificationsAdapter.notificationArrayList.get(i).getTime()+timeFormat.format(new Date())));
                if(NotificationsAdapter.notificationArrayList.get(i).getDate().equals(dateFormat.format(new Date())) && NotificationsAdapter.notificationArrayList.get(i).getTime().equals(timeFormat.format(new Date())) && !NotificationsAdapter.notificationArrayList.get(i).getSent() && NotificationsAdapter.notificationArrayList.get(i).getUserID() == FirebaseAuth.getInstance().getCurrentUser().getUid()){
                    sendNotification(NotificationsAdapter.notificationArrayList.get(i));
                    NotificationsAdapter.notificationArrayList.get(i).setSent(true);
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendNotification(Notification a) {
        String MY_API_KEY="AIzaSyBYlnssxSZiKxiCTPLMMy0Q6PeA3ESHPzg";
        String title = a.getTitle();
        String description = a.getDescription();
        String time = a.getTime();
        String date = a.getDate();
        String userID = a.getUserID();
        String url = "https://gcm-http.googleapis.com/gcm/send";
        JSONObject data = null;
        try {
            //data = new JSONObject("{ \"to\" : \"/topics/my_little_topic\", \"notification\" : { \"body\" : \"messageBody\", \"title\" : \"messageTitle\",\"icon\" : \"ic_cloud_white_48dp\"}}");
            data = new JSONObject("{ \"to\" : \"/topics/my_little_topic\", \"notification\" : { \"body\" : \""+description+"\", \"title\" : \""+title+"\",\"icon\" : \"ic_cloud_white_48dp\"}}");
        } catch (JSONException e) {
            Log.e("NotificationManager", e.toString());
        }
        try {
            makeRequest(url, data, MY_API_KEY);
        } catch (Exception e) {
            Log.e("NotificationPusher", e.toString());
        }

    }
    public static String makeRequest(String path, JSONObject data, String MY_API_KEY) throws Exception
    {
        //instantiates httpclient to make request
        DefaultHttpClient httpclient = new DefaultHttpClient();

        //url with the post data
        HttpPost httpost = new HttpPost(path);

        //passes the results to a string builder/entity
        StringEntity se = new StringEntity(data.toString());

        //sets the post request as the resulting string
        httpost.setEntity(se);
        //sets a request header so the page receving the request
        //will know what to do with it
        httpost.setHeader("Authorization", "key="+MY_API_KEY);
        httpost.setHeader("Content-type", "application/json");

        //Handles what is returned from the page
        ResponseHandler responseHandler = new BasicResponseHandler();
        return  (String) httpclient.execute(httpost, responseHandler);
    }

}
