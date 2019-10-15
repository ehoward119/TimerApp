package hu.ait.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tvData
import kotlinx.android.synthetic.main.mark_layout.*
import kotlinx.android.synthetic.main.mark_layout.view.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var time = 0
    lateinit var timer: Timer
    var active = false
    var started = false
    inner class EditTimer : TimerTask() {
        override fun run(){
            if(active){
                runOnUiThread {
                    time++
                    var minutes = (time/60).toString().padStart(2, '0')
                    var seconds = (time%60).toString().padStart(2, '0')
                    tvData.text ="$minutes:$seconds"
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = Timer()

        btnStart.setOnClickListener {
            if(!started){
                started = true
                active = true
                timer.scheduleAtFixedRate(EditTimer(),0, 1000)
            } else if(!active){
                active = true
            }
        }

        btnStop.setOnClickListener {
            active = false
        }

        btnMark.setOnClickListener {
            addMark()
        }
    }

    private fun addMark() {
        var markView = layoutInflater.inflate(
            R.layout.mark_layout, null, false
        )
        var minutes = (time/60).toString().padStart(2, '0')
        var seconds = (time%60).toString().padStart(2, '0')
        markView.tvData.text = "$minutes:$seconds"
        layoutContent.addView(markView, 0)

    }

}
