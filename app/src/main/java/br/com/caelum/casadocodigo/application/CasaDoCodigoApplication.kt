package br.com.caelum.casadocodigo.application

import android.app.Application
import br.com.caelum.casadocodigo.dagger.CasaDoCodigoComponent
import br.com.caelum.casadocodigo.dagger.DaggerCasaDoCodigoComponent

class CasaDoCodigoApplication: Application() {

    companion object {
        lateinit var instance: CasaDoCodigoApplication
            private set
    }

    lateinit var component: CasaDoCodigoComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerCasaDoCodigoComponent.builder().build();
    }

}