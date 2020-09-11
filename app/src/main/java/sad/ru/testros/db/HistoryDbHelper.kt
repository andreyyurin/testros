package sad.ru.testros.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import sad.ru.testros.models.WeatherData

class HistoryDbHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) :
    SQLiteOpenHelper(
        context, DATABASE_NAME,
        factory, DATABASE_VERSION
    ) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_CITY
                + " TEXT," +
                COLUMN_TEMPERATURE
                + " TEXT," +
                COLUMN_CLOUD
                + " TEXT," +
                COLUMN_WET
                + " TEXT," +
                COLUMN_DATE
                + " TEXT" + ")")
        db.execSQL(CREATE_HISTORY_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(data: WeatherData, date: String) {
        val values = ContentValues()
        values.put(COLUMN_CITY, data.name)
        values.put(COLUMN_TEMPERATURE, data.main.temp.toString())
        values.put(COLUMN_WET, data.main.humidity)
        values.put(COLUMN_DATE, date)
        values.put(COLUMN_CLOUD, data.weather[0].description)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "mindorksName.db"
        val TABLE_NAME = "history"
        val COLUMN_ID = "_id"
        val COLUMN_CITY = "city"
        val COLUMN_TEMPERATURE = "temperature"
        val COLUMN_CLOUD = "cloud"
        val COLUMN_WET = "wet"
        val COLUMN_DATE = "date"
    }
}