package com.maycc.rvplatillos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import kotlinx.android.synthetic.main.item_layout_dish.view.*

class DishAdapter(private val dishes: ArrayList<Dish>, var listener: ClickListener, var longListener: LongClickListener) : RecyclerView.Adapter<DishAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_dish, parent, false)

        return ViewHolder(view, listener, longListener)
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

    fun startActionMode() {

    }

    fun destroyActionMode() {

    }

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