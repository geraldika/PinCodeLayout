package com.carpe.quicknotes.presentation.noteslist

import android.os.Bundle
import android.util.Log
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.carpe.quicknotes.presentation.base.BaseMvpFragment
import com.carpediem.dailynotes.R

class NotesListFragment : BaseMvpFragment(), NotesListView {

    override val layoutRes: Int
        get() = R.layout.fragment_notes_list

    @InjectPresenter
    lateinit var presenter: NotesListPresenter

    @ProvidePresenter
    fun providePresenter(): NotesListPresenter {
        return scope.getInstance(NotesListPresenter::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TestB", "onViewCreated $ ")
    }
}