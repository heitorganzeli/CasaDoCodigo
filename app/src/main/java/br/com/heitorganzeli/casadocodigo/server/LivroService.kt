package br.com.heitorganzeli.casadocodigo.server

import br.com.heitorganzeli.casadocodigo.modelo.Livro
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