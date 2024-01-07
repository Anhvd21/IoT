package com.droidfresh.IoT

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {
    private var switchCount = 0 // Biến để theo dõi số lượng công tắc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_layout) // Gán layout cho NewActivity

        // Lấy dữ liệu từ Intent
        val scannedData = intent.getStringExtra("scannedData")

        // Xử lý dữ liệu quét được nếu cần thiết
        // Ở đây bạn có thể xử lý dữ liệu mã QR đã quét được (được gửi qua "scannedData")

        // Ánh xạ các thành phần giao diện
        val addButton = findViewById<ImageView>(R.id.btnuser)
        val userButton = findViewById<LinearLayout>(R.id.btnqr)
        val switch = findViewById<Switch>(R.id.switch1)

        // Bắt sự kiện khi người dùng nhấn nút "Add" (hoặc ImageView)
        addButton.setOnClickListener {
            switchCount++
            // Tăng biến đếm switchCount mỗi khi nhấn nút
            // Cập nhật giao diện để hiển thị switchCount (đã quét được)

            // Ví dụ: Cập nhật TextView hoặc hiển thị số lượng switch trên giao diện
        }

        // Bắt sự kiện khi người dùng nhấn nút "User" (hoặc LinearLayout)
        userButton.setOnClickListener {
            // Điều hướng đến Activity khác hoặc thực hiện hành động mong muốn
        }
    }
}
