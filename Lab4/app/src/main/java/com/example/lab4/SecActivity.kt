package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)

        // 設置主視圖的系統邊距
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 初始化元件
        val edDrink = findViewById<TextView>(R.id.edDrink)
        val rgSugar = findViewById<RadioGroup>(R.id.rgSugar)
        val rgIce = findViewById<RadioGroup>(R.id.rgIce)
        val btnSend = findViewById<Button>(R.id.btnSend)

        // 設定 btnSend 點擊事件
        btnSend.setOnClickListener {
            // 檢查 edDrink 是否為空
            if (edDrink.text.isEmpty()) {
                Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
            } else {
                // 將飲料名稱、甜度和冰塊資訊存入 Bundle
                val bundle = bundleOf(
                    "drink" to edDrink.text.toString(),
                    "sugar" to rgSugar.findViewById<RadioButton>(
                        rgSugar.checkedRadioButtonId
                    ).text.toString(),
                    "ice" to rgIce.findViewById<RadioButton>(
                        rgIce.checkedRadioButtonId
                    ).text.toString()
                )

                // 建立 Intent，並加入 Bundle
                val intent = Intent().putExtras(bundle)

                // 設定結果為 RESULT_OK 並關閉 Activity
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
