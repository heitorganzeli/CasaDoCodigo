package br.com.caelum.casadocodigo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.caelum.casadocodigo.R
import br.com.caelum.casadocodigo.modelo.Livro
import butterknife.BindView
import butterknife.ButterKnife
import com.squareup.picasso.Picasso

class DetalhesLivroFragment : Fragment() {

    @BindView(R.id.detalhes_livro_nome)
    internal lateinit var nome: TextView
    @BindView(R.id.detalhes_livro_isbn)
    internal lateinit var isbn: TextView
    @BindView(R.id.detalhes_livro_foto)
    internal lateinit var foto: ImageView
    @BindView(R.id.detalhes_livro_autores)
    internal lateinit var autores: TextView
    @BindView(R.id.detalhes_livro_descricao)
    internal lateinit var descricao: TextView
    @BindView(R.id.detalhes_livro_num_paginas)
    internal lateinit var numPaginas: TextView
    @BindView(R.id.detalhes_livro_data_publicacao)
    internal lateinit var dataPublicacao: TextView

    @BindView(R.id.detalhes_livro_comprar_fisico)
    internal lateinit var botaoComprarFisico: Button
    @BindView(R.id.detalhes_livro_comprar_ambos)
    internal lateinit var botaoComprarAmbos: Button
    @BindView(R.id.detalhes_livro_comprar_ebook)
    internal lateinit var botaoComprarEbook: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false)

        ButterKnife.bind(this, view)

        val livro = arguments!!.getSerializable("livro") as Livro

        populaCamposCom(livro)

        return view
    }

    private fun populaCamposCom(livro: Livro) {
        nome.text = livro.nome
        descricao.text = livro.descricao

        var listaAutores = ""

        for (autor in livro.autores) {
            if (!listaAutores.isEmpty()) {
                listaAutores += ", "
            }
            listaAutores += autor.nome
        }

        autores.text = listaAutores

        numPaginas.text = livro.numPaginas.toString()
        isbn.text = livro.isbn
        dataPublicacao.text = livro.dataPublicacao

        val textoComprarFisico = String.format("Comprar Livro Físico - R$ %.2f", livro.valorFisico)
        botaoComprarFisico.text = textoComprarFisico

        val textoComprarEbook = String.format("Comprar Ebook - R$ %.2f", livro.valorVirtual)
        botaoComprarEbook.text = textoComprarEbook

        val textoComprarAmbos = String.format("Comprar ambos - R$ %.2f", livro.valorDoisJuntos)
        botaoComprarAmbos.text = textoComprarAmbos

        Picasso.get().load(livro.urlFoto).into(foto)
    }

    companion object {
        fun com(livro: Livro): DetalhesLivroFragment {
            val detalhes = DetalhesLivroFragment()

            val livroBundle = Bundle()
            livroBundle.putSerializable("livro", livro)
            detalhes.arguments = livroBundle

            return detalhes
        }
    }
}
