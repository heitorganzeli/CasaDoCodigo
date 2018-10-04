package br.com.heitorganzeli.casadocodigo.service

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import android.util.Log
import br.com.heitorganzeli.casadocodigo.R
import br.com.heitorganzeli.casadocodigo.activity.CarrinhoActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MostraNotificacao: FirebaseMessagingService() {
    @SuppressLint("ServiceCast")
    override fun onMessageReceived(message: RemoteMessage?) {

        Log.e("message", "received")
        val data = message!!.data
        val mensagemTexto = data.get("message")

        val pendingIntent = PendingIntent.getActivity(
                applicationContext,
                123,
                Intent(applicationContext, CarrinhoActivity::class.java),
                PendingIntent.FLAG_CANCEL_CURRENT
        )

        val notification = NotificationCompat.Builder(applicationContext)
                .setSmallIcon(R.drawable.casadocodigo)
                .setContentTitle("Nova mensagem")
                .setContentText(mensagemTexto)
                .setColor(Color.WHITE)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

        val manager: NotificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(123, notification)
    }
}