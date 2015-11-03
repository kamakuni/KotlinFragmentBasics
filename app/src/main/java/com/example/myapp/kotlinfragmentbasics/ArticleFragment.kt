package com.example.myapp.kotlinfragmentbasics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ArticleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {

    companion object {
        val ARG_POSITION = "position"
        var mCurrentPosition = -1
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        if(savedInstanceState != null){
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION)
        }

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.article_view, container, false)
    }

    override fun onStart() {
        super.onStart()

        val args = arguments
        if(args != null){
            updateArticleView(args.getInt(ARG_POSITION))
        } else if (mCurrentPosition != -1) {
            updateArticleView(mCurrentPosition)
        }
    }

    fun updateArticleView(position : Int){
        val article = getActivity().findViewById(R.id.article) as TextView
        article.setText(Ipsum.Articles[position])
        mCurrentPosition = position
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(ARG_POSITION, mCurrentPosition)

    }

}