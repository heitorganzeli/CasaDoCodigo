package br.com.heitorganzeli.casadocodigo.modelo

import java.io.Serializable

data class Autor(
        var id: Long = 0,
        var nome: String = "",
        var biografia: String = "",
        var urlFoto: String = ""
) : Serializable
