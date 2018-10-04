package br.com.heitorganzeli.casadocodigo.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.heitorganzeli.casadocodigo.R
import br.com.heitorganzeli.casadocodigo.fragment.DetalhesLivroFragment
import br.com.heitorganzeli.casadocodigo.fragment.ListaLivrosFragment
import br.com.heitorganzeli.casadocodigo.fragment.LoadingFragment
import br.com.heitorganzeli.casadocodigo.fragment.ServiceErroFragment
import br.com.heitorganzeli.casadocodigo.modelo.Livro
import br.com.heitorganzeli.casadocodigo.server.WebClient
import br.com.heitorganzeli.casadocodigo.service.TokenService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.messaging.RemoteMessage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LivroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TokenService().fetchContent()

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.livros_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.livros_menu_carrinho -> {
                val intent = Intent(this, CarrinhoActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.livros_menu_sair -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recebeMensagem(message: RemoteMessage) {
        Log.e("post", "eventbus")
        Toast.makeText(this, "mensagem recebida: ${message.notification}", Toast.LENGTH_LONG).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun recebeToken(tokenResult: InstanceIdResult) {
        Log.e("id", tokenResult.token)
        Log.e("email", FirebaseAuth.getInstance().currentUser!!.email)
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