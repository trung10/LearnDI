package main

import com.google.gson.Gson
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton

class Foo @Inject constructor() {
    init {
        print("Foo created\n")
    }

    fun sayHell0() {
        print("Hello")
    }
}

class Bar @Inject constructor(private val foo: Foo) {
    fun saySomething() {
        foo.sayHell0()
    }
}

class Jazz @Inject constructor(
    private val foo: Foo,
    private val bar: Bar,
    private val gson: Gson,
    private val play: Playable
) {
    fun run() {
        play.play()
    }
}

interface Playable {
    fun play()
}

class PlayableImp @Inject constructor() : Playable {
    override fun play() {
        print("playing")
    }
}

@Module
class MyModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}

@Module
interface BindingModule {
    @Binds
    fun play(imp: PlayableImp): Playable
}

@Component(
    modules = [MyModule::class,
        BindingModule::class]
)

interface MyConponent {
    fun barProvider(): Jazz
}

fun main() {
    val myConponent: MyConponent = DaggerMyConponent.create()
    val jaz = myConponent.barProvider()
    jaz.run()
    //val gson = Gson()
}