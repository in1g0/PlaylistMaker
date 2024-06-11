package com.practicum.playlistmaker

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import android.view.inputmethod.InputMethodManager

class SearchActivity : AppCompatActivity() {
    companion object {
        private const val KEY_SEARCH_TEXT = "searchText"
    }

    private var searchText: String = ""
    private lateinit var searchEditText: EditText
    private lateinit var clearSearchButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        searchEditText = findViewById(R.id.searchEditText)
        clearSearchButton = findViewById(R.id.clearSearchButton)
        clearSearchButton.isVisible = false
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                searchText = s.toString()
                clearSearchButton.isVisible = s?.isNotEmpty() == true
            }
        })
        clearSearchButton.setOnClickListener {
            searchEditText.setText("")
            hideKeyboard()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_SEARCH_TEXT, searchText)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        searchText = savedInstanceState.getString(KEY_SEARCH_TEXT, "")
        searchEditText.setText(searchText)
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
