package uz.space.proverb.ui.main

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.GsonBuilder
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ActionBarMainBinding
import uz.space.proverb.databinding.FragmentMainBinding
import uz.space.proverb.ui.favorit.Channel
import java.util.*


class MainFragment : Fragment() {

    private var adapter = MainAdapter()
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var actBinding: ActionBarMainBinding
    private lateinit var modelList: List<Proverb>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        actBinding = ActionBarMainBinding.bind(view)

        navController = Navigation.findNavController(view)


        binding.apply {
            rvProverb.adapter = adapter
        }

        actBinding.apply {
            serchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    if (p0 != null) {
                        search(p0)
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0 != null) {
                        search(p0)
                    }
                    return false
                }
            })

            imgFavorit.setOnClickListener {
                var favoritList: MutableList<Proverb> = mutableListOf()
                modelList.forEach {
                    if (it.favorit == 1) favoritList.add(it)
                }
                Channel.proverbs = favoritList
                val action = MainFragmentDirections.actionMainFragmentToFavoritFragment()
                navController.navigate(action)
            }

        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { proverbs ->
            adapter.models = proverbs
            modelList = proverbs
        })

        adapter.setOnItemClickListener { model ->
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonString = gsonPretty.toJson(
                Proverb(
                    id = model.id,
                    proverb = model.proverb,
                    description = model.description,
                    favorit = model.favorit
                )
            )
            val action = MainFragmentDirections.actionMainFragmentToDescriptionFragment(jsonString)
            navController.navigate(action)
        }

        adapter.setOnFavoritClickListener {
            val isFavorit = if (it.favorit == 0) 1 else 0
            viewModel.selectFavorit(Proverb(it.id, it.proverb, it.description, isFavorit))
        }

        actBinding.apply {
            imgMore.setOnClickListener {
                val popup = PopupMenu(requireContext(), imgMore)
                popup.menuInflater.inflate(R.menu.menu, popup.menu)

                popup.setOnMenuItemClickListener {
                    if (it.itemId == R.id.mInfo) {
                        val action = MainFragmentDirections.actionMainFragmentToAboutFragment()
                        navController.navigate(action)
                    } else if (it.itemId == R.id.mSettings) {
                        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
                        val jsonString = gsonPretty.toJson(
                            Proverb(
                                id = modelList[0].id,
                                proverb = modelList[0].proverb,
                                description = modelList[0].description,
                                favorit = modelList[0].favorit
                            )
                        )
                        val action =
                            MainFragmentDirections.actionMainFragmentToSettingsFragment(jsonString)
                        navController.navigate(action)
                    }
                    true
                }
                popup.show()

            }
        }

    }

    private fun search(proverb: String) {
        var str = "%$proverb%"

        viewModel.searchDatabase(str).observe(this, { list ->
            list.let {
                adapter.models = it
            }
        })
//        val listSorted: MutableList<Proverb> = mutableListOf()
//
//        modelList.forEach {
//            if (it.proverb.lowercase(Locale.ROOT).contains(proverb.lowercase(Locale.ROOT))) {
//                listSorted.add(it)
//            }
//        }
//        adapter.models = listSorted
    }


}