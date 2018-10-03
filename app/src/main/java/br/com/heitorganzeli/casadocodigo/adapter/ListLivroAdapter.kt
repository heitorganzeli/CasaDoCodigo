package br.com.heitorganzeli.casadocodigo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.heitorganzeli.casadocodigo.R
import br.com.heitorganzeli.casadocodigo.modelo.Livro
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.squareup.picasso.Picasso
import org.greenrobot.eventbus.EventBus

class ListLivroAdapter (val livros: List<Livro>): RecyclerView.Adapter<ListLivroAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        var tipoDeLayout = R.layout.item_livro_par
        if (i % 2 != 0) {
            tipoDeLayout = R.layout.item_livro_impar
        }

        val v = LayoutInflater.from(viewGroup.getContext()).inflate(tipoDeLayout, viewGroup, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val livro = livros[i]
        viewHolder.nome.setText(livro.nome)

        Picasso.get().load(livro.urlFoto).fit().into(viewHolder.foto)
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    inner class  ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        @BindView(R.id.item_livro_nome)
        lateinit var nome: TextView
        @BindView(R.id.item_livro_foto)
        lateinit var foto: ImageView

        init {
            ButterKnife.bind(this, view)
        }

        @OnClick
        fun onItemClick() {
            val livro = livros.get(adapterPosition)
            EventBus.getDefault().post(livro)
        }
    }
}