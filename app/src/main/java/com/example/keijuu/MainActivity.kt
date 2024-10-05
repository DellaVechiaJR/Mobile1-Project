package com.example.keijuu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var shoppingListAdapter: ShoppingListAdapter
    private val shoppingLists = mutableListOf<ShoppingList>()
    private val filteredShoppingLists = mutableListOf<ShoppingList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa a RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Passa o contexto corretamente para o adaptador
        shoppingListAdapter = ShoppingListAdapter(filteredShoppingLists, this) // Passando 'this' como contexto
        recyclerView.adapter = shoppingListAdapter

        // Configura o botão de adicionar lista
        findViewById<Button>(R.id.addShoppingListButton).setOnClickListener {
            startActivityForResult(
                Intent(this, AddShoppingListActivity::class.java),
                ADD_LIST_REQUEST_CODE
            )
        }

        // Configura o botão de logout
        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Configura a SearchView
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterLists(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterLists(newText)
                return false
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_LIST_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.let {
                val newListTitle = it.getStringExtra(AddShoppingListActivity.EXTRA_LIST_TITLE) ?: ""
                val shoppingList = ShoppingList(newListTitle)
                shoppingLists.add(shoppingList)
                sortLists()
                filterLists(null)  // Atualiza a lista filtrada
            }
        }
    }

    private fun sortLists() {
        shoppingLists.sortBy { it.title }
    }

    private fun filterLists(query: String?) {
        filteredShoppingLists.clear()
        if (query.isNullOrEmpty()) {
            filteredShoppingLists.addAll(shoppingLists)
        } else {
            val lowerCaseQuery = query.lowercase() // use lowercase() para evitar deprecated warnings
            filteredShoppingLists.addAll(shoppingLists.filter {
                it.title.lowercase().contains(lowerCaseQuery) // use lowercase() aqui também
            })
        }
        shoppingListAdapter.notifyDataSetChanged()
    }

    companion object {
        const val ADD_LIST_REQUEST_CODE = 1
    }
}
