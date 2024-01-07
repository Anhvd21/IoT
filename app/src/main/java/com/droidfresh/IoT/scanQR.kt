package com.droidfresh.IoT

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class scanQR : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

        // Khởi tạo IntentIntegrator
        val integrator = IntentIntegrator(this)
        integrator.initiateScan() // Bắt đầu quét
    }

    // Xử lý kết quả từ quét mã QR
    // Trong onActivityResult
    private val REQUEST_CODE_SCAN = 123 // Định nghĩa mã request code, có thể là bất kỳ số nào

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                val scannedData = result.contents // Đọc dữ liệu từ mã QR

                // Tạo Intent để chuyển sang Activity mới
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("scannedData", scannedData) // Chuyển dữ liệu từ quét mã QR
                startActivityForResult(intent, REQUEST_CODE_SCAN)
                setResult(RESULT_OK, intent)

            }
        }
    }

    // Trong NewActivity
    class NewActivity : AppCompatActivity() {
        private var switchCount = 0 // Số lượng công tắc

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.result_layout)

            // Lấy dữ liệu từ Intent
            val scannedData = intent.getStringExtra("scannedData")

            // Xử lý dữ liệu quét được nếu cần thiết

            val addButton = findViewById<ImageView>(R.id.btnuser)
            val userButton = findViewById<LinearLayout>(R.id.btnqr)
            val switch = findViewById<Switch>(R.id.switch1)

            addButton.setOnClickListener {
                switchCount++
                // Hiển thị thêm công tắc sau mỗi lần nhấn
                // Cập nhật giao diện để hiển thị switchCount
            }

            userButton.setOnClickListener {
                // Chuyển về Activity trước đó hoặc thực hiện hành động mong muốn

            }
        }
    }

}
