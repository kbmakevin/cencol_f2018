package kevin.ma.kevinma_comp304f18finallabtest_001

import java.io.Serializable

//declare class with inst vars stockSymbol, companyName, stockQuote
//using primary constructor syntax of Kotlin, defined in class header, can omit constructor keyword
//have default var values to enable default constructor; in addition to constructor with 3 args
//getter/setters -> val = getters only, var = getter/setters
class Stock(
        var stockSymbol: String = "",
        var companyName: String = "",
        var stockQuote: Int = 0
) : Serializable {

}