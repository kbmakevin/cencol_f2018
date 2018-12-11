package kevin.ma.taskmanagerapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskManager(context: Context) : SQLiteOpenHelper(context, TaskManager.DATABASE_NAME, null, TaskManager.DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME: String = "TaskDB"
        val DATABASE_VERSION: Int = 1
        var tableName: String = ""
        var tableCreatorString: String = ""
    }

    //db created first time, table init
    //populate table
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(TaskManager.tableCreatorString)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //drop table if existed
        db!!.execSQL("DROP TABLE IF EXISTS $tableName")

        //recreate the table
        onCreate(db)
    }

    fun dbInitialize(tableName: String, tableCreatorString: String) {
        TaskManager.tableName = tableName
        TaskManager.tableCreatorString = tableCreatorString
    }

    //CRUD operations
    @Throws(Exception::class)
    fun addRow(values: ContentValues): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        //insert the row
        val nr: Long = db.insert(TaskManager.tableName, null, values)
        return nr > -1
    }

    @Throws(Exception::class)
    fun getTaskbyId(id: Any, fieldName: String): Task? {
        val db: SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery("select * from $tableName where $fieldName = '$id'", null)
        var task: Task? = Task()
        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            task!!.taskId = cursor.getInt(0)
            task!!.taskName = cursor.getString(1)
            task!!.taskDescription = cursor.getString(2)
            cursor.close()
        } else {
            task = null
        }
        db.close()
        return task
    }

    @Throws(Exception::class)
    fun editRow(id: Any, fieldName: String, values: ContentValues): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val nr = db.update(TaskManager.tableName, values, "$fieldName = ?", arrayOf(id.toString()))
        return nr > 0
    }
}