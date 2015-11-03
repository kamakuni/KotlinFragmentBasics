package com.example.myapp.kotlinfragmentbasics

import android.os.Bundle
import android.support.v4.app.FragmentActivity

class MainActivity : FragmentActivity(), HeadlinesFragment.OnHeadlineSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_articles)

        if(findViewById(R.id.fragmant_container) != null){

            if(savedInstanceState != null){
                return;
            }

            val firstFragment = HeadlinesFragment()
            firstFragment.setArguments(getIntent().getExtras())

            getSupportFragmentManager().beginTransaction()
            .add(R.id.fragmant_container, firstFragment).commit()

        }
    }

    override fun onArticleSelected(position : Int) {

        val articleFrag = getSupportFragmentManager().findFragmentById(R.id.article_fragment) as ArticleFragment?
        if(articleFrag != null){
            articleFrag.updateArticleView(position)
        } else {
            val newFragment = ArticleFragment()
            val args = Bundle()
            args.putInt(ArticleFragment.ARG_POSITION, position)
            newFragment.setArguments(args)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmant_container, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

    }

}
