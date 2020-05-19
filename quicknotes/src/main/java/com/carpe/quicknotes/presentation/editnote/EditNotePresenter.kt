package com.carpe.quicknotes.presentation.editnote

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class EditNotePresenter @Inject constructor(
    private val router: Router
) : MvpPresenter<EditNoteView>() {

}