package kevin.ma.kevinma_comp304f18finallabtest_001

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StockManager
(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        //database name and version
        private val DATABASE_NAME = "Stocks.db"
        private val DATABASE_VERSION = 1
        //SQLite types
        private val BOOLEAN = "Boolean"
        private val BYTE = "Byte"
        private val SHORT = "Short"
        private val INTEGER = "Integer"
        private val LONG = "Long"
        private val FLOAT = "Float"
        private val DOUBLE = "Double"
        private val STRING = "String"
    }

    //
    private var tableName: String? = null //table name
    private var tableCreatorString: String? = null //SQL statement to create the table
    //
    //initialize table names and CREATE TABLE statement
    //called by activity to create a table in the database
    //The following arguments should be passed:
    // tableName - a String variable which holds the table name
    // tableCreatorString - a String variable which holds the CREATE Table statement
    fun dbInitialize(tableName: String, tableCreatorString: String) {
        this.tableName = tableName
        this.tableCreatorString = tableCreatorString

    }

    override fun onCreate(db: SQLiteDatabase) {
        //drop table
        //create the table
        db.execSQL(tableCreatorString)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //
        db.execSQL("DROP TABLE IF EXISTS " + tableName!!)
        onCreate(db)
    }

    //This method is called by the activity to add a row in the table
    // The following arguments should be passed:
    //  fieldNames - a String array that holds the names of table fields
    //  fieldTypes - a String array that holds the Java types of table fields
    //  fieldValues - an Object array that holds the values to be stored in table fields
    //
    @Throws(Exception::class)
    fun addRow(fieldNames: Array<String>, fieldTypes: Array<String>, fieldValues: Array<Any>): Boolean {
        val db = this.writableDatabase
        //
        val contentValues = ContentValues()
        for (i in fieldNames.indices) {
            when (fieldTypes[i]) {
                BYTE -> {
                    val byteValue = fieldValues[i] as Byte
                    contentValues.put(fieldNames[i], byteValue)
                }
                SHORT -> {
                    val shortValue = fieldValues[i] as Short
                    contentValues.put(fieldNames[i], shortValue)
                }
                INTEGER -> {
                    val integerValue = fieldValues[i] as Int
                    contentValues.put(fieldNames[i], integerValue)
                }
                LONG -> {
                    val longValue = fieldValues[i] as Long
                    contentValues.put(fieldNames[i], longValue)
                }
                FLOAT -> {
                    val floatValue = fieldValues[i] as Float
                    contentValues.put(fieldNames[i], floatValue)
                }
                DOUBLE -> {
                    val doubleValue = fieldValues[i] as Double
                    contentValues.put(fieldNames[i], doubleValue)
                }
                STRING -> {
                    val stringValue = fieldValues[i] as String
                    contentValues.put(fieldNames[i], stringValue)
                }
            }


        }
        // Insert the row
        val nr = db.insert(tableName, null, contentValues)
        db.close() //close database connection
        return nr > -1
    }

    //This method returns a Cursor object which holds the table row with the given id
    //The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the field name for the primary key field
    @Throws(Exception::class)
    fun getRowById(id: Any, fieldName: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("select * from " + tableName + " where " + fieldName + "='" + id.toString() + "'", null)
    }


    fun editRow(fieldNames: Array<String>, fieldTypes: Array<String>, fieldValues: Array<Any>): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        for (i in 1 until fieldNames.size) {
            when (fieldTypes[i]) {
                BYTE -> {
                    val byteValue = fieldValues[i] as Byte
                    contentValues.put(fieldNames[i], byteValue)
                }
                SHORT -> {
                    val shortValue = fieldValues[i] as Short
                    contentValues.put(fieldNames[i], shortValue)
                }
                INTEGER -> {
                    val integerValue = fieldValues[i] as Int
                    contentValues.put(fieldNames[i], integerValue)
                }
                LONG -> {
                    val longValue = fieldValues[i] as Long
                    contentValues.put(fieldNames[i], longValue)
                }
                FLOAT -> {
                    val floatValue = fieldValues[i] as Float
                    contentValues.put(fieldNames[i], floatValue)
                }
                DOUBLE -> {
                    val doubleValue = fieldValues[i] as Double
                    contentValues.put(fieldNames[i], doubleValue)
                }
                STRING -> {
                    val stringValue = fieldValues[i] as String
                    contentValues.put(fieldNames[i], stringValue)
                }
            }

        }
        //
        val nr = db.update(tableName, contentValues, fieldNames[0] + " = ? ", arrayOf(fieldValues[0].toString()))
        return nr > 0
    }
}
