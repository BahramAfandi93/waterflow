package com.calcpro.flowmaster

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlowMasterApplication

fun main(args: Array<String>) {
    runApplication<FlowMasterApplication>(*args)
//
//    val name = "ARMUD"
//    val escaped = """The kid asked, \"How's it going, $name?\""""
//
//    println(escaped)
//    println("----------------------------")
//
//    println(Character.getNumericValue('0'))
//    println(Character.getNumericValue('1'))
//    println(Character.getNumericValue('2'))
//    println(".....")
//    println(Character.getNumericValue('7'))
//    println(Character.getNumericValue('8'))
//    println(Character.getNumericValue('9'))
//    println(".....")
//    println(Character.getNumericValue('A'))
//    println(Character.getNumericValue('B'))
//    println(Character.getNumericValue('C'))
//    println(".....")
//    println(Character.getNumericValue('X'))
//    println(Character.getNumericValue('Y'))
//    println(Character.getNumericValue('Z'))
//
//    for (i in 0..35) {
//        val char = i.toChar()
//        println(char)
//    }
//
//    println(isIbanValid("AZ72PAHA41010AZNFK0200103230")) // not real
//    println(isIbanValid("AZ49UBAZ01583881851010AZN001")) // not real
    println(isIbanValid("AZ49UBAZ01583881841010AZN001")) // real
}

private fun isIbanValid(iban: String): Boolean {
    val ibanMinSize = 15
    val ibanMaxSize = 34
    val ibanMax: Long = 999999999
    val ibanModulus: Long = 97

    val trimmed = iban.trim { it <= ' ' }

    if (trimmed.length < ibanMinSize || trimmed.length > ibanMaxSize) {
        return false
    }

    val reformat = trimmed.substring(4) + trimmed.substring(0, 4)

    var total: Long = 0

    for (element in reformat) {
        val charValue = Character.getNumericValue(element)

        if (charValue < 0 || charValue > 35) {
            return false
        }

        total = (if (charValue > 9) total * 100 else total * 10) + charValue

        println("total = $total")

        if (total > ibanMax) {
            total = (total % ibanModulus)
            println("if $total % $ibanModulus = ${total % ibanModulus}")
        }
    }
    return (total % ibanModulus) == 1L
}