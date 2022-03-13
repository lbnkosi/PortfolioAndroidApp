package za.co.lbnkosi.portfolio.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import za.co.lbnkosi.portfolio.data.typeconverters.PortfolioTypeConverter
import za.co.lbnkosi.portfolio.domain.model.Portfolio

@Entity(tableName = "PortfolioEntity")
class PortfolioEntity(

    @PrimaryKey(autoGenerate = true) var key: Int = 0,

    @ColumnInfo(name = "portfolio")
    @TypeConverters(PortfolioTypeConverter::class)
    var portfolio: Portfolio = Portfolio()
)