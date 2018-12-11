package kevin.ma.taskmanagerapp

//declare class with inst vars taskId, taskName, taskDescription
//using primary constructor syntax of Kotlin, defined in class header, can omit constructor keyword
//have default var values to enable default constructor; in addition to constructor with 3 args
//getter/setters -> val = getters only, var = getter/setters
class Task(var taskId: Int = 0, var taskName: String = "", var taskDescription: String = "") {

}
