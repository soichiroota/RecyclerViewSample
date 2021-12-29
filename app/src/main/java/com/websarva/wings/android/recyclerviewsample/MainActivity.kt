package com.websarva.wings.android.recyclerviewsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.setTitle(R.string.toolbar_title)

        val toolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        toolbarLayout.title = getString(R.string.toolbar_title)
        toolbarLayout.setExpandedTitleColor(Color.WHITE)
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY)

        val lvMenu = findViewById<RecyclerView>(R.id.lvMenu)
        val layout = LinearLayoutManager(this@MainActivity)
        lvMenu.layoutManager = layout
        val menuList = createTeishokuList()
        val adapter = RecyclerListAdapter(menuList)
        lvMenu.adapter = adapter

        val decorator = DividerItemDecoration(this@MainActivity, layout.orientation)
        lvMenu.addItemDecoration(decorator)
    }

    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>(
            "name" to "から揚げ定食",
            "price" to 800,
            "desc" to "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。"
        )
        menuList.add(menu)
        menu = mutableMapOf<String, Any>(
            "name" to "ハンバーグ定食",
            "price" to 850,
            "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。"
        )
        menuList.add(menu)
        return menuList
    }

    private inner class RecyclerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var _tvMenuNameRow: TextView
        var _tvMenuPriceRow: TextView
        init {
            _tvMenuNameRow = itemView.findViewById(R.id.tvMenuNameRow)
            _tvMenuPriceRow = itemView.findViewById(R.id.tvMenuPriceRow)
        }
    }

    private inner class RecyclerListAdapter(
        private val _listData: MutableList<MutableMap<String, Any>>
        ): RecyclerView.Adapter<RecyclerListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
            val inflater = LayoutInflater.from(this@MainActivity)
            val view = inflater.inflate(R.layout.row, parent, false)
            view.setOnClickListener(ItemClickListener())
            val holder = RecyclerListViewHolder(view)
            return holder
        }

        override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
            val item = _listData[position]
            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int
            val menuPriceStr = menuPrice.toString()
            holder._tvMenuNameRow.text = menuName
            holder._tvMenuPriceRow.text = menuPriceStr
        }

        override fun getItemCount(): Int {
            return _listData.size
        }

        }

    private inner class ItemClickListener: View.OnClickListener {
        override fun onClick(view: View) {
            val tvMenuName = view.findViewById<TextView>(R.id.tvMenuNameRow)
            val menuName = tvMenuName.text.toString()
            val msg = getString(R.string.msg_header) + menuName
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}