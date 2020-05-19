package com.carpe.quicknotes.presentation

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.carpe.quicknotes.di.Scopes
import com.carpe.quicknotes.presentation.base.BaseMvpFragment
import com.carpediem.dailynotes.R
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import toothpick.Scope
import toothpick.Toothpick
import javax.inject.Inject

class QuickNotesActivity : MvpAppCompatActivity(), QuickNotesView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var scope: Scope

    @InjectPresenter
    lateinit var presenter: QuickNotesPresenter

    @ProvidePresenter
    fun providePresenter(): QuickNotesPresenter {
        return scope.getInstance(QuickNotesPresenter::class.java)
    }

    private val currentFragment: BaseMvpFragment?
        get() = supportFragmentManager.findFragmentById(R.id.container) as? BaseMvpFragment

    private val navigator: Navigator =
        SupportAppNavigator(this, supportFragmentManager, R.id.container)

    //todo pincode
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_notes)
        presenter.showNotesList()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        //  currentFragment?.onBackPressed() ?: super.onBackPressed()
    }

    private fun injectDependencies() {
        scope = Toothpick.openScopes(Scopes.Application)
        Toothpick.inject(this, scope)
    }
}
