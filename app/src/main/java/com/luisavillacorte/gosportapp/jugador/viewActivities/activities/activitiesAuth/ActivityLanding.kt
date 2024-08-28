package com.luisavillacorte.gosportapp.jugador.viewActivities.activities.activitiesAuth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisavillacorte.gosportapp.R
import com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos.ImageAdapter
import com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos.presenter.ImagePresenter
import com.luisavillacorte.gosportapp.jugador.adapters.model.landing.ImageContract
import com.luisavillacorte.gosportapp.jugador.adapters.model.landing.ImageData
import com.luisavillacorte.gosportapp.jugador.viewActivities.activities.principalMain.MainActivity

class ActivityLanding : AppCompatActivity(), ImageContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar  // Actualiza esto para usar ProgressBar
    private lateinit var presenter: ImagePresenter
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        recyclerView = findViewById(R.id.recyclerViewImages)
        progressBar = findViewById(R.id.progressBar)  // Asegúrate de que esto se inicializa correctamente
        val btnlandig:Button=findViewById(R.id.buttonlanding)
        // Configurar el RecyclerView en modo horizontal
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnlandig.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Inicializar el adaptador
        imageAdapter = ImageAdapter(emptyList()) { imageData ->
            // Manejar clic en la imagen
            // Puedes agregar lógica aquí si necesitas manejar los clics
        }
        recyclerView.adapter = imageAdapter

        // Instanciar el Presenter
        presenter = ImagePresenter(this)

        // Cargar las imágenes
        presenter.loadImages()


    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun displayImages(images: List<ImageData>) {
        // Actualizar los datos del adaptador
        imageAdapter.updateData(images)
    }

    override fun showError(message: String) {
        // Mostrar el mensaje de error al usuario
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}