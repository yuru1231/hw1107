package com.example.lab6

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 設定邊距調整
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 定義按鈕元件
        val btnToast = findViewById<Button>(R.id.btnToast)
        val btnSnackBar = findViewById<Button>(R.id.btnSnackBar)
        val btnDialog1 = findViewById<Button>(R.id.btnDialog1)
        val btnDialog2 = findViewById<Button>(R.id.btnDialog2)
        val btnDialog3 = findViewById<Button>(R.id.btnDialog3)

        // 設定選項列表
        val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

        // 設定按鈕的點擊事件
        btnToast.setOnClickListener { showToast("預設 Toast") }

        btnSnackBar.setOnClickListener {
            Snackbar.make(it, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("按鈕") { showToast("已回應") }
                .show()
        }

        btnDialog1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("按鈕式 AlertDialog")
                .setMessage("AlertDialog 內容")
                .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
                .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
                .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
                .show()
        }

        btnDialog2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("列表式 AlertDialog")
                .setItems(items) { _, i -> showToast("你選的是${items[i]}") }
                .show()
        }

        btnDialog3.setOnClickListener {
            var selectedPosition = 0
            AlertDialog.Builder(this)
                .setTitle("單選式 AlertDialog")
                .setSingleChoiceItems(items, 0) { _, i -> selectedPosition = i }
                .setPositiveButton("確定") { _, _ -> showToast("你選的是${items[selectedPosition]}") }
                .show()
        }
    }

    // 建立 showToast 方法，顯示 Toast 訊息
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

//簡化了按鈕點擊事件的寫法，使程式碼更簡潔。
//使用變數名稱 items 更具語意。
//去除了不必要的註解，讓程式碼更簡潔直觀。