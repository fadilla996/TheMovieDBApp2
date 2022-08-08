package id.iglo.themoviedbapp.fragment.genre

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.iglo.common.ui.BaseFragment
import id.iglo.themoviedbapp.R
import id.iglo.themoviedbapp.databinding.LayoutGenreFragmentBinding
import id.iglo.themoviedbapp.view_model.genre.GenreViewModel

@AndroidEntryPoint
class GenreFragment : BaseFragment<GenreViewModel, LayoutGenreFragmentBinding>() {
    override val vm: GenreViewModel by viewModels()

    override val layoutResourceId: Int = R.layout.layout_genre_fragment

    val adapter = GenreAdapter {
        vm.selectionTracker?.isSelected(it) ?: false
    }

    override fun initBinding(binding: LayoutGenreFragmentBinding) {
        super.initBinding(binding)
        observeLiveData()
    }

}