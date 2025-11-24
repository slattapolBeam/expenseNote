package com.example.expensenote

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MainActivity : AppCompatActivity() {

    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ต้องเป็น layout ที่มี PieChart จริง ๆ
        setContentView(R.layout.activity_main)

        pieChart = findViewById(R.id.pieChart)

        setupDonutChart()
    }

    private fun setupDonutChart() {
        // 1) เตรียมข้อมูล (ห้ามว่าง)
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(120f, "อาหาร"))
        entries.add(PieEntry(80f, "เดินทาง"))
        entries.add(PieEntry(50f, "ช้อปปิ้ง"))
        entries.add(PieEntry(30f, "อื่น ๆ"))

        // 2) สร้าง DataSet
        val dataSet = PieDataSet(entries, "")
        dataSet.colors = listOf(
            Color.parseColor("#81C784"), // เขียวอ่อน
            Color.parseColor("#64B5F6"), // ฟ้า
            Color.parseColor("#FFB74D"), // ส้ม
            Color.parseColor("#E57373")  // แดงอ่อน
        )
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueTextSize = 12f

        // 3) สร้าง Data
        val data = PieData(dataSet)

        // 4) เซ็ต data ให้กราฟ
        pieChart.data = data

        // 5) ตั้งให้เป็น donut
        pieChart.isDrawHoleEnabled = true
        pieChart.holeRadius = 60f
        pieChart.transparentCircleRadius = 65f
        pieChart.setHoleColor(Color.parseColor("#FAFAFA"))

        pieChart.description.isEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.isEnabled = true

        // 6) รีเฟรชกราฟ
        pieChart.invalidate()
    }
}