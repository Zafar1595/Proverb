package uz.space.proverb.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.GsonBuilder
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ActionBarBinding
import uz.space.proverb.databinding.ActionBarMainBinding
import uz.space.proverb.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var adapter = MainAdapter()
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var actBinding: ActionBarMainBinding

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
//            serchView
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { proverbs ->
            adapter.models = proverbs
            Log.d("zafar", proverbs.isEmpty().toString())
        })

        adapter.setOnItemClickListener { model ->
            val gsonPretty = GsonBuilder().setPrettyPrinting().create()
            val jsonString = gsonPretty.toJson(
                Proverb(
                    id = model.id,
                    proverb = model.proverb,
                    description = model.description
                )
            )
            val action = MainFragmentDirections.actionMainFragmentToDescriptionFragment(jsonString)
            navController.navigate(action)
        }
    }

}