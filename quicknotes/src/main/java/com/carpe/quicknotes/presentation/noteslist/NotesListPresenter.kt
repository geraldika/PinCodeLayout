package com.carpe.quicknotes.presentation.noteslist

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class NotesListPresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<NotesListView>() {


}