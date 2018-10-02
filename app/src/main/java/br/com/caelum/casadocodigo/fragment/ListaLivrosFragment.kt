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
import kotlin.collections.ArrayList

class ListaLivrosFragment: Fragment(){

    @BindView(R.id.lista_livros)
    internal lateinit var lista: RecyclerView

    companion object {
        @JvmStatic fun com(livros: List<Livro>): ListaLivrosFragment {
            val comLivros = ListaLivrosFragment()

            val livrosBundle = Bundle()
            livrosBundle.putSerializable("livros", ArrayList(livros))
            comLivros.arguments = livrosBundle

            return comLivros
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_livro, container, false)

        ButterKnife.bind(this, view)


        val livros = arguments!!.getSerializable("livros") as ArrayList<Livro>

        lista.layoutManager = LinearLayoutManager(context)
        lista.adapter = ListLivroAdapter(livros)

        return view
    }




}