package br.com.caelum.casadocodigo.server

import br.com.caelum.casadocodigo.modelo.Livro
import retrofit2.Call
import retrofit2.http.GET

interface LivroService {
    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    fun listaLivros(): Call<List<Livro>>
}