package br.com.caelum.casadocodigo.dagger

import br.com.caelum.casadocodigo.modelo.Carrinho
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CasaDoCodigoModule {
    @Provides
    @Singleton
    fun getCarrinho(): Carrinho {
        return Carrinho()
    }
}