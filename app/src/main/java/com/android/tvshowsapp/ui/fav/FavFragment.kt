package com.android.tvshowsapp.ui.fav

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.android.tvshowsapp.R
import com.android.tvshowsapp.data.model.Show
import com.android.tvshowsapp.databinding.FragmentFavBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment(R.layout.fragment_fav) {

    private val viewModel by viewModels<FavViewModel>()

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavBinding.bind(view)

        val adapter = FavAdapter(FavAdapter.ShowClickListener{ favShow ->
            val show = Show(
                    favShow.id,
                    favShow.name,
                    favShow.start_date,
                    favShow.country,
                    favShow.satus,
                    favShow.image_thumbnail_path
            )
            findNavController().navigate(FavFragmentDirections.actionFavFragmentToDetailFragment(show))
        })

        binding.apply {
            this!!.favList.setHasFixedSize(true)
            favList.adapter = adapter
        }

        viewModel.shows.observe(viewLifecycleOwner){ data ->
            adapter.submitList(data)
        }

        ItemTouchHelper(object  : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val show = adapter.currentList[position]
                viewModel.delete(show)
                Snackbar.make(binding!!.root, "deleted!", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        viewModel.insert(show)
                    }
                    show()
                }
            }
        }).attachToRecyclerView(binding!!.favList)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_delete_all -> viewModel.deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}