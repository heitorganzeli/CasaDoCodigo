package br.com.caelum.casadocodigo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.caelum.casadocodigo.R
import br.com.caelum.casadocodigo.adapter.ListLivroAdapter
import br.com.caelum.casadocodigo.modelo.Autor
import br.com.caelum.casadocodigo.modelo.Livro
import butterknife.BindView
import butterknife.ButterKnife
import java.util.*

class ListaLivrosFragment: Fragment(){

    @BindView(R.id.lista_livros)
    internal lateinit var lista: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_livro, container, false)

        ButterKnife.bind(this, view)

        val livros = ArrayList<Livro>()
        for (i in 0..19) {
            val autor = Autor()
            autor.nome = "Autor $i"
            val livro = Livro("Livro $i", "Descrição $i", Arrays.asList(autor))
            livros.add(livro)
        }

        lista.layoutManager = LinearLayoutManager(context)
        lista.adapter = ListLivroAdapter(livros)

        return view
    }


}