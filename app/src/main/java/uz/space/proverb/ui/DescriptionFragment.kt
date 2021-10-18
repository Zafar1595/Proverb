package uz.space.proverb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ActionBarBinding
import uz.space.proverb.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {

    private val args: DescriptionFragmentArgs by navArgs()
    private lateinit var model: Proverb
    private lateinit var binding: FragmentDescriptionBinding
    private lateinit var actBinding: ActionBarBinding
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view)
        actBinding = ActionBarBinding.bind(view)
        navController = Navigation.findNavController(view)

        var strModel = args.model
        val gson = Gson()
        model = gson.fromJson(strModel, Proverb::class.java)

        binding.apply {
            tvDescription.text = model.description
        }

        actBinding.apply {
            btnHome.setOnClickListener {
                navController.popBackStack()
            }
        }

    }

}