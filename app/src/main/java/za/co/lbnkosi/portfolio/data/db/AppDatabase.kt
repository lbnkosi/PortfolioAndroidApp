package za.co.lbnkosi.portfolio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity
import za.co.lbnkosi.portfolio.data.typeconverters.PortfolioTypeConverter

@Database(entities = [PortfolioEntity::class], version = 1, exportSchema = false)
@TypeConverters(PortfolioTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun portfolioDao(): PortfolioDao

    companion object {
        const val DATABASE_NAME: String = "portfoliodb"
    }

}