package za.co.lbnkosi.portfolio.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import za.co.lbnkosi.portfolio.data.entity.DynamicContentEntity

@Dao
interface DynamicContentDao {

    @Query("DELETE FROM DynamicContentEntity")
    suspend fun deleteDynamicContentEntity()

    @Query("SELECT * FROM DynamicContentEntity")
    suspend fun getDynamicContentEntity(): DynamicContentEntity

    @Query("SELECT COUNT(*) FROM DynamicContentEntity")
    suspend fun availableDynamicContent(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDynamicContentEntity(dynamicContentEntity: DynamicContentEntity)

}