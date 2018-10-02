package br.com.caelum.casadocodigo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.caelum.casadocodigo.R
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment
import br.com.caelum.casadocodigo.fragment.LoadingFragment
import br.com.caelum.casadocodigo.fragment.ServiceErroFragment
import br.com.caelum.casadocodigo.modelo.Livro
import br.com.caelum.casadocodigo.server.WebClient
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class LivroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            WebClient().getLivros(0, 5)
            exibe(LoadingFragment(), false)
        }
    }

    override fun onResume() {
        super.onResume()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                false
            }
            else -> true
        }

    }

    @Subscribe
    fun lidaCom(livro: Livro) {
        exibe(DetalhesLivroFragment.com(livro), true)
    }

    @Subscribe
    fun lidaComErro(erro: Throwable) {
       exibe(ServiceErroFragment.com(erro), false)
    }

    @Subscribe
    fun lidaComSucesso(livros: List<Livro>) {
        var fragment = supportFragmentManager.findFragmentById(R.id.frame_principal)

        when (fragment) {
            is ListaLivrosFragment -> fragment.adicionaLivros(livros)
            else -> exibe(ListaLivrosFragment.com(livros), false)
        }
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