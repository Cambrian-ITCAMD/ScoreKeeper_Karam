
package ca.skaram.scorekeeper

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import ca.skaram.scorekeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    //create instance of the binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var pointsTeamA: TextView
    private lateinit var pointsTeamB: TextView
    private lateinit var teamAName: EditText
    private lateinit var teamBName: EditText
    private lateinit var sharedPrefers: SharedPreferences

    //private variables that represents the points of the teams
    private var teamAPoints = 0
    private var teamBPoints = 0
    private var pointsChangeBy = 1
    private var maxPoints = 100
    private var minPoints = 0

    private var radioCheckedId = -1 // variable to store ID of the checked radio button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create object of the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var radioCheckedId = binding.rg1.checkedRadioButtonId
        var switchChecked = binding.switch1.isChecked

        sharedPrefers = PreferenceManager.getDefaultSharedPreferences(this)
        teamAName = binding.teamA
        teamBName = binding.teamB

        // Initialize the points of each team from SharedPreferences
        teamAPoints = sharedPrefers.getInt("teamAPoints", teamAPoints)
        teamBPoints = sharedPrefers.getInt("teamBPoints", teamBPoints)
        teamAName.setText(sharedPrefers.getString("teamAName", ""))
        teamBName.setText(sharedPrefers.getString("teamBName", ""))
        radioCheckedId = sharedPrefers.getInt("radioCheckedId", binding.rg1.checkedRadioButtonId)

        // Restore saved data for switch button selection
        switchChecked = sharedPrefers.getBoolean("switchChecked", switchChecked)
        binding.switch1.isChecked = switchChecked

        //Assign variable to the increase button using the id of the button
        val incButton = findViewById<Button>(R.id.inc_button)

        //Assign variable to the decrease button using the id of the button
        val decButton = findViewById<Button>(R.id.dec_button)

        //Finds the implementation of the TextViews/scoreboard of the teams using id
        pointsTeamA = findViewById<TextView>(R.id.scoreBoard_A)
        pointsTeamB = findViewById<TextView>(R.id.scoreBoard_B)

        //Sets the click listener for radio buttons
        binding.radioButton1.setOnClickListener(this)
        binding.radioButton2.setOnClickListener(this)
        binding.radioButton3.setOnClickListener(this)
        binding.switch1.setOnCheckedChangeListener(this)
        binding.teamA.setOnClickListener(this)
        binding.teamB.setOnClickListener(this)

        //Sets the click listener for the increase button
        incButton.setOnClickListener(this)

        //Sets the click listener for the decrease button
        decButton.setOnClickListener(this)

        // Update the scoreboards with restored points
        pointsTeamA.text = teamAPoints.toString()
        pointsTeamB.text = teamBPoints.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options, menu)
        return true
    }

    override fun onPause() {
        super.onPause()

        val editor = sharedPrefers.edit()
        val isSavingEnabled = sharedPrefers.getBoolean("switch_preference_1", false)

        if(isSavingEnabled){

            // Save the points of each team
            editor.putInt("teamAPoints", teamAPoints)
            editor.putInt("teamBPoints", teamBPoints)

            // save the team name, radio pointer, and switch button state
            editor.putString("teamAName", teamAName.text.toString())
            editor.putString("teamBName", teamBName.text.toString())
            editor.putBoolean("switch1", binding.switch1.isChecked)

            // save the ID of the checked radio button
            if (binding.radioButton1.isChecked) {
                radioCheckedId = binding.radioButton1.id
            } else if (binding.radioButton2.isChecked) {
                radioCheckedId = binding.radioButton2.id
            } else if (binding.radioButton3.isChecked) {
                radioCheckedId = binding.radioButton3.id
            }
            if (radioCheckedId != -1) { // save only if at least one radio button is checked
                editor.putInt("radioCheckedId", radioCheckedId)
            }
        }
        else{
            editor.clear()
        }
        // Apply the changes
        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        // Retrieve the stored values from the SharedPreferences
        teamAPoints = sharedPrefers.getInt("teamAPoints", teamAPoints)
        teamBPoints = sharedPrefers.getInt("teamBPoints", teamBPoints)
        binding.switch1.isChecked = sharedPrefers.getBoolean("switch1", false)
        teamAName.setText(sharedPrefers.getString("teamAName", ""))
        teamBName.setText(sharedPrefers.getString("teamBName", ""))

        // Restore saved data for radio buttons
        radioCheckedId = sharedPrefers.getInt("radioCheckedId", -1)
        if (radioCheckedId != -1) { // restore only if at least one radio button was checked
            binding.radioButton1.isChecked = (binding.radioButton1.id == radioCheckedId)
            binding.radioButton2.isChecked = (binding.radioButton2.id == radioCheckedId)
            binding.radioButton3.isChecked = (binding.radioButton3.id == radioCheckedId)
        }

        // Update the UI with the retrieved values
        pointsTeamA.text = teamAPoints.toString()
        pointsTeamB.text = teamBPoints.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSettings -> {
                // Call the function named "savedata" when the "settings" item is clicked
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menuAbout -> {
                // Print the name and course when the "about" item is clicked
                val names = listOf("Rishabh Bhutani", "Aneri Patel", "Karam Singh")
                val studentIds = listOf("A00244270", "A00244887", "A00242034")
                val message = StringBuilder()
                for (i in names.indices) {
                    message.append("Name: ${names[i]} \nStudent ID: ${studentIds[i]} \nCourse: IOT1009\n\n")
                }
                Toast.makeText(this, "Created By:\n\n"+ message.toString().trim(), Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        //when switch is checked/set to true, TeamB is set to earn points
        if(binding.switch1.isChecked){
            //calls teamBPoints(v) function
            teamBPoints(v)

            //set the points of team B
            pointsTeamB.text = teamBPoints.toString()
        }

        //when switch is not checked/set to false TeamA is set to earn points
        if(!binding.switch1.isChecked){
            when (v?.id) {
                //when increase button is pressed, TeamA will have incrementation in points
                R.id.inc_button -> {
                    teamAPoints += pointsChangeBy

                    //displays increase button was pressed
                    Log.i("onClick", "INCREASE button was pressed")

                    //Handles Min max exception for the points of Team A when points increases
                    minMaxHandlerTeamA()
                }
                //when decrease button is pressed, TeamA will loose points
                R.id.dec_button -> {
                    teamAPoints -= pointsChangeBy
                    Log.i("onClick", "DECREASE Button was pressed")

                    //Handles Min max exception for the points of Team A when points decreases
                    minMaxHandlerTeamA()
                }
            }
            //Sets points for Team A
            pointsTeamA.text = teamAPoints.toString()
        }

        //Access radio button using id and set them to change points of the Teams
        when(binding.rg1.checkedRadioButtonId){
            R.id.radioButton0 -> pointsChangeBy
            R.id.radioButton1 -> pointsChangeBy = 3
            R.id.radioButton2 -> pointsChangeBy = 5
            R.id.radioButton3 -> pointsChangeBy = 10
        }
    }

    //This function helps to increase and decrease the points of Team B
    private  fun teamBPoints(v: View?) {
        when(v?.id){
            //when increase button is pressed for TeamB
            R.id.inc_button -> {
                teamBPoints += pointsChangeBy
                Log.i("onClick", "INCREASE button was pressed")

                //Handles Min max exception for the points of Team B when points increases
                minMaxHandlerTeamB()
            }
            //when decrease button is pressed for TeamB
            R.id.dec_button -> {
                teamBPoints -= pointsChangeBy
                Log.i("onClick", "DECREASE Button was pressed" )

                //Handles Min max exception for the points of Team B when points decreases
                minMaxHandlerTeamB()
            }
        }
    }

    //Function to handle the minimum and maximum points for TEAM A
    private fun minMaxHandlerTeamA(){
        //if teamA points are more than max points allowed
        if (teamAPoints >= maxPoints) {
            //sets max points to 100
            teamAPoints = maxPoints
            Log.i("onClick", "Maximum limit Reached")
        }
        //if teamA points are less than zero
        else if (teamAPoints <= minPoints) {
            //sets min points to zero
            teamAPoints = minPoints
            Log.i("onClick", "Points can't be negative")
        }
    }

    //Function to handle the minimum and maximum points for TEAM B
    private fun minMaxHandlerTeamB(){
        //if teamB points are more than maximum allowed
        if (teamBPoints >= maxPoints) {
            //sets max points to 100
            teamBPoints = maxPoints
            Log.i("onClick", "Maximum limit Reached")
            Toast.makeText(this@MainActivity, "Maximum limit reached", Toast.LENGTH_LONG).show()

        }
        //if teamB points are less than zero
        else if (teamBPoints <= minPoints) {
            //sets min points to zero
            teamBPoints = minPoints
            Log.i("onClick", "Points can't be negative")
        }
    }


    /**
     * Called when the checked state of a compound button has changed.
     *
     * @param buttonView The compound button view whose state has changed.
     * @param isChecked  The new checked state of buttonView.
     */
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
    }
}
