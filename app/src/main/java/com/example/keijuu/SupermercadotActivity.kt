package com.example.keijuu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SupermercadoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddItem: Button
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supermercado)

        recyclerView = findViewById(R.id.rvItens)
        btnAddItem = findViewById(R.id.btnAddItem)

        // Instanciando o adapter e passando a lista de compras
        adapter = ItemAdapter(ShoppingListManager.listaDeCompras)

        // Configurando o RecyclerView com o layout manager e adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Configurando o botão para adicionar novos itens
        btnAddItem.setOnClickListener {
            val intent = Intent(this, AdicionaritemActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualiza a lista após adicionar novos itens
        adapter.notifyDataSetChanged()
    }
}
