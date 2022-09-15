package uz.gita.myeventapp.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import uz.gita.myeventapp.MainActivity
import uz.gita.myeventapp.R
import uz.gita.myeventapp.broadcast.MyBroadCastReceiver

class MyService : Service() {
    val CHANNEL = "DEMO"
    var reciever = MyBroadCastReceiver()
    override fun onBind(intent: Intent?): IBinder?  = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val stateName = intent?.extras?.getString("STATE_NAME")
        val stopState = intent?.extras?.getString("STOP")
        val stateValue = intent?.extras?.getBoolean("STATE_VALUE",false)

        stopState?.let {
            if (it == "STOP"){
                stopSelf()
            }
        }

        val actionName = when(stateName){
            "Screen off"->Intent.ACTION_SCREEN_OFF
            "Screen on"->Intent.ACTION_SCREEN_ON
            "Airplane mode"->Intent.ACTION_AIRPLANE_MODE_CHANGED
            "Power connected"->Intent.ACTION_POWER_CONNECTED
            "Power disconnected"->Intent.ACTION_POWER_DISCONNECTED
            "Battery low"->Intent.ACTION_BATTERY_LOW
            "Battery ok"->Intent.ACTION_BATTERY_OKAY
            else ->{Intent.ACTION_SCREEN_ON}
        }
        registerReceiver(reciever, IntentFilter().apply {
            addAction(actionName)
        })

        return START_NOT_STICKY
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    "Events App",
                    CHANNEL,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            channel.enableVibration(false)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("DEMO", CHANNEL, NotificationManager.IMPORTANCE_DEFAULT)
            channel.setSound(null, null)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun startService() {
        val notifyIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setCustomContentView(createRemoteView())
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .build()

        startForeground(1, notification)
        /*val notification = NotificationCompat
            .Builder(this, CHANNEL)
            .setSmallIcon(R.drawable.ic_logo)
            .setContentTitle(resources.getString(R.string.app_name))
            .setContentText("Send SMS gateway is running background")
//            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
//            .setCustomContentView(createNotificationLayout())
            .build()
*/
//        startForeground(1, notification)
    }

    @SuppressLint("RemoteViewLayout")
    private fun createRemoteView(): RemoteViews? {
        val view = RemoteViews(this.packageName, R.layout.remoteview)
        view.setOnClickPendingIntent(R.id.exit, createPendingIntent())
        return view
    }

    /* private fun createNotificationLayout(): RemoteViews {
         val view = RemoteViews(packageName, R.layout.remote_view)
         view.setOnClickPendingIntent(R.id.remoteButtonClose, createPendingIntent())
         return view
     }*/

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(this, MyService::class.java)
        intent.putExtra("STOP", "STOP")
        return PendingIntent.getService(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}