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

class DishAdapter(private val context: Context, private val dishes: ArrayList<Dish>) : RecyclerView.Adapter<DishAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout_dish, parent, false)

        return ViewHolder(view)
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ivPhoto:    ImageView    = view.ivPhoto
        val tvNameDish: TextView     = view.tvNameDish
        val tvPrice:    TextView     = view.tvPrice
        val ratingDish: RatingBar    = view.ratingDish
    }
}