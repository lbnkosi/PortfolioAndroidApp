package za.co.lbnkosi.portfolio.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import za.co.lbnkosi.portfolio.data.db.AppDatabase
import za.co.lbnkosi.portfolio.data.db.PortfolioDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun providesWeatherDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesWeatherDao(database: AppDatabase): PortfolioDao {
        return database.portfolioDao()
    }

}