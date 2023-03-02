package com.example.gamestates

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamestates.Model.*
import com.example.gamestates.ui.theme.GameStatesTheme
import java.sql.Date
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameStatesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Log.e("State" , ReturnGameState().toString())
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReturnGameState() : String{
    val list = getGameStateList().get(0).states
//
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    val current = LocalDateTime.now().format(formatter)
//    Log.e("currentDate",current.toString())
//
//
//    val date = getDateTimeFromEpocLongOfSeconds(list.default.startDateTime)
//    Log.e("ddndk",date.toString())

//
//    val d1 = "2023-03-02"
//    val d2 = "2023-02-28"
//
//    val firstDate: LocalDate = LocalDate.parse(d1, formatter)
//    val secondDate: LocalDate = LocalDate.parse(d2, formatter)
//
//    when {
//        firstDate > secondDate -> {
//            Log.e("dshbjs","$date + is after + $current")
//            return "PRE"
//        }
//        firstDate < secondDate -> {
//            Log.e("dshbjs","$date + is before + $current")
//            return "POST"
//        }
//        else -> {
//            print("Both dates are equal")
//            return "jn"
//        }
//    }


    val calPre = Calendar.getInstance()
    calPre.timeInMillis = 1677699000

    val calLive = Calendar.getInstance()
    calLive.timeInMillis = 1677706200

    val calPost = Calendar.getInstance()
    calPost.timeInMillis = 1677717000

    val calEnd = Calendar.getInstance()
    calEnd.timeInMillis = 1677713400

    val currentDayTimeInMilli = System.currentTimeMillis()
//
//    if (currentDayTimeInMilli.compareTo(calPre.timeInMillis)==1){
//        Log.e("dknd","1")
//    }
//    if (currentDayTimeInMilli.compareTo(calPre.timeInMillis)==-1){
//        Log.e("dknd","-1")
//    }


    Log.e("result",calPre.timeInMillis.compareTo(calLive.timeInMillis).toString())

    if (calPre.timeInMillis.compareTo(calLive.timeInMillis)==-1){
        Log.e("Pre","Pre-Game starts at 3:00 ")
    }else if (calPre.timeInMillis.compareTo(calLive.timeInMillis)==1){
        Log.e("Pre","Live")

        if (calLive.timeInMillis.compareTo(calEnd.timeInMillis)==-1){
            Log.e("Live","Live-Game is live , end at 5")
        }else if(calLive.timeInMillis.compareTo(calEnd.timeInMillis)==0){
            Log.e("Live","game ended")

            if (calPost.timeInMillis.compareTo(calEnd.timeInMillis)==-1){
                Log.e("Post","-1")
            }else if(calPost.timeInMillis.compareTo(calEnd.timeInMillis)==1){
                Log.e("Post","Post-game ended show highlights")
            }
        }
    }else{
        Log.e("ddjbd","same")
    }
    return ""

}

fun getGameStateList():List<GameState>{
    return listOf<GameState>(
        GameState(States(
            Default("null",1677601860),
            End("",1677602400),
            Live("",1677604680),
            Post("",1677603600),
            Pre("",1677602400)
        ))
    )
}

private fun getDateTimeFromEpocLongOfSeconds(epoc: Long): String? {
    try {
        val netDate = Date(epoc*1000)
        return netDate.toString()
    } catch (e: Exception) {
        return e.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameStatesTheme {
    }
}