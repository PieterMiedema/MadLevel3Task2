package com.example.madlevel3task2

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.setFragmentResultListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { onClickPortal(it) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPortals.layoutManager = GridLayoutManager(activity, 2)
        rvPortals.adapter = portalAdapter

        if (portals.isEmpty()) {
            portals.add(Portal("Nu.nl", "https://nu.nl"))
            portals.add(Portal("Nu.nl", "https://nu.nl"))
            portals.add(Portal("Nu.nl", "https://nu.nl"))
        }

        observeAddPortalResult()
    }

    private fun onClickPortal(portal: Portal) {
        val customTabsIntent = CustomTabsIntent.Builder().build()

        if (URLUtil.isValidUrl(portal.portalUrl)) {
            customTabsIntent.launchUrl(requireContext(), Uri.parse(portal.portalUrl))
        }
        else {
            Toast.makeText(context, "Url is invalid.", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { _, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = Portal(it.portalTitle, it.portalUrl)
                portals.add(portal)
                portalAdapter.notifyDataSetChanged()
            }
        }
    }
}