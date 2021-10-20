package uz.space.proverb.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.space.proverb.R
import uz.space.proverb.databinding.ActionBarBinding
import uz.space.proverb.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var actBinding: ActionBarBinding
    private lateinit var binding: FragmentAboutBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)
        actBinding = ActionBarBinding.bind(view)
        navController = Navigation.findNavController(view)
        actBinding.apply {
            btnHome.setOnClickListener {
                navController.popBackStack()
            }

            actionBarTitle.text = context?.getString(R.string.about)

        }
    }

}