package com.example.keijuu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddShoppingListActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_shopping_list)

        titleEditText = findViewById(R.id.titleEditText)

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val title = titleEditText.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_LIST_TITLE, title)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_LIST_TITLE = "EXTRA_LIST_TITLE"
    }
}
