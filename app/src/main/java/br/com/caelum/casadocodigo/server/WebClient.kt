package br.com.caelum.casadocodigo.server

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory
import br.com.caelum.casadocodigo.delegate.LivroDelegate
import br.com.caelum.casadocodigo.modelo.Livro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.RuntimeException

class WebClient(val delegate: LivroDelegate) {
    companion object {
        @JvmField val SERVER_URL = "http://cdcmob.herokuapp.com/"
    }
    fun getLivros() {
        val client = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(LivroServiceConverterFactory())
                .build()

        val service = client.create(LivroService::class.java)

        val call = service.listaLivros()

        call.enqueue(object : Callback<List<Livro>> {
            override fun onFailure(call: Call<List<Livro>>?, t: Throwable?) {
                if (t != null) {
                    delegate.lidaComErro(t)
                } else {
                    delegate.lidaComErro(RuntimeException("response null"))
                }
            }

            override fun onResponse(call: Call<List<Livro>>?, response: Response<List<Livro>>?) {
                if (response != null) {
                    delegate.lidaComSucesso(response.body())
                } else {
                    delegate.lidaComErro(RuntimeException("response null"))
                }
            }

        })
    }

}