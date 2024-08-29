package com.luisavillacorte.gosportapp.jugador.adapters.model.homeCampeonatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luisavillacorte.gosportapp.R
import com.luisavillacorte.gosportapp.jugador.adapters.model.landing.ImageData

class ImageAdapter(private var imageList: List<ImageData>, private val onClick: (ImageData) -> Unit) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageData = imageList[position]
        holder.bind(imageData)
        holder.itemView.setOnClickListener {
            onClick(imageData)  // Aquí llamas a la función que abrirá el modal
        }
    }

    override fun getItemCount(): Int = imageList.size

    fun updateData(newList: List<ImageData>) {
        imageList = newList
        notifyDataSetChanged()
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(imageData: ImageData) {
            Glide.with(itemView.context)
                .load(imageData.ImageUrl)
                .into(imageView)
        }
    }
}
