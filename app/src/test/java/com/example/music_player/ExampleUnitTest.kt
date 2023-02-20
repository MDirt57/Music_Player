package com.example.music_player

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.example.music_player.data.readTest
import com.example.music_player.data.sample
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

//class readFromRawTest{
//    @Test
//    fun list_isCorrect(){
//        var mainScreen = MainActivity()
//        val sampleArray = sample()
//        val testArray = readTest(mainScreen.context)
//        for (i in 0..testArray.size-1){
//            assertEquals(sampleArray.get(i).name, testArray.get(i).name)
//        }
//    }
//}