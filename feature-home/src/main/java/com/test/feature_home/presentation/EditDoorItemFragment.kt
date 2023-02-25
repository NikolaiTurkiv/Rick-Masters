package com.test.feature_home.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.test.feature_home.R
import com.test.feature_home.presentation.domain.DoorsUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditDoorItemFragment : DialogFragment(R.layout.edit_door_item_dialog) {
    companion object {

        const val TAG = "EditDoorItemFragment"

        private const val NAME: String = "NAME"
        private const val ROOM: String = "ROOM"
        private const val ID: String = "ID"
        private const val FAVORITES: String = "FAVORITES"
        private const val SNAPSHOT: String = "SNAPSHOT"
        private const val VIEW_TYPE: String = "VIEW_TYPE"

        fun newInstance(
            item: DoorsUI
        ) = EditDoorItemFragment().apply {
            arguments = Bundle().apply {
                putString(NAME, item.name)
                putString(ROOM, item.room)
                putInt(ID, item.id ?: 0)
                putBoolean(FAVORITES, item.favorites ?: false)
                putString(SNAPSHOT, item.snapshot ?: "")
                putInt(VIEW_TYPE, item.viewType)
            }
        }
    }

    private val viewModel by viewModels<HomeViewModel>()


    private lateinit var editName: EditText
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE);
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initClickListeners()
    }

    private fun initClickListeners() {
        buttonCancel.setOnClickListener {
            this.dismiss()
        }

        buttonSave.setOnClickListener {

            val newItemName = editName.text.toString()
            if (newItemName.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(com.test.core_ui.R.string.field_can_not_be_empty),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun initView(view: View) {
        editName = view.findViewById(R.id.new_name_edit_text)
        buttonCancel = view.findViewById(R.id.button_cancel)
        buttonSave = view.findViewById(R.id.button_save)
    }


}