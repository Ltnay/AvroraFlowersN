package com.ltn.avroraflowers.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ltn.avroraflowers.R
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun loadFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commit()
    }
}