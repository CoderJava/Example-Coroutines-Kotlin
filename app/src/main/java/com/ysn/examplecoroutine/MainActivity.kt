package com.ysn.examplecoroutine

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_test_activity_main.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.run {
            when (id) {
                R.id.button_test_activity_main -> {
                    /** Example 1 */
                    /*Log.d(TAG, "Kotlin Start")
                    launch(CommonPool) {
                        delay(2000)
                        Log.d(TAG, "Kotlin Inside")
                    }
                    Log.d(TAG, "Kotlin End")*/

                    /** Example 2 */
                    /*Log.d(TAG, "Kotlin Start")
                    async(CommonPool) {
                        delay(2000)
                        Log.d(TAG, "Kotlin Inside")
                    }
                    Log.d(TAG, "Kotlin End")*/

                    /** Example 3 (before) */
                    /*doWorkInSeries()*/

                    /** Example 4 (after) */
                    doWorkInParallel()
                }
                else -> {
                    /** nothing to do in here */
                }
            }
        }
    }

    suspend fun doWorkFor1Seconds(): String {
        delay(1000)
        return "doWorkFor1Seconds"
    }

    suspend fun doWorkFor2Seconds(): String {
        delay(2000)
        return "doWorkFor2Seconds"
    }

    // Serial execution
    private fun doWorkInSeries() {
        launch(CommonPool) {
            val one = doWorkFor1Seconds()
            Log.d(TAG, "Kotlin One: " + one)
            val two = doWorkFor2Seconds()
            Log.d(TAG, "Kotlin Two: " + two)
        }
    }

    // Parallel execution
    private fun doWorkInParallel() {
        val one = async(CommonPool) {
            doWorkFor1Seconds()
        }
        val two = async(CommonPool) {
            doWorkFor2Seconds()
        }
        launch(CommonPool) {
            val combined = one.await() + "_" + two.await()
            Log.d(TAG, "Kotlin Combined: $combined")
        }
    }
}
