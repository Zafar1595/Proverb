package uz.space.proverb.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import uz.space.proverb.R
import uz.space.proverb.databinding.ActionBarBinding
import uz.space.proverb.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var actBinding: ActionBarBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var textSize = 0F
        binding = FragmentSettingsBinding.bind(view)
        actBinding = ActionBarBinding.bind(view)
        binding.apply {
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    tvTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress.toFloat())
                    textSize = progress.toFloat()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
//                    TODO("Not yet implemented")
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
//                    TODO("Not yet implemented")
                }
            })
            btnSave.setOnClickListener {
//                var sp: SharedPreferences.Editor = getString(R.string.MySettings,)
            }
        }
    }

}