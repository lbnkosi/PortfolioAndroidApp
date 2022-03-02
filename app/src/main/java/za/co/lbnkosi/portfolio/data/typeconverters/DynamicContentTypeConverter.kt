package za.co.lbnkosi.portfolio.data.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import za.co.lbnkosi.portfolio.domain.model.DynamicContent
import java.lang.reflect.Type

class DynamicContentTypeConverter {

    private var gson: Gson = Gson()

    @TypeConverter
    fun stringToDynamicContent(data: String?): DynamicContent {
        if (data == null) {
            return DynamicContent()
        }
        val type: Type = object : TypeToken<DynamicContent>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun dynamicContentToString(dynamicContent: DynamicContent): String? {
        return gson.toJson(dynamicContent)
    }

}