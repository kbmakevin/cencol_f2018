package kevin.ma.kevinma_comp304f18finallabtest_001

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class StockInfoService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val selectedStock: Stock = intent!!.getSerializableExtra("selectedStock") as Stock

        Toast.makeText(this, "Stock Info received:\nCompany Name: ${selectedStock.companyName}\nStock Quote: ${selectedStock.stockQuote}", Toast.LENGTH_LONG).show()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
