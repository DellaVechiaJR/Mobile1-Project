package com.example.keijuu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class AdicionaritemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_item)

        val nomeItem = findViewById<EditText>(R.id.etNomeItem)
        val quantidadeItem = findViewById<EditText>(R.id.etQuantidade)
        val categoriaItem = findViewById<Spinner>(R.id.spCategoria)
        val btnAdicionar = findViewById<Button>(R.id.btnAdicionar)

        btnAdicionar.setOnClickListener {
            val nome = nomeItem.text.toString()
            val quantidade = quantidadeItem.text.toString()
            val categoria = categoriaItem.selectedItem.toString()

            if (nome.isNotEmpty() && quantidade.isNotEmpty()) {
                // Cria o novo item e adiciona à lista de compras global no Singleton
                val novoItem = Item(nome, quantidade, categoria)
                ShoppingListManager.listaDeCompras.add(novoItem)

                // Finaliza a activity para voltar à anterior
                finish()
            }
        }
    }
}
