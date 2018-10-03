package br.com.heitorganzeli.casadocodigo.dagger

import br.com.heitorganzeli.casadocodigo.modelo.Carrinho
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