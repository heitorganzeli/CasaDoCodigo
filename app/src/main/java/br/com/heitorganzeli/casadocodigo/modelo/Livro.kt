package br.com.heitorganzeli.casadocodigo.modelo

import java.io.Serializable

class Livro : Serializable {

    var id: Long = 0
    var nome: String? = null
    var descricao: String? = null
    var numPaginas: Int = 0
    var dataPublicacao: String? = null
    var isbn: String? = null
    var valorFisico: Double = 0.toDouble()
    var valorVirtual: Double = 0.toDouble()
    var valorDoisJuntos: Double = 0.toDouble()
    var urlFoto: String? = null
    var autores: List<Autor> = ArrayList()

    constructor()

    constructor(nome: String, descricao: String, autores: List<Autor>) {
        this.nome = nome
        this.descricao = descricao
        this.autores = autores
    }
}
