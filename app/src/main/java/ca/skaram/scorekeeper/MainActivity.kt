package ca.skaram.scorekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var teamAPoints = 0
    private var teamBPoints = 0
    private var pointsChangeBy = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.dec_button).setOnClickListener(this)
        findViewById< Button>(R.id.inc_button).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        Log.i("OnClick", "A button was pressed")
    }

    private fun incPoints(){

    }

    private fun decPoints(){

    }
}