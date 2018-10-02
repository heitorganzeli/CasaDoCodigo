package br.com.caelum.casadocodigo.modelo

import java.io.Serializable
import java.util.*

class Carrinho : Serializable {

    private val itens = ArrayList<Item>()

    fun adiciona(item: Item) {
        itens.add(item)
    }

    fun remove(item: Item) {
        itens.remove(item)
    }

    fun limpa() {
        itens.clear()
    }

    fun getItens(): List<Item> {
        return Collections.unmodifiableList(itens)
    }

}
