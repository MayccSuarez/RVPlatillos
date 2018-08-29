package com.maycc.rvplatillos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dishes = ArrayList<Dish>()
    private lateinit var adapterDish: DishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        initRecyclerViewDishes()
        initSwipeRefresh()
    }

    private fun loadData() {
        dishes.add(Dish(R.drawable.cangrejada, "Cangrejada", 4.50, 4f))
        dishes.add(Dish(R.drawable.come_bebe, "Come y bebe", 2.50, 3f))
        dishes.add(Dish(R.drawable.encebollado, "Encebollado", 3.50, 3.5f))
        dishes.add(Dish(R.drawable.flan_coco, "Flan de coco", 2.00, 4f))
        dishes.add(Dish(R.drawable.torta_tres_leches, "Torta de tres leches", 5.50, 4.5f))
        dishes.add(Dish(R.drawable.dulce_higos, "Dulce de higos", 3.50, 4f))
        dishes.add(Dish(R.drawable.bolon_verde, "Bolón de verde", 3.50, 4f))
        dishes.add(Dish(R.drawable.ceviche, "Ceviche", 2.50, 4.5f))
        dishes.add(Dish(R.drawable.churrasco, "Churrasco", 4.80, 4.5f))
    }

    private fun initRecyclerViewDishes() {
        val linearLayoutManager = LinearLayoutManager(this)
        adapterDish = DishAdapter(dishes, object: ClickListener{
            override fun onClick(view: View, index: Int) {
                Toast.makeText(applicationContext, dishes[index].name, Toast.LENGTH_SHORT).show()
            }
        })

        rvDishes.apply {
            setHasFixedSize(true)
            adapter = adapterDish
            layoutManager = linearLayoutManager
        }
    }

    private fun initSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            fetchData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun fetchData() {
        dishes.add(0, Dish(R.drawable.ceviche, "Ceviche", 2.50, 4.5f))
        adapterDish.notifyDataSetChanged()
    }
}
