package com.ltn.avroraflowers.dagger

import com.ltn.avroraflowers.dagger.module.*
import com.ltn.avroraflowers.ui.activities.EntryActivity
import com.ltn.avroraflowers.ui.activities.registerActivity.RegisterActivity
import com.ltn.avroraflowers.ui.activities.registerActivity.presenter.RegisterActivityPresenter
import com.ltn.avroraflowers.ui.base.BaseInteractor
import com.ltn.avroraflowers.ui.base.BaseLoginFragment
import com.ltn.avroraflowers.ui.dialogs.InputDialog
import com.ltn.avroraflowers.ui.fragments.cartFragment.presenter.CartFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.catalogFragment.presenter.CatalogFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.entryLoginFragment.EntryLoginFragment
import com.ltn.avroraflowers.ui.fragments.entryLoginFragment.presenter.EntryLoginFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.innerOrderFragment.presenter.InnerOrderFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.innerProductFragment.InnerProductFragment
import com.ltn.avroraflowers.ui.fragments.innerProductFragment.presenter.InnerProductFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.ordersFragment.presenter.OrdersFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.productsFragment.presenter.ProductsFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.searchFragment.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = arrayOf(
        ContextModule::class,
        UtilsModule::class,
        NetworkModule::class,
        InteractorsModule::class,
        FieldsValidatorModule::class,
        PreferencesModule::class
    )
)
@Singleton
interface ApplicationComponent {

    fun inject(entryActivity: EntryActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(entryLoginFragment: EntryLoginFragment)

    //IN PRESENTER
    fun inject(entryLoginFragmentPresenter: EntryLoginFragmentPresenter)

    fun inject(registerActivityPresenter: RegisterActivityPresenter)
    fun inject(catalogFragmentPresenter: CatalogFragmentPresenter)
    fun inject(productsFragmentPresenter: ProductsFragmentPresenter)
    fun inject(cartFragmentPresenter: CartFragmentPresenter)
    fun inject(ordersFragmentPresenter: OrdersFragmentPresenter)
    fun inject(innerOrderFragmentPresenter: InnerOrderFragmentPresenter)
    fun inject(innerProductFragmentPresenter: InnerProductFragmentPresenter)
    fun inject(innerProductFragment: InnerProductFragment)
    fun inject(inputDialog: InputDialog)
    fun inject(searchFragment: SearchFragment)

    fun inject(baseLoginFragment: BaseLoginFragment)
    //IN INTERACTOR
    fun inject(baseInteractor: BaseInteractor)
}