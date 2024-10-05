package com.example.keijuu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val listaDeCompras: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeItem: TextView = itemView.findViewById(R.id.nomeItem)
        val quantidadeItem: TextView = itemView.findViewById(R.id.quantidadeItem)
        val categoriaItem: TextView = itemView.findViewById(R.id.categoriaItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaDeCompras[position]
        holder.nomeItem.text = item.nome
        holder.quantidadeItem.text = item.quantidade
        holder.categoriaItem.text = item.categoria
    }

    override fun getItemCount(): Int {
        return listaDeCompras.size
    }
}
