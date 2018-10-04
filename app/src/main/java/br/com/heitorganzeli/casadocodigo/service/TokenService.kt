package br.com.heitorganzeli.casadocodigo.service

import com.google.firebase.iid.FirebaseInstanceId
import org.greenrobot.eventbus.EventBus

class TokenService {

    fun fetchContent() {
        val instance = FirebaseInstanceId.getInstance()
        instance.instanceId.addOnCompleteListener {
            EventBus.getDefault().post(it.result)
        }
    }
}