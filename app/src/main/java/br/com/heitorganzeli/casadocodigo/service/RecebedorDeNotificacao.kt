package br.com.heitorganzeli.casadocodigo.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.greenrobot.eventbus.EventBus

class RecebedorDeNotificacao: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage?) {
        Log.e("message", "received")
        if (message == null) {
            Log.e("error", "invalid notification")
        }


        EventBus.getDefault().post(message)
        super.onMessageReceived(message)
    }
}