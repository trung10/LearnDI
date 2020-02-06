package main

interface Outputter {
    fun output(string: String)
}

class OutputterImp: Outputter{
    override fun output(string: String) {
        println(string)
    }
}