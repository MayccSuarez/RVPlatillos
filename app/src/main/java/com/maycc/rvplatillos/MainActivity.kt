package com.maycc.rvplatillos

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.support.v7.view.ActionMode
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dishes = ArrayList<Dish>()
    private lateinit var adapterDish: DishAdapter

    lateinit var callback: ActionMode.Callback
    var isActionMode = false
    var myMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        initRecyclerViewDishes()
        initSwipeRefresh()
        initActionMode()
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
        }, object: LongClickListener{
            override fun longClick(view: View, index: Int) {
                adapterDish.selectItems(index, view)

                if (!isActionMode) {
                    startSupportActionMode(callback)
                    isActionMode = true
                } else {
                    myMode?.title = "${adapterDish.getItemsSelected()} Seleccionados"
                }


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

    private fun initActionMode() {
        callback = object: ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                // Called after startActionMode
                // Inflate a menu resource providing context menu items
                menuInflater.inflate(R.menu.menu_action, menu)
                myMode = mode
                mode?.finish()
                return true
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                // Called when the user selects a contextual menu item
                when (item?.itemId) {
                    R.id.itemDelete -> {
                        adapterDish.deleteItemsSelected()
                    }
                }

                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                // Called each time the action mode is shown
                mode?.title = "1  Seleccionado"
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                // Called when the action mode is finished
                Toast.makeText(applicationContext, "ON DESTROY MODE", Toast.LENGTH_SHORT).show()
                isActionMode = false
                adapterDish.clear()
            }
        }
    }
}
