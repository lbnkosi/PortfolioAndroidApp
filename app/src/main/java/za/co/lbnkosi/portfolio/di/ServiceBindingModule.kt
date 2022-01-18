package za.co.lbnkosi.portfolio.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import za.co.lbnkosi.portfolio.data.repository.PortfolioRepository
import za.co.lbnkosi.portfolio.domain.repository.IPortfolioRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceBindingModule {

    @Singleton
    @Binds
    abstract fun bindPortfolioRepository(portfolioRepository: IPortfolioRepository): IPortfolioRepository

}