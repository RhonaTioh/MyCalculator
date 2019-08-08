package sg.edu.rp.c346.mycalculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tv1.setOnClickListener { appendOnExpression("1", true) }
        tv2.setOnClickListener { appendOnExpression("2", true) }
        tv3.setOnClickListener { appendOnExpression("3", true) }
        tv4.setOnClickListener { appendOnExpression("4", true) }
        tv5.setOnClickListener { appendOnExpression("5", true) }
        tv6.setOnClickListener { appendOnExpression("6", true) }
        tv7.setOnClickListener { appendOnExpression("7", true) }
        tv8.setOnClickListener { appendOnExpression("8", true) }
        tv9.setOnClickListener { appendOnExpression("9", true) }
        tv0.setOnClickListener { appendOnExpression("0", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }


        //Operators
        tvAdd.setOnClickListener { appendOnExpression("+", false) }
        tvSubtract.setOnClickListener { appendOnExpression("-", false) }
        tvEquals.setOnClickListener { appendOnExpression("=", false) }
        tvMultiply.setOnClickListener { appendOnExpression("*", false) }
        tvDivide.setOnClickListener { appendOnExpression("/", false) }


        //AC button
        tvAC.setOnClickListener {
            tvDisplay.text = ""
            tvResult.text = ""
        }

        //Equal button
        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvDisplay.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e:Exception) {
                Log.d("Exception", "message: " + e.message)
            }
        }

    }

    fun appendOnExpression(string: String, canClear: Boolean) {
        if(tvResult.text.isNotEmpty()) {
            tvDisplay.text = ""
        }
        if(canClear) {
            tvResult.text = ""
            tvDisplay.append(string)
        } else {
            tvDisplay.append(tvResult.text)
            tvDisplay.append(string)
            tvResult.text = ""
        }
    }
}

