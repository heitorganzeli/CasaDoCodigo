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

class LivroActivity : AppCompatActivity(), LivroDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebClient(this).getLivros()
    }

    override fun lidaCom(livro: Livro) {
        exibe(DetalhesLivroFragment.com(livro), true)
    }

    override fun lidaComErro(erro: Throwable) {
        Toast.makeText(this, "Não foi possivel carregar os livros: ${erro.message}", Toast.LENGTH_SHORT).show()
    }

    override fun lidaComSucesso(livros: List<Livro>) {
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