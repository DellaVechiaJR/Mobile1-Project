package com.example.keijuu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShoppingListAdapter(private var shoppingLists: List<ShoppingList>, private val context: Context) :
    RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    class ShoppingListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.textView)
        val image: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shopping_list, parent, false)
        return ShoppingListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val shoppingList = shoppingLists[position]
        holder.title.text = shoppingList.title
        holder.image.setImageResource(R.drawable.baseline_add_shopping_cart_24) // Imagem estática escolhida

        // Adicionando um listener para a ImageView
        holder.image.setOnClickListener {
            // Inicia a SupermercadoActivity quando a imagem é clicada
            val intent = Intent(context, SupermercadoActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = shoppingLists.size
}
