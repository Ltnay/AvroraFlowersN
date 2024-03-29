package com.ltn.avroraflowers.ui.fragments.innerOrderFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ltn.avroraflowers.R
import com.ltn.avroraflowers.adapters.InnerOrderAdapter
import com.ltn.avroraflowers.model.OrderInner
import com.ltn.avroraflowers.ui.base.BaseFragment
import com.ltn.avroraflowers.ui.fragments.innerOrderFragment.presenter.InnerOrderFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.innerOrderFragment.view.InnerOrderFragmentView
import com.ltn.avroraflowers.ui.fragments.innerProductFragment.InnerProductFragment
import com.ltn.avroraflowers.ui.fragments.productsFragment.ProductsFragment
import com.ltn.avroraflowers.utils.Constants
import com.ltn.avroraflowers.utils.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_orders_inner.*

class InnerOrderFragment : BaseFragment(), InnerOrderFragmentView, InnerOrderAdapter.OnCardItemClickListener {

    @InjectPresenter
    lateinit var innerOrderFragmentPresenter: InnerOrderFragmentPresenter

    private lateinit var adapter: InnerOrderAdapter

    companion object {
        val KEY_ID = "id"
        val KEY_DATE = "date"
        val TAG = "InnerOrderFragment"
        fun getInstance(id: Int, date: String): InnerOrderFragment {
            val fragment = InnerOrderFragment()
            val bundle = Bundle()
            bundle.putInt(KEY_ID, id)
            bundle.putString(KEY_DATE, date)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_orders_inner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        innerOrderFragmentPresenter.attach()
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        val orderId = arguments?.getInt(KEY_ID)
        val date = arguments?.getString(KEY_DATE)
        if (orderId != null) {
            setNavigationIcon(toolbarInnerOrder, date!!)
            innerOrderFragmentPresenter.getOrderInfo(orderId)
        }
    }

    private fun initRecycler() {
        adapter = InnerOrderAdapter(this)
        recyclerViewInnerOrder.addItemDecoration(
            GridSpacingItemDecoration(
                1,
                40,
                true,
                0))
        recyclerViewInnerOrder.layoutManager = LinearLayoutManager(activity as Context, RecyclerView.VERTICAL, false)
        recyclerViewInnerOrder.adapter = adapter
    }

    override fun onItemClick(id: Int, title: String) {
        val fragment = InnerProductFragment.getInstance(id, title)
        parentFragment?.childFragmentManager?.beginTransaction()
            ?.add(R.id.fragmentOrdersContainer, fragment, ProductsFragment.TAG)
            ?.show(fragment)
            ?.hide(this)
            ?.addToBackStack(Constants.STACK)
            ?.commit()
    }

    override fun showProductsInOrder(list: List<OrderInner>) {
        adapter.addAllProducts(list)
    }

    override fun showProgress() {
        progressBarInnerOrder.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBarInnerOrder.visibility = View.GONE
    }
}