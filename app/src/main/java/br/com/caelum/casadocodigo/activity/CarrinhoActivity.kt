package br.com.caelum.casadocodigo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.TextView
import br.com.caelum.casadocodigo.R
import br.com.caelum.casadocodigo.adapter.ItensAdapter
import br.com.caelum.casadocodigo.application.CasaDoCodigoApplication
import br.com.caelum.casadocodigo.modelo.Carrinho
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

class CarrinhoActivity: AppCompatActivity() {
    @BindView(R.id.lista_itens_carrinho)
    lateinit var itensCarrinho: RecyclerView
    @BindView(R.id.valor_carrinho)
    lateinit var valor: TextView

    @Inject
    lateinit var carrinho: Carrinho


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrinho)

        CasaDoCodigoApplication.instance.component.inject(this)
        ButterKnife.bind(this)


        itensCarrinho.adapter = ItensAdapter(carrinho.getItens(), this)
        itensCarrinho.layoutManager = LinearLayoutManager(this)

        valor.text = "R$ ${carrinho.calculaValor()}"
    }
}
