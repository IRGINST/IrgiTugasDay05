package com.example.irgitugasday05

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.irgitugasday05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private var diskon: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(bind.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bind.btnProses.setOnClickListener {
            val nama = bind.nama.text.toString()
            var hargaBarang = 0
            val jumlahBarang = bind.jumlahBarang.text.toString().toInt()
            var jenisMember = ""

            if (bind.gold.isChecked) {
                diskon = 10
                jenisMember = "GOLD"
            } else if (bind.silver.isChecked){
                diskon = 5
                jenisMember = "SILVER"
            } else if (bind.biasa.isChecked) {
                diskon = 2
                jenisMember = "BIASA"
            }

            val namaBarang = bind.kodeBarang.text.toString()
            val namaItem: String
            when(namaBarang){
                "IPX" -> {
                    hargaBarang = 5723300
                    namaItem = "IPHONE X"
                }
                "MP3" -> {
                    hargaBarang = 28999999
                    namaItem = "Macbook Pro M3"
                }
                "LV3" -> {
                    hargaBarang = 6666666
                    namaItem = "Lenovo V14 Gen 3"
                }
                else -> {
                    namaItem = ""
                }
            }

            if (nama.isEmpty()){
                Toast.makeText(this, "Nama Masih Kosong", Toast.LENGTH_SHORT).show()
            } else if (diskon == 0){
                Toast.makeText(this, "Diskon Masih Kosong", Toast.LENGTH_SHORT).show()
            } else if (namaItem.isEmpty()) {
                Toast.makeText(this, "Barang Masih Kosong", Toast.LENGTH_SHORT).show()
            } else if (jumlahBarang == 0){
                Toast.makeText(this, "Jumlah Barang Masih Kosong", Toast.LENGTH_SHORT).show()
            } else if (jenisMember.isEmpty()) {
                Toast.makeText(this, "Member Belum dipilih", Toast.LENGTH_SHORT).show()
            } else {
                val diskonHarga = hargaBarang * (diskon / 100.0)
                println("diskon ${diskonHarga.toFloat()}")
                val totalHarga =  hargaBarang * jumlahBarang
                val jumlahBayar = hargaBarang - diskonHarga * jumlahBarang
                println("Total Harga = $jumlahBayar")

                val i = Intent(this, DetailActivity::class.java)
                i.putExtra("nama", nama)
                i.putExtra("jenisMember", jenisMember)
                i.putExtra("namaBarang", namaItem)
                i.putExtra("hargaBarang", hargaBarang)
                i.putExtra("totalHarga", totalHarga)
                i.putExtra("diskonHarga", diskonHarga)
                i.putExtra("diskonMember", diskon)
                i.putExtra("jumlahBayar", jumlahBayar)
                startActivity(i)
            }
        }
    }
}