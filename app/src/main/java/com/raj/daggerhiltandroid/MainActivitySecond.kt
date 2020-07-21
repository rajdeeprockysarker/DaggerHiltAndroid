package com.raj.daggerhiltandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivitySecond : AppCompatActivity() {
    @Inject
    lateinit var newSomeClass: NewSomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newSomeClass.value()
        newSomeClass.doOtherThing()
    }
}


@Singleton
class NewSomeClass
@Inject
constructor(
    private var newSomeOtherClass: NewSomeOtherClassInterface
) {

    fun value() {
        Log.v("TAG", "in New Value")
    }

    fun doOtherThing() {
        newSomeOtherClass.otherValue()
    }
}


class NewSomeOtherClass
@Inject
constructor() : NewSomeOtherClassInterface {

    override fun otherValue() {
        Log.v("TAG", "in New otherValue")
    }
}


interface NewSomeOtherClassInterface {
    fun otherValue()
}


@InstallIn(ApplicationComponent::class)
@Module
class MyModule {

    @Provides
    @Singleton
    fun provideNewSomeOtherClassObject(): NewSomeOtherClassInterface {
        return NewSomeOtherClass()
    }

}