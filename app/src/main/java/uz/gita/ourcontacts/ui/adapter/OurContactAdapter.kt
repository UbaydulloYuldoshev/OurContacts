package uz.gita.ourcontacts.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.ourcontacts.R
import uz.gita.ourcontacts.data.model.response.ContactData

///
class OurContactAdapter : ListAdapter<ContactData, OurContactAdapter.ContactViewHolder>(DiffItem){
    var clickListener : ((Int,Int) -> Unit)?= null

    object DiffItem : DiffUtil.ItemCallback<ContactData>() {
        override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
            return oldItem == newItem
        }
    }

    inner class ContactViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val textUserName = itemView.findViewById<TextView>(R.id.textUserName)
        private val textPhoneNumber = itemView.findViewById<TextView>(R.id.textPhoneNumber)
        init {
            itemView.setOnClickListener {
                clickListener?.invoke(adapterPosition,getItem(adapterPosition).id)
            }
        }

        fun bind() {
            val data = getItem(adapterPosition)
            textPhoneNumber.text = data.phone
            textUserName.text = data.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ContactViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false))

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind()
    }

}
