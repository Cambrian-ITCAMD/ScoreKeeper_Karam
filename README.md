# Score Keeper

Creates a score keeping app using the Android Studio project. It is based on following aspects:
- An appropriate layout
- Two team names (EditText)
- Two scores (EditText)
- Buttons to increase and decrease the scores
  * Two Buttons with a way to determine which team is currently scoring (Switch or ToggleButton)
  * An amount to change the score by (RadioButtons or Spinner)

- There are two scoring options
  * Radio buttons
  * Increase and decrease buttons

- The scores can only presented or gained in following ways:
  * Score can not be negative/minimum score that a team can hold is 'zero'
  * Maximum score allowed is 100.
  * Switch between Teams using the 'Switch' button and increase or decrease scores based on selected option through switch.
  * If radio button is selected and increase or decrease button is pressed, scores will increase or decrease based on the radio button option is selected.
  * If no radio button option is selected, scores can still be increase or decreased using default value 1 by pressing increase or decrease button.

Initially appeared on
[gist](https://github.com/Cambrian-ITCAMD/ScoreKeeper_Karam.git).

## Getting Started

Read the above description in Score Keeper to get an idea how it works.

## Built With

-Kotlin compiler
-Android Studio

## License

This project is licensed under the [CC0 1.0 Universal](LICENSE.md)
Creative Commons License - see the [LICENSE.md](LICENSE.md) file for
details

## Acknowledgments

- Professor David
- Rishabh Bhutani 
- ChatGpt

## Contribution
  Karam Singh:
    * Created Initial layout
    * Worked on giving functionality to the switch button, radio buttons, EditText, and TextView
    * Tried best to add binding functionality and make proper use of find by Id methods.
    * Implemented logic for increasing and decreasing points.
    * Handled exceptions for the maximum and minimum score. 
    * Initial commit of code on github.
    * Added all update in the code to the github in proper way.

  Rishabh Bhutani:
    * Worked on giving functionality to the increase and decrease button.
    * Added comments/documentation in the code.
    * Made adjustments in the layout.
    * Helped in resolving some find by Id issues while giving functionality to all buttons.
    * Helped in making proper use of data fields, accessors, and mutators.
    * Maintained code and tested every aspect to remove all minor issues and bugs

## Submitted By:
* Karam Singh
* Rishabh Bhutani
