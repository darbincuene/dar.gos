package com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luisavillacorte.gosportapp.R

class ImageAdapter(
    private val context: Context,
    private val images: List<ImageData>,

    ) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewNombre: TextView = view.findViewById(R.id.textViewNombre)
        val textViewDescripcion: TextView = view.findViewById(R.id.textViewDescripcion)
        val contenedorimagenes:LinearLayout=view.findViewById(R.id.imageViewC)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.textViewNombre.text = image.Nombre

        Glide.with(holder.imageView.context).load(image.ImageUrl).into(holder.imageView)

        // Configurar el OnClickListener para abrir el modal al hacer clic en cualquier parte del contenedor
        holder.imageView.setOnClickListener {
            showImageModal(image)
        }
    }




    private fun showImageModal(image: ImageData) {
        // Crear el dialogo
        val dialogView = LayoutInflater.from(context).inflate(R.layout.modalfoto, null)

        // Configurar la imagen, nombre y descripción dentro del modal
        val imageView: ImageView = dialogView.findViewById(R.id.imageViewModal)
        val textViewNombre: TextView = dialogView.findViewById(R.id.textViewNombreModal)
        val textViewDescripcion: TextView = dialogView.findViewById(R.id.textViewDescripcionModal)

        Glide.with(context).load(image.ImageUrl).into(imageView)
        textViewNombre.text = image.Nombre
        textViewDescripcion.text = image.Descripcion

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun getItemCount(): Int {
        return images.size
    }
}
