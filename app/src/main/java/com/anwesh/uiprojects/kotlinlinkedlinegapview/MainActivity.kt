package com.anwesh.uiprojects.kotlinlinkedlinegapview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.linkedlinegapview.LinkedLineGapView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LinkedLineGapView.create(this)
    }
}
