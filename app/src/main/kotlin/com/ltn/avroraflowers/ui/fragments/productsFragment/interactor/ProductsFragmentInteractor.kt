package com.ltn.avroraflowers.ui.fragments.productsFragment.interactor

import android.util.Log
import com.ltn.avroraflowers.model.Repository.CartProductsRepository
import com.ltn.avroraflowers.network.RequestBody.AddToCart
import com.ltn.avroraflowers.ui.base.BaseInteractor
import com.ltn.avroraflowers.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsFragmentInteractor : BaseInteractor(), IProductsFragmentInteractor {

    override fun requestProductsByCategoryId(id: Int, onRequestProductsListener: OnRequestProductsListener) {
        disposable = apiAvrora.getProductsByCategoryId(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRequestProductsListener.onRequestStart() }
            .doFinally { onRequestProductsListener.onRequestEnded() }
            .doOnError { onRequestProductsListener.onFailure() }
            .subscribe(
                { result ->
                    onRequestProductsListener.onSuccessful(result)
                    disposable.dispose()
                },
                {
                    disposable.dispose()
                }
            )
    }

    override fun requestAddProductToCart(
        id: Int,
        count: Int,
        perPack: Int,
        onAddToCartProductsListener: OnAddToCartProductsListener
    ) {
        val addToCardBody = AddToCart(id, count, perPack)
        disposable = apiAvrora.addProductInCart(Constants.TEST_TOKEN, addToCardBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onAddToCartProductsListener.onRequestStart() }
            .doFinally { onAddToCartProductsListener.onRequestEnded() }
            .doOnError { onAddToCartProductsListener.onFailure() }
            .subscribe()
    }
}