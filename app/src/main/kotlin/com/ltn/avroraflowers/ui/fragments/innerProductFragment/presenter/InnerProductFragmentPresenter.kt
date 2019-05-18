package com.ltn.avroraflowers.ui.fragments.innerProductFragment.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.ltn.avroraflowers.App
import com.ltn.avroraflowers.model.Product
import com.ltn.avroraflowers.ui.base.BasePresenter
import com.ltn.avroraflowers.ui.fragments.innerProductFragment.interactor.InnerProductFragmentInteractor
import com.ltn.avroraflowers.ui.fragments.innerProductFragment.interactor.OnRequestProductListener
import com.ltn.avroraflowers.ui.fragments.innerProductFragment.view.InnerProductFragmentView
import javax.inject.Inject

@InjectViewState
class InnerProductFragmentPresenter: BasePresenter<InnerProductFragmentView>(), IInnerProductFragmentPresenter {

    @Inject
    lateinit var innerProductFragmentInteractor: InnerProductFragmentInteractor

    override fun attach() {
        App.component.inject(this)
    }

    override fun getProduct(id: Int) {
        innerProductFragmentInteractor.requestProductFromServer(id, object : OnRequestProductListener {
            override fun onRequestStart() {
                viewState.showProgress()
            }

            override fun onSuccessful(product: Product) {
                viewState.showProductInfo(product)
                Log.d("GLL", "ok")
            }

            override fun onFailure() {
                viewState.showConnectionProblem()
                Log.d("GLL", "fail")
            }

            override fun onRequestEnded() {
                viewState.hideProgress()
            }
        })
    }

    override fun detach() {
    }

    override fun destroy() {
    }
}