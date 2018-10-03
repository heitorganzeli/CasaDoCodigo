package br.com.heitorganzeli.casadocodigo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.heitorganzeli.casadocodigo.R
import br.com.heitorganzeli.casadocodigo.adapter.ListLivroAdapter
import br.com.heitorganzeli.casadocodigo.modelo.Livro
import br.com.heitorganzeli.casadocodigo.server.WebClient
import butterknife.BindView
import butterknife.ButterKnife
import com.mugen.Mugen
import com.mugen.MugenCallbacks
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

    private var carregando: Boolean = false
    private lateinit var livros: ArrayList<Livro>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_livro, container, false)

        ButterKnife.bind(this, view)


        livros = arguments!!.getSerializable("livros") as ArrayList<Livro>

        lista.layoutManager = LinearLayoutManager(context)
        lista.adapter = ListLivroAdapter(livros)

        Mugen.with(lista, object : MugenCallbacks {
            override fun isLoading(): Boolean {
                return carregando;
            }

            override fun hasLoadedAllItems(): Boolean {
                return false;
            }

            override fun onLoadMore() {
                WebClient().getLivros(livros.size, 5)
                carregando = true

                Snackbar.make(lista, "Carregando mais livros", Snackbar.LENGTH_SHORT).show()
            }
        }).start()

        return view
    }

    @SuppressLint("RestrictedApi")
    override fun onResume() {
        super.onResume()
        var activity: AppCompatActivity = activity as AppCompatActivity
        activity.supportActionBar!!.title = "Catálogo"
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    fun adicionaLivros(livros: List<Livro>) {
        carregando=false;
        this.livros.addAll(livros)
        lista.adapter!!.notifyDataSetChanged()
    }


}