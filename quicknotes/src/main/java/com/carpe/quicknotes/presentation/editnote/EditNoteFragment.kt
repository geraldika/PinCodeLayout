package com.carpe.quicknotes.presentation.editnote

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.carpe.quicknotes.presentation.base.BaseMvpFragment
import com.carpediem.dailynotes.R

class EditNoteFragment : BaseMvpFragment(), EditNoteView {

    override val layoutRes: Int
        get() = R.layout.fragment_edit_note

    @InjectPresenter
    lateinit var presenter: EditNotePresenter

    @ProvidePresenter
    fun providePresenter(): EditNotePresenter {
        return scope.getInstance(EditNotePresenter::class.java)
    }
}