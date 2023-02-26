package com.test.feature_home.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
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

        private const val KEY_ID = "KEY_ID"
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
                putInt(KEY_ID, item.keyId ?: 0)
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
    private lateinit var buttonCancel: TextView
    private lateinit var buttonSave: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }
        dialog?.setCancelable(false)

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
            } else {

                progressBar.visibility = View.VISIBLE

                viewModel.updateDoor(
                    DoorsUI(
                        keyId = arguments?.getInt(KEY_ID),
                        name = editName.text.toString(),
                        room = arguments?.getString(ROOM),
                        id = arguments?.getInt(ID),
                        favorites = arguments?.getBoolean(FAVORITES),
                        snapshot = arguments?.getString(SNAPSHOT),
                        viewType = arguments?.getInt(VIEW_TYPE) ?: 0
                    )
                )


                object : CountDownTimer(2000, 1000) {
                    override fun onTick(p0: Long) {
                    }

                    override fun onFinish() {
                        progressBar.visibility = View.GONE
                        editName.clearFocus()
                        editName.text.clear()
                        this@EditDoorItemFragment.dismiss()
                    }

                }.start()

            }

        }
    }

    private fun initView(view: View) {
        progressBar = view.findViewById(R.id.progressBar)
        editName = view.findViewById(R.id.new_name_edit_text)
        buttonCancel = view.findViewById(R.id.button_cancel)
        buttonSave = view.findViewById(R.id.button_save)
    }
}
