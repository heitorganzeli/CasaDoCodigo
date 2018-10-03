package br.com.caelum.casadocodigo.dagger

import br.com.caelum.casadocodigo.activity.CarrinhoActivity
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [CasaDoCodigoModule::class])
@Singleton
interface CasaDoCodigoComponent {
    fun inject(fragment: DetalhesLivroFragment)
    fun inject (activity: CarrinhoActivity)
}