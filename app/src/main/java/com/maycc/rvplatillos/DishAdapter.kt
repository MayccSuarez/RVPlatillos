package com.maycc.rvplatillos

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import kotlinx.android.synthetic.main.item_layout_dish.view.*

class DishAdapter(private val dishes: ArrayList<Dish>, var listener: ClickListener, var longListener: LongClickListener) : RecyclerView.Adapter<DishAdapter.ViewHolder>(){

    var indexesSelected = ArrayList<Int>()
    lateinit var holder: ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_dish, parent, false)

        holder = ViewHolder(view, listener, longListener)
        return holder
    }

    override fun getItemCount(): Int {
        return dishes.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dish = dishes[position]

        holder.ivPhoto.setImageResource(dish.photo)
        holder.tvNameDish.text      = dish.name
        holder.tvPrice.text         = "$" + dish.price
        holder.ratingDish.rating    = dish.rating

    }

    fun selectItems(index: Int) {
        if (indexesSelected.contains(index)) {
            indexesSelected.remove(index)
        } else {
            indexesSelected.add(index)
        }
    }

    fun deleteItemsSelected() {
        for (i in indexesSelected) {
            Log.d("DELETE ITEMS", i.toString())
        }
        clear()
    }

    fun clear() {
        indexesSelected.clear()
    }

    fun getItemsSelected() = indexesSelected.count()

    class ViewHolder(view: View, var listener: ClickListener, var longListener: LongClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener{
        val ivPhoto:    ImageView    = view.ivPhoto
        val tvNameDish: TextView     = view.tvNameDish
        val tvPrice:    TextView     = view.tvPrice
        val ratingDish: RatingBar    = view.ratingDish

        init {
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            longListener.longClick(v!!, adapterPosition)
            return true
        }
    }
}