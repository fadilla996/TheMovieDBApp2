package id.iglo.themoviedbapp.fragment.genre

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy

fun GenreFragment.observeLiveData() {
    binding.recycler.adapter = adapter
    vm.selectionTracker = vm.selectionTracker?.let {
        createTracker().apply {
            it.selection.forEach{
                this.select(it)
            }
        }
    } ?: run { createTracker() }

    observeResponseData(vm.genreData, success = {
        binding.loadingContainer.visibility = View.GONE
        binding.recycler.visibility = View.VISIBLE
        binding.fabToMovie.visibility = View.VISIBLE
        binding.fabToMovie.setOnClickListener {
            findNavController().navigate(
                GenreFragmentDirections.toDiscover(
                    vm.selectionTracker?.selection?.toMutableList().orEmpty().joinToString(",")
                )
            )
        }
        adapter.listDiffer.submitList(it)
    }, error = {
        binding.loadingContainer.visibility = View.GONE
        binding.recycler.visibility = View.GONE
        binding.retryButton.visibility = View.VISIBLE
        binding.retryButton.setOnClickListener {
            vm.getAllGenres()
        }
    }, loading = {
        binding.loadingContainer.visibility = View.VISIBLE
        binding.recycler.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
    })
}

private fun GenreFragment.createTracker() =
    SelectionTracker.Builder<Long>(
        this::class.java.name,
        binding.recycler,
        adapter.getItemKeyProvider(),
        GenreItemDetailsLookUp(binding.recycler),
        StorageStrategy.createLongStorage()
    ).withOnItemActivatedListener{ a, _ ->
        a.selectionKey?.let {
            vm.selectionTracker?.select(it)
        }
        true
    }.withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()