package com.guillem.sample_app_willy_wonka_chocolate_factory.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.dao.WorkersDao
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.dao.RemoteKeyDao
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.OompaLoompaEntity
import com.guillem.sample_app_willy_wonka_chocolate_factory.data.local.entity.RemoteKeyEntity

private const val APP_DB_NAME = "willy_wonka_chocolate_factory_database"


@Database(
    entities = [
        RemoteKeyEntity::class,
        OompaLoompaEntity::class
    ], version = 1, exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun workersDao(): WorkersDao
    abstract fun remoteKeysDao(): RemoteKeyDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context.applicationContext, AppDatabase::class.java, APP_DB_NAME)
            .build()
    }
}