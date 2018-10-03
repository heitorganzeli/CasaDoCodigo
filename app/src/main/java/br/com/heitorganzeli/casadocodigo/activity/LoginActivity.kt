package br.com.heitorganzeli.casadocodigo.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import br.com.heitorganzeli.casadocodigo.R
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth

    @BindView(R.id.login_email)
    lateinit var campoEmail: EditText
    @BindView(R.id.login_senha)
    lateinit var campoSenha: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()

        var currentUser = mAuth.currentUser
        if (currentUser != null) {
            vaiParaListaLivros()
        }
    }

    private fun vaiParaListaLivros() {
        val intent = Intent(this, LivroActivity::class.java)
        startActivity(intent)
        finish()
    }

    @OnClick(R.id.login_novo)
    fun novoUsuario(botao: Button) {
        var email = campoEmail.text.toString().trim()
        var senha = campoSenha.text.toString().trim()

        var textoOriginal = desabilitaBotao(botao)


        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(trataRespostaFirebase(botao, textoOriginal))
    }

    private fun desabilitaBotao(botao: Button): String {
        val original = botao.text.toString()
        botao.text = "carregando"
        botao.isClickable = false
        botao.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        return original
    }

    @OnClick(R.id.login_logar)
    fun logar(botao: Button) {
        var email = campoEmail.text.toString().trim()
        var senha = campoSenha.text.toString().trim()

        var textoOriginal = desabilitaBotao(botao)


        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(trataRespostaFirebase(botao, textoOriginal))
    }

    private fun trataRespostaFirebase(botao: Button, textoOriginal: String): OnCompleteListener<AuthResult> {
        return object: OnCompleteListener<AuthResult> {
            override fun onComplete(task: Task<AuthResult>) {
                abilitaBotao(botao, textoOriginal)
                if (task.isSuccessful) {
                    Log.d("Login", "created user")
                    vaiParaListaLivros()
                } else {
                    Log.w("Login", "failed to login")
                    exibeErro(task.exception!!)

                }

            }

        }
    }

    private fun abilitaBotao(botao: Button, textoOriginal: String) {
        botao.text = textoOriginal
        botao.isClickable = true
        botao.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
    }

    private fun exibeErro(exception: Exception) {
        AlertDialog.Builder(this)
                .setMessage(exception.message)
                .setIcon(android.R.drawable.stat_sys_warning)
                .create()
                .show()
    }
}