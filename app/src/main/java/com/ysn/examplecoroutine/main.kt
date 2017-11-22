package com.ysn.examplecoroutine

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Created by yudisetiawan on 11/22/17.
 */
fun main(args: Array<String>) {
    println("Kotlin start")
    launch(CommonPool) {
        delay(2000)
        println("Kotlin inside")
    }
    println("Kotlin End")
}
