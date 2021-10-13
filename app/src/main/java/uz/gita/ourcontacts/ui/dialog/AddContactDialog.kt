package uz.gita.ourcontacts.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import uz.gita.ourcontacts.R
import uz.gita.ourcontacts.data.model.request.AddContactRequest


class AddContactDialog : DialogFragment(R.layout.dialog_add_contact) {
    private var listener : ((AddContactRequest) -> Unit)?= null

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

        buttonAdd.setOnClickListener {
            listener?.invoke(AddContactRequest(inputName.text.toString(),inputPhoneNumber.text.toString()))
            dismiss()
        }
        buttonNo.setOnClickListener {
            dismiss()
        }
    }

    fun setListener(f : (AddContactRequest) -> Unit) {
        listener = f
    }
}

