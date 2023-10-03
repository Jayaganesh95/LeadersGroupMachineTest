package com.example.studentrecords.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentrecords.model.StudentsDataModel

@Database(entities = [StudentsDataModel::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDatabase(context: Context): StudentDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, StudentDatabase::class.java, "studentDb")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}