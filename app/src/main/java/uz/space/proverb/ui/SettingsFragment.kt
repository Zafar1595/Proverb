package uz.space.proverb.ui

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ActionBarSettingBinding
import uz.space.proverb.databinding.FragmentSettingsBinding
import uz.space.proverb.settings.Settings

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var actBinding: ActionBarSettingBinding
    private lateinit var navController: NavController
    private val args: SettingsFragmentArgs by navArgs()
    private lateinit var model: Proverb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val strModel = args.model
        val gson = Gson()
        model = gson.fromJson(strModel, Proverb::class.java)

        var textSize = 0F
        var textSizeTitle = 0F
        binding = FragmentSettingsBinding.bind(view)
        actBinding = ActionBarSettingBinding.bind(view)
        binding.apply {
            seekBar.progress =
                Settings().getTextSize(Settings.TEXT_SIZE_DESCRIPTON, requireContext()).toInt()
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    textSize = progress.toFloat()
                    tvDescription.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress.toFloat())

                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
//                    TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
//                    TODO("Not yet implemented")
                }
            })
            tvProverb.text = model.proverb
            tvDescription.text = model.proverb + " - " + model.description
            tvProverb.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                Settings().getTextSize(Settings.TEXT_SIZE_TITLE, requireContext())
            )
            tvDescription.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                Settings().getTextSize(Settings.TEXT_SIZE_DESCRIPTON, requireContext())
            )
            seekBarTitle.progress =
                Settings().getTextSize(Settings.TEXT_SIZE_TITLE, requireContext()).toInt()
            seekBarTitle.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    tvProverb.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP, progress.toFloat()
                    )
                    textSizeTitle = progress.toFloat()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
//                    TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
//                    TODO("Not yet implemented")
                }
            })

            btnDefSettings.setOnClickListener { useDefaultSettings() }
        }
        actBinding.apply {
            btnHome.setOnClickListener {
                navController.popBackStack()
            }
            actionBarTitle.text = context?.getString(R.string.settings)
            btnSave.setOnClickListener {
                Settings().setTextSize(Settings.TEXT_SIZE_TITLE, textSizeTitle, requireContext())

                Settings().setTextSize(Settings.TEXT_SIZE_DESCRIPTON, textSize, requireContext())
                Toast.makeText(
                    requireContext(),
                    context?.getString(R.string.settings_saved),
                    Toast.LENGTH_SHORT
                ).show()
                navController.popBackStack()
            }
        }
    }

    private fun useDefaultSettings() {
        Settings().setTextSize(
            Settings.TEXT_SIZE_TITLE,
            Settings.DEFAULT_TEXT_SIZE_TITLE,
            requireContext()
        )
        Settings().setTextSize(
            Settings.TEXT_SIZE_DESCRIPTON,
            Settings.DEFAULT_TEXT_SIZE_DESCRIPTION,
            requireContext()
        )

        binding.apply {

            seekBar.progress = Settings.DEFAULT_TEXT_SIZE_DESCRIPTION.toInt()
            seekBarTitle.progress = Settings.DEFAULT_TEXT_SIZE_TITLE.toInt()

            tvTest.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                Settings().getTextSize(Settings.TEXT_SIZE_DESCRIPTON, requireContext())
            )
            tvProverb.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                Settings().getTextSize(Settings.TEXT_SIZE_TITLE, requireContext())
            )
            tvDescription.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                Settings().getTextSize(Settings.TEXT_SIZE_DESCRIPTON, requireContext())
            )
        }
    }
}