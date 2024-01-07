package com.droidfresh.IoT

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class ResultActivity : AppCompatActivity() {
    private val switchContainer: LinearLayout by lazy { findViewById<LinearLayout>(R.id.switch_container) }
    private var switchCount = 0 // Số lượng công tắc
    private val REQUEST_CODE_SCAN = 123
    private val switchArrayList: ArrayList<Switch> = ArrayList() // ArrayList chứa các Switch


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.result_layout)
        val addButton = findViewById<ImageView>(R.id.btnuser)
        val userButton = findViewById<LinearLayout>(R.id.btnqr)
        val scannedData = intent.getStringExtra("scannedData")
        if (!scannedData.isNullOrEmpty()) {
            addSwitch()
        }

        addButton.setOnClickListener {
            addSwitch()
        }

        userButton.setOnClickListener {
            val intent = Intent(this@ResultActivity, scanQR::class.java)
            startActivityForResult(intent, REQUEST_CODE_SCAN)
        }
    }

    // Hàm thêm switch
    private fun addSwitch() {
        val customSwitchItem = layoutInflater.inflate(R.layout.custom_switch_item, null)
        val newSwitch = customSwitchItem.findViewById<Switch>(R.id.customSwitch)
        val deviceLabel = customSwitchItem.findViewById<TextView>(R.id.deviceLabel)
        val setTimeButton = customSwitchItem.findViewById<LinearLayout>(R.id.setTimeButton)

        // Tìm và liên kết TextView trong custom_switch_item
        val dateTimeTextView = customSwitchItem.findViewById<TextView>(R.id.ttttt)


        deviceLabel.text = "Thiết bị ${switchCount + 1}"
        switchContainer.addView(customSwitchItem)
        switchArrayList.add(newSwitch)
        switchCount++

        setTimeButton.setOnClickListener {
            // Mở TimePickerDialog để chọn thời gian và thực hiện các hành động sau khi chọn
            showDateTimePickerDialog(deviceLabel.text.toString(), newSwitch, dateTimeTextView)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            val scannedData = data?.getStringExtra("scannedData")
            if (!scannedData.isNullOrEmpty()) {
                addSwitch()
            }
        }
    }
    private fun showDateTimePickerDialog(deviceName: String, switch: Switch, dateTimeTextView: TextView) {
        val currentCalendar = Calendar.getInstance()
        val year = currentCalendar.get(Calendar.YEAR)
        val month = currentCalendar.get(Calendar.MONTH)
        val dayOfMonth = currentCalendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val timePickerDialog = TimePickerDialog(
                    this,
                    { _, hourOfDay, minute ->
                        val selectedDateTime = String.format(
                            "%04d-%02d-%02d %02d:%02d",
                            selectedYear,
                            selectedMonth + 1, // Tháng bắt đầu từ 0
                            selectedDay,
                            hourOfDay,
                            minute
                        )
                        // Cập nhật TextView trong custom_switch_item với ngày giờ được chọn
                        dateTimeTextView.text = "Thời gian tắt $deviceName: $selectedDateTime"
                        showCycleOptionsDialog()
                    },
                    currentCalendar.get(Calendar.HOUR_OF_DAY),
                    currentCalendar.get(Calendar.MINUTE),
                    true
                )
                timePickerDialog.setTitle("Chọn thời gian")
                timePickerDialog.show()
            },
            year,
            month,
            dayOfMonth
        )
        datePickerDialog.setTitle("Chọn ngày")
        datePickerDialog.show()
    }

    private fun showCycleOptionsDialog() {
        val cycleOptions = arrayOf("Một lần", "Hằng ngày", "Thứ 2 đến Thứ 7", "Tùy Chỉnh") // Thêm các tùy chọn khác tại đây

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn chu kỳ")
            .setItems(cycleOptions) { _, which ->
                // Xử lý khi người dùng chọn một tùy chọn
                when (which) {
                    0 -> {
                        // Xử lý cho "Hằng ngày"
                    }
                    1 -> {
                        // Xử lý cho "Option 1"
                    }
                    2 -> {
                        // Xử lý cho "Option 2"
                    }
                    3 -> {
                        // Xử lý cho "Option 3"
                    }
                    // Thêm xử lý cho các tùy chọn khác nếu cần thiết
                }
            }
        val dialog = builder.create()
        dialog.show()
    }
}
