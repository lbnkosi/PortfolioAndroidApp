package za.co.lbnkosi.portfolio.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import za.co.lbnkosi.portfolio.data.typeconverters.DynamicContentTypeConverter
import za.co.lbnkosi.portfolio.domain.model.DynamicContent

@Entity(tableName = "DynamicContentEntity")
class DynamicContentEntity(

    @PrimaryKey(autoGenerate = true) var key: Int = 0,

    @ColumnInfo(name = "dynamicContent")
    @TypeConverters(DynamicContentTypeConverter::class)
    var dynamicContent: DynamicContent? = DynamicContent()

)