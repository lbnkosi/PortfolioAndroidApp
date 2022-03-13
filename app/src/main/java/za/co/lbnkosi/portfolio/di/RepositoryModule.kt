package za.co.lbnkosi.portfolio.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import za.co.lbnkosi.portfolio.data.db.DynamicContentDao
import za.co.lbnkosi.portfolio.data.db.PortfolioDao
import za.co.lbnkosi.portfolio.data.repository.FirebaseRepository
import za.co.lbnkosi.portfolio.data.repository.PortfolioRepository
import za.co.lbnkosi.portfolio.data.service.FCMService
import za.co.lbnkosi.portfolio.data.service.PortfolioApiService
import za.co.lbnkosi.portfolio.data.source.local.dynamicContent.LocalDynamicContentDataSource
import za.co.lbnkosi.portfolio.data.source.local.portfolio.LocalPortfolioDataSource
import za.co.lbnkosi.portfolio.data.source.remote.firebase.FirebaseDataSource
import za.co.lbnkosi.portfolio.data.source.remote.portfolio.PortfolioDataSource
import za.co.lbnkosi.portfolio.domain.usecase.FirebaseUseCase
import za.co.lbnkosi.portfolio.domain.usecase.PortfolioUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesPortfolioUseCase(portfolioRepository: PortfolioRepository): PortfolioUseCase = PortfolioUseCase(portfolioRepository)

    @Provides
    fun providesPortfolioRemoteDataSource(portfolioLocalDataSource: LocalPortfolioDataSource, contentLocalDataSource: LocalDynamicContentDataSource, portfolioApiService: PortfolioApiService): PortfolioDataSource = PortfolioDataSource(portfolioApiService, portfolioLocalDataSource, contentLocalDataSource, )

    @Provides
    fun providesPortfolioLocalDataSource(portfolioDao: PortfolioDao): LocalPortfolioDataSource = LocalPortfolioDataSource(portfolioDao)

    @Provides
    fun providesDynamicContentLocalDataSource(dynamicContentDao: DynamicContentDao): LocalDynamicContentDataSource = LocalDynamicContentDataSource(dynamicContentDao)

    @Singleton
    @Provides
    fun providesFirebaseUseCase(firebaseRepository: FirebaseRepository): FirebaseUseCase = FirebaseUseCase(firebaseRepository)

    @Provides
    fun providesFirebaseDataSource(firebaseService: FCMService): FirebaseDataSource = FirebaseDataSource(firebaseService)

}