package za.co.lbnkosi.portfolio.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import za.co.lbnkosi.portfolio.data.repository.PortfolioRepository
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import za.co.lbnkosi.portfolio.data.source.PortfolioDataSource
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesPortfolioUseCase(portfolioRepository: PortfolioRepository): PortfolioUseCase = PortfolioUseCase(portfolioRepository)

    @Provides
    fun providesPortfolioDataSource(portfolioApiService: PortfolioApiService): PortfolioDataSource = PortfolioDataSource(portfolioApiService)

}