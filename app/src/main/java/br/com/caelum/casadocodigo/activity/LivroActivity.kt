package br.com.caelum.casadocodigo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.caelum.casadocodigo.R
import br.com.caelum.casadocodigo.delegate.LivroDelegate
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment
import br.com.caelum.casadocodigo.modelo.Livro
import br.com.caelum.casadocodigo.server.WebClient
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LivroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            WebClient().getLivros()
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun lidaCom(livro: Livro) {
        exibe(DetalhesLivroFragment.com(livro), true)
    }

    @Subscribe
    fun lidaComErro(erro: Throwable) {
        Toast.makeText(this, "Não foi possivel carregar os livros: ${erro.message}", Toast.LENGTH_SHORT).show()
    }

    @Subscribe
    fun lidaComSucesso(livros: List<Livro>) {
        exibe(ListaLivrosFragment.com(livros), false)
    }

    private fun exibe(fragment: Fragment, empilha: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_principal, fragment)

        if (empilha) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }
}