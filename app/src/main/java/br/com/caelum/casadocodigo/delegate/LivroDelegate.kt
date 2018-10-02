package br.com.caelum.casadocodigo.delegate

import br.com.caelum.casadocodigo.modelo.Livro

interface LivroDelegate {
    fun lidaCom(livro: Livro)
}