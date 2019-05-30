package com.ltn.avroraflowers.ui.fragments.ordersFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.ltn.avroraflowers.R
import com.ltn.avroraflowers.adapters.OrdersAdapter
import com.ltn.avroraflowers.model.Repository.OrdersRepository
import com.ltn.avroraflowers.ui.base.BaseFragment
import com.ltn.avroraflowers.utils.GridSpacingItemDecoration
import com.ltn.avroraflowers.ui.fragments.innerOrderFragment.InnerOrderFragment
import com.ltn.avroraflowers.ui.fragments.ordersFragment.presenter.OrdersFragmentPresenter
import com.ltn.avroraflowers.ui.fragments.ordersFragment.view.OrdersFragmentView
import com.ltn.avroraflowers.utils.Constants
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : BaseFragment(), OrdersFragmentView, OrdersAdapter.OnCardItemClickListener {

    @InjectPresenter
    lateinit var ordersFragmentPresenter: OrdersFragmentPresenter

    private lateinit var ordersAdapter: OrdersAdapter

    companion object {
        val TAG = "OrdersFragment"
        fun getInstance(): OrdersFragment {
            return OrdersFragment()
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ordersFragmentPresenter.attach()
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        (incToolbarOrders as Toolbar).title = resources.getString(R.string.orders_item_nav)
        OrdersRepository.getInstance().callUpdate()
    }

    private fun initRecycler() {
        ordersAdapter = OrdersAdapter(this)
        recyclerViewOrdersFragment.addItemDecoration(
            GridSpacingItemDecoration(
                1,
                40,
                true,
                0
            )
        )
        recyclerViewOrdersFragment.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerViewOrdersFragment.adapter = ordersAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        ordersFragmentPresenter.destroy()
    }

    override fun onItemClick(id: Int, date: String) {
        val fragment = InnerOrderFragment.getInstance(id, date)
        parentFragment?.childFragmentManager?.beginTransaction()
            ?.add(R.id.fragmentOrdersContainer, fragment, InnerOrderFragment.TAG)
            ?.show(fragment)
            ?.hide(this)
            ?.addToBackStack(Constants.STACK)
            ?.commit()
    }

    override fun invalidateRecycler() {
        ordersAdapter.invalidate()
    }

    override fun showProgress() {
        progressBarOrders.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBarOrders.visibility = View.GONE
    }

    override fun showConnectionProblem() {
    }
}