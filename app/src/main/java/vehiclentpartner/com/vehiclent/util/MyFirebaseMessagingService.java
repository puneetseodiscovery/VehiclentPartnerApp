package vehiclentpartner.com.vehiclent.util;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vehiclentpartner.com.vehiclent.R;
import vehiclentpartner.com.vehiclent.upcommingDetails.BeanUpcomingDetails;
import vehiclentpartner.com.vehiclent.upcommingDetails.UpCommingDetailsActivity;

import static android.content.ContentValues.TAG;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String device_token;

    public MyFirebaseMessagingService() {

        Log.d("++++++++++", "++ working ++");
    }


    @Override
    public void onCreate() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        device_token = task.getResult().getToken();
                        Log.e("firebase_token", device_token);

                        SavePref.setDeviceToken(getApplicationContext(), "device_token", device_token);

                        SavePref.getDeviceToken(getApplicationContext(), "device_token");
                        Log.e("get_deviceToken", SavePref.getDeviceToken(getApplicationContext(), "device_token"));

                    }
                });
    }

    @Override
    public void onNewToken(String token) {

        Log.e("onNewToken", "Working");
        this.device_token = token;
        SavePref.setDeviceToken(getApplicationContext(), "device_token++++", token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("Data", "" + remoteMessage.getData().get("user_id"));
        Log.e("Data", "" + remoteMessage.getData().get("service_id"));
        Log.e("Data", "" + remoteMessage.getData().get("Latitude"));
        Log.e("Data", "" + remoteMessage.getData().get("Longitude"));
        Log.e("Data", "" + remoteMessage.getData().get("title"));
        Log.e("Data", "" + remoteMessage.getData().get("Partner_Id"));
        Log.e("Data", "" + remoteMessage.getData().get("text"));
        Log.e("Data", "" + remoteMessage.getData().get("first_name"));
        Log.e("Data", "" + remoteMessage.getData().get("last_name"));
        Log.e("Data", "" + remoteMessage.getData().get("email"));
        Log.e("Data", "" + remoteMessage.getData().get("service_name"));
        Log.e("Data", "" + remoteMessage.getData().get("service_price"));
        Log.e("Data", "" + remoteMessage.getData().get("phone_number"));
        Log.e("Data", "" + remoteMessage.getData().get("profile_pic"));
        Log.e("Data", "" + remoteMessage.getData().get("address"));

        Log.d("+++++++++++", "++ remote data ++" + remoteMessage.getData().toString());

        //sendNotification(remoteMessage.getData().toString(), remoteMessage.getData().get("title"), remoteMessage.getData().get("text"));
        sendNotification(remoteMessage);
    }

    //private void sendNotification(String data, String title, String text) {
    private void sendNotification(RemoteMessage remoteMessage) {

        //   Log.e("sendNotification", "Working " + data);

        Intent intent = new Intent(getApplicationContext(), UpCommingDetailsActivity.class);

        BeanUpcomingDetails beanUpcomingDetails = new BeanUpcomingDetails();
        beanUpcomingDetails.setUser_id(remoteMessage.getData().get("user_id"));
        beanUpcomingDetails.setService_id(remoteMessage.getData().get("service_id"));
        beanUpcomingDetails.setLatitude(remoteMessage.getData().get("Latitude"));
        beanUpcomingDetails.setLongitude(remoteMessage.getData().get("Longitude"));
        beanUpcomingDetails.setTitle(remoteMessage.getData().get("title"));
        beanUpcomingDetails.setPartner_Id(remoteMessage.getData().get("Partner_Id"));
        beanUpcomingDetails.setText(remoteMessage.getData().get("text"));
        beanUpcomingDetails.setEmail(remoteMessage.getData().get("email"));
        beanUpcomingDetails.setFirst_name(remoteMessage.getData().get("first_name"));
        beanUpcomingDetails.setLast_name(remoteMessage.getData().get("last_name"));
        beanUpcomingDetails.setEmail(remoteMessage.getData().get("email"));
        beanUpcomingDetails.setService_name(remoteMessage.getData().get("service_name"));
        beanUpcomingDetails.setPhone_number(remoteMessage.getData().get("phone_number"));
        beanUpcomingDetails.setProfile_pic(remoteMessage.getData().get("profile_pic"));
        beanUpcomingDetails.setAddress(remoteMessage.getData().get("address"));
        intent.putExtra("user_date_send", beanUpcomingDetails);

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);
            //Configure Notification Channel
            notificationChannel.setDescription(remoteMessage.getData().get("text"));
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setAutoCancel(true)
                //.setSound(defaultSound)
                .setContentText(remoteMessage.getData().get("text"))
                .setContentIntent(pendingIntent)
                //.setStyle(style)
                //.setLargeIcon(bitmap)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX)
                .addAction(R.mipmap.ic_launcher_round, "Dismiss", pendingIntent);

        notificationManager.notify(1, notificationBuilder.build());
    }
}
