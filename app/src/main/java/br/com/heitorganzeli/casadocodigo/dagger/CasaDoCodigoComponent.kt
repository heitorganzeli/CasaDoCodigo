package br.com.heitorganzeli.casadocodigo.dagger

import br.com.heitorganzeli.casadocodigo.activity.CarrinhoActivity
import br.com.heitorganzeli.casadocodigo.fragment.DetalhesLivroFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CasaDoCodigoModule::class])
@Singleton
interface CasaDoCodigoComponent {
    fun inject(fragment: DetalhesLivroFragment)
    fun inject (activity: CarrinhoActivity)
}