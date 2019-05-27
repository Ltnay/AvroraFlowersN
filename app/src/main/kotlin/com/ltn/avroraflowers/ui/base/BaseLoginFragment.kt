package com.ltn.avroraflowers.ui.base

import android.content.SharedPreferences
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment
import com.ltn.avroraflowers.App
import com.ltn.avroraflowers.ui.activities.mainActivity.MainActivity
import com.ltn.avroraflowers.utils.PreferencesUtils
import javax.inject.Inject

abstract class BaseLoginFragment : BaseManagerFragment() {

    @Inject
    lateinit var preferencesUtils: PreferencesUtils

    private lateinit var preferencesListener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.component.inject(this)

        if (activity is MainActivity) {
            preferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
                if (preferencesUtils.isLogin()) refreshUserStatus(true)
                else refreshUserStatus(false)
            }
            preferencesUtils.registerChangePreferences(preferencesListener)
        }

        userLogin(preferencesUtils.isLogin())
    }

    override fun onResume() {
        super.onResume()
        refreshUserStatus(preferencesUtils.isLogin())
    }

    fun refreshUserStatus(status: Boolean) {
        userLogin(status)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (activity is MainActivity)
            preferencesUtils.unregisterChangePreferences(preferencesListener)
    }

    abstract fun userLogin(status: Boolean)
}