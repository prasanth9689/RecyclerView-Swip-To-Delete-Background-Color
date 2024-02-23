package com.skyblue.recyclerview_swip_to_delete_background_color

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections

class MainActivity : AppCompatActivity() {
    private lateinit var items: ArrayList<String>
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var itemsArrayList: ArrayList<Post>
    var context: Context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        itemsArrayList = arrayListOf()

        items = arrayListOf(
            "position 01",
            "position 02",
            "position 03",
            "position 04",
            "position 05",
            "position 06",
            "position 07",
            "position 08",
            "position 09",
            "position 10"
        )

        for (i in items.indices) {
            val item = Post(items[i])
            itemsArrayList.add(item)
        }

        myAdapter = MyAdapter(itemsArrayList)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration( context, LinearLayoutManager.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = myAdapter


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                source: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val sourcePosition = source.adapterPosition
                val targetPosition = target.adapterPosition

                Collections.swap(itemsArrayList,sourcePosition,targetPosition)
                myAdapter.notifyItemMoved(sourcePosition,targetPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.deleteItem(viewHolder.adapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {


                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.my_color
                        )
                    )
                    .addActionIcon(R.drawable.ic_delete)
                    .create()
                    .decorate()


                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}