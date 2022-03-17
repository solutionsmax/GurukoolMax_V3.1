package com.solutionsmax.gurukoolmax_v3.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solutionsmax.gurukoolmax_v3.room.dao.LicenseDAO
import com.solutionsmax.gurukoolmax_v3.room.dao.TokenDAO
import com.solutionsmax.gurukoolmax_v3.room.entity.LicenseItem
import com.solutionsmax.gurukoolmax_v3.room.entity.TokenItem

@Database(
    entities = [
        TokenItem::class,
        LicenseItem::class
    ], version = 3, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tokenDao(): TokenDAO
    abstract fun licenseDao(): LicenseDAO

    companion object {
        const val DATABASE_NAME = "gurukool_max_db"
    }
}