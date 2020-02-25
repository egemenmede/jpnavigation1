package com.etiya.jpnavigation1


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_blank_fragment2.*

/**
 * A simple [Fragment] subclass.
 */
class BlankFragment4 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank_fragment4, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvMessage.text = "Diğer sayfadan gelen veri: " + arguments?.getString("nameField")

        val notificationButton = view?.findViewById<Button>(R.id.btnSendNotificationButton)
        notificationButton?.setOnClickListener {
            val args = Bundle()
            args.putString("nameField", "Egemen")

            val deeplink = findNavController().createDeepLink()
                .setDestination(R.id.blankFragment4)
                .setArguments(args)
                .createPendingIntent()

            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                    "deeplink", "Navigation ile Deep Link", NotificationManager.IMPORTANCE_HIGH)
                )
            }

            val builder = NotificationCompat.Builder(
                context!!, "deeplink")
                .setContentTitle("Navigation Component")
                .setContentText("Bildirimden Android uygulamasına Deep link için tıklayın.")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentIntent(deeplink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }

}
