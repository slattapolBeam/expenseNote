package com.example.expensenote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etAmount: EditText
    private lateinit var etNote: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnClear: Button
    private lateinit var tvLastItem: TextView
    private lateinit var tvTotal: TextView

    private var total: Double = 0.0  // เก็บยอดรวมค่าใช้จ่ายวันนี้

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ผูกตัวแปรกับ view
        etAmount = findViewById<EditText>(R.id.etAmount)
        etNote = findViewById<EditText>(R.id.etNote)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnClear = findViewById<Button>(R.id.btnClear)
        tvLastItem = findViewById<TextView>(R.id.tvLastItem)
        tvTotal = findViewById<TextView>(R.id.tvTotal)

        // ปุ่มเพิ่มรายการ
        btnAdd.setOnClickListener {
            val amountText = etAmount.text.toString().trim()
            val noteText = etNote.text.toString().trim()

            if (amountText.isEmpty()) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // แปลงเป็น Double
            val amount = amountText.toDoubleOrNull()
            if (amount == null) {
                Toast.makeText(this, "กรุณากรอกจำนวนเงินเป็นตัวเลข", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // อัปเดตยอดรวม
            total += amount

            // อัปเดต TextView แสดงผล
            val showNote = if (noteText.isNotEmpty()) noteText else "ไม่ระบุ"
            tvLastItem.text = "รายการล่าสุด: $showNote - %.2f บาท".format(amount)
            tvTotal.text = "ยอดรวมวันนี้: %.2f บาท".format(total)

            // เคลียร์ช่องจำนวนเงิน (ให้พิมพ์รายการต่อไปสะดวก)
            etAmount.text.clear()
            etNote.text.clear()
        }

        // ปุ่มเคลียร์ทั้งหมด
        btnClear.setOnClickListener {
            total = 0.0
            etAmount.text.clear()
            etNote.text.clear()
            tvLastItem.text = "รายการล่าสุด: -"
            tvTotal.text = "ยอดรวมวันนี้: 0.00 บาท"
        }
    }
}