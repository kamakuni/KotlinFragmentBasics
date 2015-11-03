package com.example.myapp.kotlinfragmentbasics

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AbsListView
import android.widget.ListView

/**
 * A fragment representing a list of Items.
 *
 *
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 *
 *
 * Activities containing this fragment MUST implement the [OnFragmentInteractionListener]
 * interface.
 */

class HeadlinesFragment : ListFragment() {
    lateinit var mCallback : OnHeadlineSelectedListener

    interface OnHeadlineSelectedListener {
        fun onArticleSelected(position: Int)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout : Int = if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
            android.R.layout.simple_list_item_activated_1
        else
            android.R.layout.simple_list_item_1

        setListAdapter(ArrayAdapter<String>(activity, layout, Ipsum.Headlines));
    }

    override fun onStart() {
        super.onStart()

        if(getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            val listView = getListView()
            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE)
        }

    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            mCallback = activity as OnHeadlineSelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity!!.toString() + " must implement OnHeadlineSelectedListener")
        }

    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        mCallback!!.onArticleSelected(position)
        getListView().setItemChecked(position, true)
    }

}