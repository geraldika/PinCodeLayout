package com.carpe.quicknotes.presentation

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.carpe.quicknotes.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class QuickNotesPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<QuickNotesView>() {

    fun showNotesList() {
        router.newRootScreen(Screens.NotesList)
    }
}