package uz.space.proverb.ui.main

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.space.proverb.R
import uz.space.proverb.data.Proverb
import uz.space.proverb.databinding.ItemProverbBinding
import uz.space.proverb.settings.Settings

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
    private var onItemClickFavorit:(model: Proverb) -> Unit = {}
    fun setOnFavoritClickListener(onItemClickFavorit:(model: Proverb) -> Unit){
        this.onItemClickFavorit = onItemClickFavorit
    }

    inner class MainViewHolder(private val binding: ItemProverbBinding): RecyclerView.ViewHolder(binding.root){
        fun populateModel(model: Proverb){
            binding.apply {
                tvProverb.text = model.proverb
                tvDescription.text = model.allText

                tvProverb.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    Settings().getTextSize(Settings.TEXT_SIZE_TITLE, itemView.context)
                )
                tvDescription.setTextSize(
                    TypedValue.COMPLEX_UNIT_SP,
                    Settings().getTextSize(Settings.TEXT_SIZE_DESCRIPTON, itemView.context)
                )

                itemView.setOnClickListener {
                    onItemClick.invoke(model)
                }
                if(model.favorit == 0){
                    imgFavorit.setImageResource(R.drawable.ic_bookmark_default)
                }else{
                    imgFavorit.setImageResource(R.drawable.ic_bookmark)
                }
                imgFavorit.setOnClickListener {
                    if(model.favorit == 0) imgFavorit.setImageResource(R.drawable.ic_bookmark)
                    else imgFavorit.setImageResource(R.drawable.ic_bookmark_default)
                    onItemClickFavorit.invoke(model)
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
