package br.com.heitorganzeli.casadocodigo.modelo

import java.io.Serializable
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
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

    fun calculaValor(): BigDecimal {
        var valorFinal = BigDecimal("0.00")

        for (item in itens) {
            valorFinal = valorFinal.add(BigDecimal(item.valor))
        }

        valorFinal = valorFinal.setScale(2, BigDecimal.ROUND_HALF_UP)
        return valorFinal
    }

    fun getValor(): String {
        val format: NumberFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))

        return format.format(calculaValor())
    }

}
