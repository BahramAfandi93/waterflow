package com.calcpro.flowmaster

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlowMasterApplication

fun main(args: Array<String>) {
    runApplication<FlowMasterApplication>(*args)
}
