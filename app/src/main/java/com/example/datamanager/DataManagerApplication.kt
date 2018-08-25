package com.example.datamanager

import android.app.Application
import com.example.datamanager.database.DaoMaster
import com.example.datamanager.database.DaoSession

class DataManagerApplication : Application() {

    var daoSession: DaoSession? = null

    override fun onCreate() {
        super.onCreate()

        setupDatabase()
    }

    private fun setupDatabase() {
        val devOpenHelper = DaoMaster.DevOpenHelper(this, "db")
        val database = devOpenHelper.writableDatabase
        daoSession = DaoMaster(database).newSession()
    }
}