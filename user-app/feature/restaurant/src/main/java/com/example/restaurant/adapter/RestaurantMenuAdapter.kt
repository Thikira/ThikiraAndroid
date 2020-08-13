package com.example.restaurant.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Menu
import com.example.restaurant.databinding.ItemMenuBinding
import com.example.restaurant.viewmodel.RestaurantViewModel

class RestaurantMenuAdapter(
    private val viewModel: RestaurantViewModel
) : RecyclerView.Adapter<MenuViewHolder>() {
    var menus: List<Menu> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder =
        MenuViewHolder(ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = menus.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) = holder.bind()
}

class MenuViewHolder(private val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.root.setOnClickListener {
            //TODO add activity or dialog for menu
        }
    }
}
