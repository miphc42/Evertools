package everything.philipchoi;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1id";
    public static final String channel1name = "channel1name";
    public static String title;
    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels(){
        NotificationChannel channel = new NotificationChannel(channel1ID, channel1name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(R.color.colorPrimary);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getManager().createNotificationChannel(channel);
    }
    private NotificationManager manager;
    public NotificationManager getManager(){
        if(manager==null) {
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return manager;
    }
    public NotificationCompat.Builder getChannelNotification(){
        Alarm alarm = new Alarm();
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setContentTitle(title)
                .setContentText("Message")
                .setSmallIcon(R.drawable.ic_compass);
    }
}
