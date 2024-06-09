package com.practicum.playlistmaker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    private val KEY_SEARCH_TEXT = "searchText"
    private var searchText: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val searchEditText: EditText = findViewById(R.id.searchEditText)
        val clearSearchButton: ImageButton = findViewById(R.id.clearSearchButton)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                searchText = s.toString()
                clearSearchButton.visibility =
                    if (s?.isNotEmpty() == true) ImageButton.VISIBLE else ImageButton.GONE
            }
        })
        clearSearchButton.setOnClickListener {
            searchEditText.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_SEARCH_TEXT, searchText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchText = savedInstanceState.getString(KEY_SEARCH_TEXT, "")
        val searchEditText: EditText = findViewById(R.id.searchEditText)
        searchEditText.setText(searchText)
    }
}
