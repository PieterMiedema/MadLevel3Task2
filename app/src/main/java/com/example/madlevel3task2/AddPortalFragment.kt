package com.example.madlevel3task2

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addPortalBtn.setOnClickListener { addPortal() }
    }

    private fun addPortal() {
        if(validateInput()) {
            val portal = Portal(etTitle.text.toString(), etUrl.text.toString())
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portal)))
            findNavController().popBackStack()
        }
    }

    private fun validateInput(): Boolean {
        if (etTitle.text.isBlank() || etUrl.text.isBlank()) {
            Toast.makeText(requireContext(), "Please fill in the title and the url.", Toast.LENGTH_LONG).show()
            return false
        }

        if (!Patterns.WEB_URL.matcher(etUrl.text.toString()).matches()) {
            Toast.makeText(requireContext(), "The URL is invalid.", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}