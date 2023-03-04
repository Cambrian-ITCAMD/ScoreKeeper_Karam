package ca.skaram.scorekeeper

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ca.skaram.scorekeeper.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pointsTeamA: TextView
    private lateinit var pointsTeamB: TextView
    private var teamAPoints = 0
    private var teamBPoints = 0
    private var pointsChangeBy = 1
    private var maxPoints = 100
    private var minPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val incButton = findViewById<Button>(R.id.inc_button)
        val decButton = findViewById<Button>(R.id.dec_button)


        pointsTeamA = findViewById<TextView>(R.id.scoreBoard_A)
        pointsTeamB = findViewById<TextView>(R.id.scoreBoard_B)
        binding.radioButton1.setOnClickListener(this)
        binding.radioButton2.setOnClickListener(this)
        binding.radioButton3.setOnClickListener(this)
        binding.radioButton4.setOnClickListener(this)
        binding.radioButton5.setOnClickListener(this)
        binding.radioButton6.setOnClickListener(this)
        binding.switch1.setOnCheckedChangeListener(this)

        incButton.setOnClickListener(this)
        decButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(binding.switch1.isChecked){
            teamBPoints(v)
            pointsTeamB.text = teamBPoints.toString()
        }
        else{
            teamAPoints(v)
            pointsTeamA.text = teamAPoints.toString()
        }

        when(binding.rg1.checkedRadioButtonId){
            R.id.radioButton1 -> pointsChangeBy = 3
            R.id.radioButton2 -> pointsChangeBy = 5
            R.id.radioButton3 -> pointsChangeBy = 10
            R.id.radioButton4 -> pointsChangeBy = 3
            R.id.radioButton5 -> pointsChangeBy = 5
            R.id.radioButton6 -> pointsChangeBy = 10
        }
    }

    private  fun teamBPoints(v: View?) {
        when(v?.id){
            R.id.inc_button -> {
                teamBPoints += pointsChangeBy
                Log.i("onClick", "INCREASE button was pressed")
                if (teamBPoints >= maxPoints) {
                    Log.i("onClick", "Maximum limit Reached")
                }
                else if (teamBPoints <= minPoints) {
                    Log.i("onClick", "Points can't be negative")
                }
                else {
                    Log.e("onClick", "Something Went Wrong...!!!")
                }
            }
            R.id.dec_button -> {
                teamBPoints -= pointsChangeBy
                Log.i("onClick", "DECREASE Button was pressed" )
                if(teamBPoints >= maxPoints){
                    Log.i("onClick", "Maximum limit Reached")
                }
                else if (teamBPoints <= minPoints) {
                    Log.i("onClick", "Points can't be negative")
                }
                else {
                    Log.e("onClick", "Something Went Wrong...!!!")
                }
            }
        }
    }

    private fun teamAPoints(v: View?) {
        when(v?.id){
            R.id.inc_button -> {
                teamAPoints += pointsChangeBy
                Log.i("onClick", "INCREASE button was pressed")
                if (teamAPoints >= maxPoints) {
                    Log.i("onClick", "Maximum limit Reached")
                }
                else if (teamAPoints <= minPoints) {
                    Log.i("onClick", "Points can't be negative")
                }
                else {
                    Log.e("onClick", "Something Went Wrong...!!!")
                }
            }
            R.id.dec_button -> {
                teamAPoints -= pointsChangeBy
                Log.i("onClick", "DECREASE Button was pressed" )
                if(teamAPoints >= maxPoints){
                    Log.i("onClick", "Maximum limit Reached")
                }
                else if (teamAPoints <= minPoints) {
                    Log.i("onClick", "Points can't be negative")
                }
                else {
                    Log.e("onClick", "Something Went Wrong...!!!")
                }
            }
        }
    }

    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        TODO("Not yet implemented")
    }
}
