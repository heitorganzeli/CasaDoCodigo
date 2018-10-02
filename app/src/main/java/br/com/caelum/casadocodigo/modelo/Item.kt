package br.com.caelum.casadocodigo.modelo

class Item(val livro: Livro, val tipoDeCompra: TipoDeCompra) {

    val valor: Double
        get() {
            when (tipoDeCompra) {
                TipoDeCompra.FISICO -> return livro.valorFisico

                TipoDeCompra.VIRTUAL -> return livro.valorVirtual

                else -> return livro.valorDoisJuntos
            }
        }
}