package br.com.caelum.casadocodigo.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.caelum.casadocodigo.R
import br.com.caelum.casadocodigo.delegate.LivroDelegate
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment
import br.com.caelum.casadocodigo.modelo.Livro

class LivroActivity : AppCompatActivity(), LivroDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exibe(ListaLivrosFragment(), false)
    }

    override fun lidaCom(livro: Livro) {
        exibe(DetalhesLivroFragment.com(livro), true)
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