package com.ltn.avroraflowers.ui.fragments.catalogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ltn.avroraflowers.R
import com.ltn.avroraflowers.ui.base.BaseFragment

class CatalogFragment : BaseFragment() {

    companion object {
        fun newInstance(): CatalogFragment {
            return CatalogFragment()
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }
}