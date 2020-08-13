package com.example.restaurant.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.dsm.androidcomponent.base.BaseFragment
import com.example.model.MenuCategory
import com.example.restaurant.R
import com.example.restaurant.adapter.RestaurantMenuAdapter
import com.example.restaurant.databinding.FragmentMenuBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuFragment: BaseFragment<FragmentMenuBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_menu

    //TODO: make items for menu and check for menu rv adapter is working with tab layout

    private val viewModel: RestaurantViewModel by sharedViewModel()
    private val menuAdapter by lazy { RestaurantMenuAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        recyclerview_menu.adapter = menuAdapter
        recyclerview_menu.layoutManager = GridLayoutManager(context, 2)

        viewModel.getMenuCategory()

        viewModel.menuCategoryList.observe(viewLifecycleOwner, Observer {
            it.map {
                tablayout_menu_category.addTab(tablayout_menu_category.newTab().setText(it.name))
            }
            tablayout_menu_category.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val category = it.filter {
                        it.name == tab?.text
                    }
                    viewModel.getMenu(category[0].menuCategoryId)
                }
            })
        })
    }
}