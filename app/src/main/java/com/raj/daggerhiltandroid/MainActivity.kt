package com.raj.daggerhiltandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mSomeClass:SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSomeClass.value()
        mSomeClass.doOtherThing(a=5)
    }
}

@AndroidEntryPoint
class TestFragmentClass:Fragment(){
    @Inject
    lateinit var mSomeClass:SomeClass

}

@Singleton
class SomeClass
@Inject
constructor(
    private var someOtherClass: SomeOtherClass
){

    fun value(){
        Log.v("TAG","in Value")
    }

    fun doOtherThing(a:Int){
        someOtherClass.otherValue(a)
    }
}


class SomeOtherClass
@Inject
constructor(){

    fun otherValue(a:Int){
        Log.v("TAG","in otherValue"+a)
    }
}