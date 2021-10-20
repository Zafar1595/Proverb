package uz.space.proverb.ui.favorit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ItemFavoritListBinding
import uz.space.proverb.databinding.ItemProverbBinding
import uz.space.proverb.ui.MainAdapter

class FavoritAdapter : RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {

    var models: List<Proverb> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var onItemClick: (model: Proverb) -> Unit = {}
    fun setOnItemClickListener(onItemClick: (model: Proverb) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class FavoritViewHolder(private val binding: ItemFavoritListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(model: Proverb) {
            binding.apply {
                tvProverb.text = model.proverb
                tvDescription.text = model.proverb + " - " + model.description
                itemView.setOnClickListener {
                    onItemClick.invoke(model)
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorit_list, parent, false)
        val binding = ItemFavoritListBinding.bind(itemView)
        return FavoritViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount(): Int = models.size
}