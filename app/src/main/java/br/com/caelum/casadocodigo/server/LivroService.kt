package br.com.caelum.casadocodigo.server

import br.com.caelum.casadocodigo.modelo.Livro
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LivroService {
    @GET("listarLivros")
    fun listaLivros(
            @Query("indicePrimeiroLivro") indicePrimeiroLivro: Int,
            @Query("qtdLivros") qtdLivros: Int
    ): Call<List<Livro>>
}