package kevin.ma.taskmanagerapp

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TaskActivity : AppCompatActivity() {

    private var taskManager: TaskManager? = null

    private var txtId: EditText? = null
    private var txtTaskName: EditText? = null
    private var txtTaskDescription: EditText? = null

    private var btnAdd: Button? = null
    private var btnShow: Button? = null
    private var btnEdit: Button? = null

    companion object {
        val TABLE_NAME = "AndroidTask"
        val tableCreatorString: String = "CREATE TABLE $TABLE_NAME (taskId integer primary key, taskName text, taskDescription text)"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtId = findViewById(R.id.edTextTaskId)
        txtTaskName = findViewById(R.id.edTextTaskName)
        txtTaskDescription = findViewById(R.id.edTextTaskDescription)

        btnAdd = findViewById(R.id.btnInsertTask)
        btnShow = findViewById(R.id.btnShowTask)
        btnEdit = findViewById(R.id.btnEdit)

        //attach event handlers
        btnAdd!!.setOnClickListener { view -> addTask(view) }
        btnShow!!.setOnClickListener { view -> showTask(view) }
        btnEdit!!.setOnClickListener { view -> editTask(view) }

        try {
            taskManager = TaskManager(this)
            taskManager!!.dbInitialize(TaskActivity.TABLE_NAME, TaskActivity.tableCreatorString)
        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            Log.i("Error: ", exception.message)
        }
    }

    fun showTask(v: View) {
        try {
            val task = taskManager!!.getTaskbyId(txtId!!.text.toString(), "taskId")

            txtTaskName!!.setText(task!!.taskName)
            txtTaskDescription!!.setText(task!!.taskDescription)

        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            Log.i("Error: ", exception.message)
        }
    }

    fun addTask(v: View) {
        //read values
        val taskId = Integer.parseInt(txtId!!.text.toString())
        val taskName = txtTaskName!!.text.toString()
        val taskDescription = txtTaskDescription!!.text.toString()

        val contentValues: ContentValues = ContentValues()
        contentValues.put("taskId", taskId)
        contentValues.put("taskName", taskName)
        contentValues.put("taskDescription", taskDescription)

        try {
            taskManager!!.addRow(contentValues)
        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            Log.i("Error: ", exception.message)
        }
    }

    fun editTask(v: View) {
        val taskId = Integer.parseInt(txtId!!.text.toString())
        val taskName = txtTaskName!!.text.toString()
        val taskDescription = txtTaskDescription!!.text.toString()

        try {
            val contentValues = ContentValues()
            contentValues.put("taskId", taskId)
            contentValues.put("taskName", taskName)
            contentValues.put("taskDescription", taskDescription)

            val b = taskManager!!.editRow(taskId, "taskId", contentValues)

        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            Log.i("Error: ", exception.message)
        }
    }
}
