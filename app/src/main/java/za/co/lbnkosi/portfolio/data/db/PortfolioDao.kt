package za.co.lbnkosi.portfolio.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import za.co.lbnkosi.portfolio.data.entity.PortfolioEntity

@Dao
interface PortfolioDao {

    @Query("DELETE FROM PortfolioEntity")
    suspend fun deletePortfolioEntity()

    @Query("SELECT * FROM PortfolioEntity")
    suspend fun getPortfolioEntity(): PortfolioEntity

    @Query("SELECT COUNT(*) FROM PortfolioEntity")
    suspend fun availablePortfolio(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePortfolioEntity(portfolioEntity: PortfolioEntity)

}