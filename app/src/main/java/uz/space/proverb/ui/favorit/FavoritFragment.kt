package uz.space.proverb.ui.favorit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.gson.GsonBuilder
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ActionBarBinding
import uz.space.proverb.databinding.FragmentDescriptionBinding
import uz.space.proverb.databinding.FragmentFavoritBinding
import uz.space.proverb.ui.DescriptionFragmentArgs

class FavoritFragment : Fragment() {

    private lateinit var binding: FragmentFavoritBinding
    private lateinit var actBinding: ActionBarBinding
    private lateinit var navController: NavController
    private val adapter = FavoritAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritBinding.bind(view)
        actBinding = ActionBarBinding.bind(view)
        navController = Navigation.findNavController(view)

        actBinding.apply {
            btnHome.setOnClickListener {
                navController.popBackStack()
                Channel.proverbs.clear()
            }
            actionBarTitle.text = context?.getString(R.string.favorits)
        }

        adapter.setOnItemClickListener { model ->
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonString = gsonPretty.toJson(
                Proverb(
                    id = model.id,
                    proverb = model.proverb,
                    description = model.description,
                    favorit = model.favorit,
                    allText = model.allText
                )
            )
            val action =
                FavoritFragmentDirections.actionFavoritFragmentToDescriptionFragment(jsonString)
            navController.navigate(action)
        }

        binding.apply {
            rvFavorits.adapter = adapter
        }

        if (Channel.proverbs.isNotEmpty()) {
            adapter.models = Channel.proverbs
        } else {
            Toast.makeText(requireContext(), context?.getString(R.string.favorite_list_is_empty), Toast.LENGTH_SHORT).show()
        }
    }

}