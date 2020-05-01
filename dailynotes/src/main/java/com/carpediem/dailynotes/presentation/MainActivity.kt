package com.carpediem.dailynotes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carpediem.dailynotes.R
import com.carpediem.dailynotes.di.Scopes
import toothpick.Toothpick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependencies()
    }

    private fun injectDependencies() {
        val scope = Toothpick.openScopes(Scopes.Application)
        Toothpick.inject(this, scope)
    }
}
