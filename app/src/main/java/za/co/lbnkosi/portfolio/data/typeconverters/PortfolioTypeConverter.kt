package za.co.lbnkosi.portfolio.data.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import za.co.lbnkosi.portfolio.domain.model.Portfolio
import java.lang.reflect.Type

class PortfolioTypeConverter {

    private var gson: Gson = Gson()

    @TypeConverter
    fun stringToPortfolio(data: String?): Portfolio {
        if (data == null) {
            return Portfolio()
        }
        val type: Type = object : TypeToken<Portfolio>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun portfolioToString(portfolio: Portfolio): String? {
        return gson.toJson(portfolio)
    }

}