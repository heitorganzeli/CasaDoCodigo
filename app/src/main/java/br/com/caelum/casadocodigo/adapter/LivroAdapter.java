package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LivroAdapter extends RecyclerView.Adapter {
    private final List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int tipoDeLayout = R.layout.item_livro_par;
        if (i % 2 != 0) {
            tipoDeLayout = R.layout.item_livro_impar;
        }

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(tipoDeLayout, viewGroup, false);

        return new MyViewHoler(v);
    }

    class MyViewHoler extends ViewHolder {

        @BindView(R.id.item_livro_nome)
        TextView nome;
        @BindView(R.id.item_livro_foto)
        ImageView foto;

        public MyViewHoler(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyViewHoler myViewHoder = (MyViewHoler) viewHolder;
        Livro livro = livros.get(i);
        myViewHoder.nome.setText(livro.getNome());
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }
}
