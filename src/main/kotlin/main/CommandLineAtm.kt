package main

import java.util.*


fun main() {

    val scanner = Scanner(System.`in`)
    val commandProcessor: CommandProcessor = CommandProcessorFactory.create().processor()

    while (scanner.hasNextLine()){
        commandProcessor.process(scanner.nextLine())
    }
}