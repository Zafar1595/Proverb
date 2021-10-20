package uz.space.proverb.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.FragmentMainBinding
import uz.space.proverb.databinding.ItemProverbBinding

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var models = listOf<Proverb>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private var onItemClick:(model: Proverb) -> Unit = {}
    fun setOnItemClickListener(onItemClick:(model: Proverb) -> Unit){
        this.onItemClick = onItemClick
    }

    inner class MainViewHolder(private val binding: ItemProverbBinding): RecyclerView.ViewHolder(binding.root){
        fun populateModel(model: Proverb){
            binding.apply {
                tvProverb.text = model.proverb
                tvDescription.text = model.proverb + "-" + model.description
                itemView.setOnClickListener {
                    onItemClick.invoke(model)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_proverb, parent, false)
        val binding = ItemProverbBinding.bind(itemView)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size
}
