package com.carpe.quicknotes.navigation

import com.carpe.quicknotes.presentation.noteslist.NotesListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    object NotesList : SupportAppScreen() {
        override fun getFragment() = NotesListFragment()
    }
}