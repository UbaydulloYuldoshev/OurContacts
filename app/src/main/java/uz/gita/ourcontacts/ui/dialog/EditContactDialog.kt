package uz.gita.ourcontacts.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import uz.gita.ourcontacts.R
import uz.gita.ourcontacts.data.model.response.ContactData


class EditContactDialog : DialogFragment(R.layout.dilalog_edit_contact) {
    private var listener: ((ContactData) -> Unit)? = null

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonAdd = view.findViewById<AppCompatButton>(R.id.buttonAdd)
        val buttonNo = view.findViewById<AppCompatButton>(R.id.buttonNo)
        val inputName = view.findViewById<AppCompatEditText>(R.id.inputName)
        val inputPhoneNumber = view.findViewById<AppCompatEditText>(R.id.inputPhoneNumber)

        var id = 0
        arguments?.let {
            val data = it.getSerializable("data") as ContactData
            inputName.setText(data.name)
            inputPhoneNumber.setText(data.phone)
            id = data.id
        }

        buttonAdd.setOnClickListener {
            listener?.invoke(ContactData(id,inputPhoneNumber.text.toString(),inputName.text.toString()))
            dismiss()
        }
        buttonNo.setOnClickListener {
            dismiss()
        }
    }

    fun setListener(f : (ContactData) -> Unit) {
        listener = f
    }
}