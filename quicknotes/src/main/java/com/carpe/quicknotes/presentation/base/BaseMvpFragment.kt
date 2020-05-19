package com.carpe.quicknotes.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carpe.quicknotes.di.Scopes
import moxy.MvpAppCompatFragment
import toothpick.Scope
import toothpick.Toothpick

abstract class BaseMvpFragment : MvpAppCompatFragment(), BaseMvpView {

    abstract val layoutRes: Int

    protected lateinit var scope: Scope
        private set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun showError() {
    }

    private fun injectDependencies() {
        scope = Toothpick.openScopes(Scopes.Application)
        Toothpick.inject(this, scope)
    }
}