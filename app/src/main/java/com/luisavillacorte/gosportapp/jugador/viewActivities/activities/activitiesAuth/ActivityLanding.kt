package com.luisavillacorte.gosportapp.jugador.viewActivities.activities.activitiesAuth

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luisavillacorte.gosportapp.R
import com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos.ImageAdapter
import com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos.presenter.ImagePresenter
import com.luisavillacorte.gosportapp.jugador.adapters.model.landing.ImageContract
import com.luisavillacorte.gosportapp.jugador.adapters.model.landing.ImageData
import com.luisavillacorte.gosportapp.jugador.viewActivities.activities.principalMain.MainActivity

class ActivityLanding : AppCompatActivity(), ImageContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: ImagePresenter
    private lateinit var imageAdapter: ImageAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private val autoScrollInterval: Long = 3000 // 3 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        recyclerView = findViewById(R.id.recyclerViewImages)
        progressBar = findViewById(R.id.progressBar)
        val btnlandig: Button = findViewById(R.id.buttonlanding)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnlandig.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        imageAdapter = ImageAdapter(emptyList()) { imageData ->
            showModal(imageData)
        }
        recyclerView.adapter = imageAdapter

        presenter = ImagePresenter(this)
        presenter.loadImages()
    }

    private fun startAutoScroll() {
        val runnable = object : Runnable {
            override fun run() {
                if (imageAdapter.itemCount > 0) {
                    currentPage = (currentPage + 1) % imageAdapter.itemCount
                    recyclerView.smoothScrollToPosition(currentPage)
                }
                handler.postDelayed(this, autoScrollInterval)
            }
        }
        handler.postDelayed(runnable, autoScrollInterval)
    }

    override fun displayImages(images: List<ImageData>) {
        imageAdapter.updateData(images)
        startAutoScroll() // Inicia el desplazamiento automático después de cargar las imágenes
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Detener el desplazamiento automático cuando la actividad se destruye
    }

    private fun showModal(imageData: ImageData) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.modalfoto)

        val imageViewModal: ImageView = dialog.findViewById(R.id.imageViewModal)
        val textViewNombreModal: TextView = dialog.findViewById(R.id.textViewNombreModal)
        val textViewDescripcionModal: TextView = dialog.findViewById(R.id.textViewDescripcionModal)

        Glide.with(this).load(imageData.ImageUrl).into(imageViewModal)
        textViewNombreModal.text = imageData.Nombre
        textViewDescripcionModal.text = imageData.Descripcion

        dialog.show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
