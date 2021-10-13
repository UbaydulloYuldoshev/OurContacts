package uz.gita.ourcontacts.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.gita.ourcontacts.R


class EventDialog : BottomSheetDialogFragment() {
    private var editListener : (() -> Unit)? =null
    private var deleteListener : (() -> Unit)? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.dialog_event,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<LinearLayout>(R.id.edit).setOnClickListener {
            editListener?.invoke()
            dismiss()
        }
        view.findViewById<LinearLayout>(R.id.delete).setOnClickListener {
            deleteListener?.invoke()
            dismiss()
        }
    }

    fun setEditListener(f : () -> Unit) {
        editListener = f
    }

    fun setDeleteListener(f : () -> Unit) {
        deleteListener = f
    }
}