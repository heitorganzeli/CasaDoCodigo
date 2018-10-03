package br.com.heitorganzeli.casadocodigo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.heitorganzeli.casadocodigo.R
import butterknife.BindView
import butterknife.ButterKnife

class ServiceErroFragment: Fragment() {
    @BindView(R.id.service_error_message)
    lateinit var errorMessage: TextView

    companion object {
        @JvmStatic fun com(erro: Throwable): ServiceErroFragment {
            val comErro = ServiceErroFragment()

            val livrosBundle = Bundle()
            livrosBundle.putSerializable("erro", erro.message)
            comErro.arguments = livrosBundle

            return comErro
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_service_error, container, false)

        ButterKnife.bind(this,view)


        errorMessage.text = arguments!!.getString("erro")

        return view
    }
}