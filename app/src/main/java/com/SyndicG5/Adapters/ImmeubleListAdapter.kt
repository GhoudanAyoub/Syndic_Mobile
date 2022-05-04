package com.SyndicG5.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.SyndicG5.databinding.SelectedRadioButtonItemBinding
import com.SyndicG5.ui.util.KarnyRadioButton
import com.syndicg5.networking.models.Immeuble

class ImmeubleListAdapter:
    RecyclerView.Adapter<ImmeubleListAdapter.ImmeubleItemViewHolder>() {

    private var ImmeublesList: List<Immeuble>? = null
    private var checkedItems = arrayListOf<SelectedRadioButtonItemBinding>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImmeubleItemViewHolder {

        val binding = SelectedRadioButtonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImmeubleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImmeubleItemViewHolder, position: Int) {
        val Immeuble = ImmeublesList?.get(position)
        Immeuble?.let {
            holder.bind(
                KarnyRadioButton.KarnyRadioButtonData(
                    title = it.nom
                ),
            )

            holder.itemView.setOnClickListener {
                setImmeubleClickListener?.let {
                    it(Immeuble)
                    checkedItems[position].root.isChecked(true)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return ImmeublesList?.size ?: 0
    }

    fun setImmeublesList(Immeubles: List<Immeuble>) {
        this.ImmeublesList = Immeubles
        notifyDataSetChanged()
    }

    fun setAllItemsChecked(isChecked: Boolean) {
        checkedItems.forEachIndexed { index, item ->
            item.root.isChecked(isChecked)
            notifyItemChanged(index)
        }
    }

    private var setImmeubleClickListener: ((Immeuble: Immeuble) -> Boolean)? = null

    fun onImmeubleClicked(listener: (Immeuble) -> Boolean) {
        setImmeubleClickListener = listener
    }

    inner class ImmeubleItemViewHolder(private val ImmeubleItemBinding: SelectedRadioButtonItemBinding) :
        RecyclerView.ViewHolder(ImmeubleItemBinding.root) {


        fun bind(ImmeubleItemData: KarnyRadioButton.KarnyRadioButtonData) {
            checkedItems.add(ImmeubleItemBinding)
            ImmeubleItemBinding.root.bindView(ImmeubleItemData) { isChecked ->
                if (isChecked) {
                    for (ImmeubleItem in checkedItems) {
                        if (ImmeubleItem != ImmeubleItemBinding) {
                            ImmeubleItem.root.isChecked(false)
                        } else {
                            ImmeubleItem.root.isChecked(true)
                           // callback.invoke()
                        }
                    }
                }
            }
        }
    }
}
