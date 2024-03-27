package com.example.irgitugasday05

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.irgitugasday05.databinding.ActivityDetailBinding
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var bind: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)
        ViewCompat.setOnApplyWindowInsetsListener(bind.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dataNama = intent.getStringExtra("nama")
        val dataMember = intent.getStringExtra("jenisMember")
        val dataNamaBarang = intent.getStringExtra("namaBarang")
        val dataHargaBarang = intent.getIntExtra("hargaBarang", 0)
        val dataTotalHarga = intent.getIntExtra("totalHarga", 0)
        val dataDiskonHarga = intent.getDoubleExtra("diskonHarga", 0.0)
        val dataDiskonMember = intent.getIntExtra("diskonMember", 0)
        val dataJumlahBayar = intent.getDoubleExtra("jumlahBayar", 0.0)

        val locale = Locale("in", "ID")
        val format = NumberFormat.getNumberInstance(locale)

        bind.nama.text = "${getString(R.string.welcome)} $dataNama"
        bind.member.text = "${getString(R.string.tipe_member)} $dataMember"
        bind.namaBarang.text = "${getString(R.string.nama_barang)} : $dataNamaBarang"
        bind.hargaBarang.text = "${getString(R.string.harga)} : Rp" + format.format(dataHargaBarang)
        bind.totalHarga.text = "${getString(R.string.total_harga)} : Rp" + format.format(dataTotalHarga)
        bind.discountHarga.text = "${getString(R.string.diskon_harga)} : Rp" + format.format(dataDiskonHarga)
        bind.diskonMember.text = "${getString(R.string.diskon_member)} : $dataDiskonMember%"
        bind.jumlahBayar.text = "${getString(R.string.total_bayar)} : Rp" + format.format(dataJumlahBayar)

        val jumlah = format.format(dataJumlahBayar)

        bind.btnShare.setOnClickListener {
            val i = Intent()
            i.setAction(Intent.ACTION_SEND)
            i.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.total_bayar)} : Rp $jumlah")
            i.setType("text/plain")
            startActivity(i)
        }
    }
}