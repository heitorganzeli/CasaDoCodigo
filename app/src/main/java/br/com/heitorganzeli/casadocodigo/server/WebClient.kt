package br.com.heitorganzeli.casadocodigo.server

import br.com.heitorganzeli.casadocodigo.converter.LivroServiceConverterFactory
import br.com.heitorganzeli.casadocodigo.modelo.Livro
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WebClient {
    companion object {
        const val SERVER_URL = "http://cdcmob.herokuapp.com/"
    }

    fun getLivros(indicePrimeiroLivro: Int, qtdLivros: Int) {
        val client = Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(LivroServiceConverterFactory())
                .build()

        val service = client.create(LivroService::class.java)

        val call = service.listaLivros(indicePrimeiroLivro, qtdLivros)

        call.enqueue(object : Callback<List<Livro>> {
            override fun onFailure(call: Call<List<Livro>>?, t: Throwable?) {
                EventBus.getDefault().post(t ?: Exception("response null"))
            }

            override fun onResponse(call: Call<List<Livro>>?, response: Response<List<Livro>>?) {
                EventBus.getDefault().post(if (response != null) response.body() else Exception("response null"))
            }

        })
    }

}