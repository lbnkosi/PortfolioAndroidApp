package za.co.lbnkosi.portfolio.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import za.co.lbnkosi.portfolio.data.entity.DynamicContentEntity
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity
import za.co.lbnkosi.portfolio.data.typeconverters.DynamicContentTypeConverter
import za.co.lbnkosi.portfolio.data.typeconverters.PortfolioTypeConverter

@Database(entities = [PortfolioEntity::class, DynamicContentEntity::class], version = 2, exportSchema = false)
@TypeConverters(PortfolioTypeConverter::class, DynamicContentTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun portfolioDao(): PortfolioDao

    abstract fun dynamicContentDao(): DynamicContentDao

    companion object {
        const val DATABASE_NAME: String = "portfoliodb"
    }

}