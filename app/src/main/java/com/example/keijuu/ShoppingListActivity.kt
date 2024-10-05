package com.example.keijuu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var shoppingListRecyclerView: RecyclerView
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private lateinit var shoppingLists: MutableList<ShoppingList>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        // Inicialização da RecyclerView
        shoppingListRecyclerView = findViewById(R.id.recyclerViewShoppingList)
        shoppingListRecyclerView.layoutManager = GridLayoutManager(this, 2)

        // Inicialização da lista de compras
        shoppingLists = mutableListOf()
        shoppingListAdapter = ShoppingListAdapter(shoppingLists, this)

        shoppingListRecyclerView.adapter = shoppingListAdapter

        // Configurar botão de adicionar lista
        val buttonAddList: Button = findViewById(R.id.buttonAddList)
        buttonAddList.setOnClickListener {
            // Ação para adicionar nova lista
            val intent = Intent(this, AddShoppingListActivity::class.java)
            startActivityForResult(intent, ADD_LIST_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_LIST_REQUEST_CODE && resultCode == RESULT_OK) {
            // Obter os dados da nova lista e atualizar a RecyclerView
            data?.let {
                val newListTitle = it.getStringExtra(EXTRA_LIST_TITLE) ?: ""
                val shoppingList = ShoppingList(newListTitle)
                shoppingLists.add(shoppingList)
                shoppingListAdapter.notifyItemInserted(shoppingLists.size - 1)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        shoppingLists.clear()
    }

    companion object {
        const val ADD_LIST_REQUEST_CODE = 1
        const val EXTRA_LIST_TITLE = "EXTRA_LIST_TITLE"
    }
}
