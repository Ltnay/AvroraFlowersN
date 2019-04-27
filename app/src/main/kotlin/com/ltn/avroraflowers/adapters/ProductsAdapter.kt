package com.ltn.avroraflowers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ltn.avroraflowers.R
import com.ltn.avroraflowers.model.Category
import com.squareup.picasso.Picasso

//fixme
class ProductsAdapter(private val onClickCardListener: OnCardItemClickListener) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var products: MutableList<Category> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun addCategory(category: Category) {
        products.add(category)
        notifyDataSetChanged()
    }

    fun addAll(categories: List<Category>) {
        products.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get()
            .load(products[position].image)
            .fit()
            .centerCrop()
            .into(holder.imageCategory)

        holder.titleCategory.text = products[position].title
        holder.categoryCardItem.setOnClickListener { onClickCardListener.onItemClick(products[position]._id) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryCardItem = itemView.findViewById<MaterialCardView>(R.id.categoryCardItem)
        val imageCategory = itemView.findViewById<AppCompatImageView>(R.id.imageItemCategoryRecycler)
        val titleCategory = itemView.findViewById<TextView>(R.id.textViewItemCategoryRecycler)
    }

    interface OnCardItemClickListener {
        fun onItemClick(id: Int)
    }
}