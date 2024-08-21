package com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luisavillacorte.gosportapp.R

class ImageAdapter(private val images: List<ImageData>) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewNombre: TextView = view.findViewById(R.id.textViewNombre)
        val textViewDescripcion: TextView = view.findViewById(R.id.textViewDescripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.textViewNombre.text = image.Nombre
        //holder.textViewDescripcion.text = image.Descripcion
        Glide.with(holder.imageView.context).load(image.ImageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}