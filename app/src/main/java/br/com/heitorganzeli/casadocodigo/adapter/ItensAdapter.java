package br.com.heitorganzeli.casadocodigo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.com.heitorganzeli.casadocodigo.R;
import br.com.heitorganzeli.casadocodigo.modelo.Item;
import br.com.heitorganzeli.casadocodigo.modelo.TipoDeCompra;

public class ItensAdapter extends RecyclerView.Adapter {

    private List<Item> items;
    private Context context;
    private NumberFormat formater = DecimalFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public ItensAdapter(List<Item> items, Context context) {

        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrinho, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ViewHolder holder = (ViewHolder) viewHolder;

        Item item = items.get(position);

        String valorComprado = retornaValorCompradoDo(item);

        holder.nomeItem.setText(item.getLivro().getNome());
        holder.valorComprado.setText(valorComprado);

        Picasso.get().load(item.getLivro().getUrlFoto()).fit().into(((ViewHolder) viewHolder).fotoLivro);
    }

    private String retornaValorCompradoDo(Item item) {

        TipoDeCompra tipoDeCompra = item.getTipoDeCompra();

        switch (tipoDeCompra) {
            case FISICO:
                return formater.format(item.getLivro().getValorFisico());

            case VIRTUAL:
                return formater.format(item.getLivro().getValorVirtual());

            default:
                return formater.format(item.getLivro().getValorDoisJuntos());

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView fotoLivro;
        TextView nomeItem;
        TextView valorComprado;

        public ViewHolder(View itemView) {
            super(itemView);

            fotoLivro = (ImageView) itemView.findViewById(R.id.imagem_item_comprado);
            nomeItem = (TextView) itemView.findViewById(R.id.nome_item_comprado);
            valorComprado = (TextView) itemView.findViewById(R.id.valor_pago_item_comprado);
        }
    }
}
