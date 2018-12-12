package kevin.ma.kevinma_comp304f18finallabtest_001

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var stockManager: StockManager? = null

    private var companyNameTextView: TextView? = null
    private var stockQuoteTextView: TextView? = null

    private var btnInsertStocks: Button? = null
    private var btnDisplayStockInfo: Button? = null

    private var stockSymbolRadioGrp: RadioGroup? = null

    private var selectedStockSymbol: String? = null

    companion object {
        const val TABLE_NAME = "StockInfo"
        const val tableCreatorString: String = "CREATE TABLE $TABLE_NAME (stockQuote integer primary key, companyName text, stockSymbol text)"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        companyNameTextView = findViewById(R.id.company_name_text_view)
        stockQuoteTextView = findViewById(R.id.stock_quote_text_view)

        btnInsertStocks = findViewById(R.id.insert_stocks_btn)
        btnDisplayStockInfo = findViewById(R.id.display_stock_info_btn)

        stockSymbolRadioGrp = findViewById(R.id.stock_symbol_radio_grp)

        //hide the textviews initially until the user clicks on button to display stock information
        companyNameTextView!!.visibility = View.INVISIBLE
        stockQuoteTextView!!.visibility = View.INVISIBLE

        try {
            stockManager = StockManager(this)
            stockManager!!.dbInitialize(TABLE_NAME, tableCreatorString)
        } catch (exception: Exception) {
            Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            Log.i("Error: ", exception.message)
        }

        //add event handlers
        btnInsertStocks!!.setOnClickListener {
            run {
                val fieldNames: Array<String> = arrayOf("stockQuote", "companyName", "stockSymbol")
                val fieldTypes: Array<String> = arrayOf("Integer", "String", "String")
                val arrayOfFieldValues: Array<Array<Any>> = arrayOf(
                        arrayOf(1695, "Amazon", "AMZN"),
                        arrayOf(1088, "Google", "GOOGL"),
                        arrayOf(2210, "Samsung Electronics Co Ltd", "SSNLF")
                )

                try {
                    var tempString = ""
                    for (fieldValues in arrayOfFieldValues) {
                        stockManager!!.addRow(fieldNames, fieldTypes, fieldValues)
                        tempString += "- ${fieldValues[1]}\n"
                    }
                    Toast.makeText(this, "The stock information for the following companies:\n\n$tempString\nhave been successfully added to the $TABLE_NAME table of the database!", Toast.LENGTH_SHORT).show()

                } catch (exception: Exception) {
                    Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                    Log.i("Error: ", exception.message)
                }
            }
        }

        stockSymbolRadioGrp!!.setOnCheckedChangeListener { _, idChecked ->
            run {
                when (idChecked) {
                    R.id.googl_radio_btn -> {
                        Toast.makeText(this, "GOOGL was clicked!", Toast.LENGTH_SHORT).show()
                        selectedStockSymbol = "GOOGL"
                    }
                    R.id.amzn_radio_btn -> {
                        Toast.makeText(this, "AMZN was clicked!", Toast.LENGTH_SHORT).show()
                        selectedStockSymbol = "AMZN"
                    }
                    R.id.ssnlf_radio_btn -> {
                        Toast.makeText(this, "SSNLF was clicked!", Toast.LENGTH_SHORT).show()
                        selectedStockSymbol = "SSNLF"
                    }
                }
            }
        }

        btnDisplayStockInfo!!.setOnClickListener {
            run {
                companyNameTextView!!.visibility = View.VISIBLE
                stockQuoteTextView!!.visibility = View.VISIBLE

                //get stock info
                val cursor = stockManager!!.getRowById(selectedStockSymbol!!, "stockSymbol")

                var stock: Stock? = Stock()
                //has result
                if (cursor.moveToFirst()) {
                    cursor.moveToFirst()
                    stock!!.stockQuote = cursor.getInt(0)
                    stock!!.companyName = cursor.getString(1)
                    stock!!.stockSymbol = cursor.getString(2)
                    cursor.close()
                } else stock = null

                companyNameTextView!!.text = "Company Name: ${stock!!.companyName}"
                stockQuoteTextView!!.text = "Stock Quote: ${stock!!.stockQuote}"

                //start the service
                startService(Intent(baseContext, StockInfoService::class.java)
                        .putExtra("selectedStock", stock))
            }
        }
    }
}
