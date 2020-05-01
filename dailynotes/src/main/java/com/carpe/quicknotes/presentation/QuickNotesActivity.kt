package com.carpe.quicknotes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carpe.quicknotes.di.Scopes
import com.carpediem.dailynotes.R
import toothpick.Toothpick

class QuickNotesActivity : AppCompatActivity() {

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
