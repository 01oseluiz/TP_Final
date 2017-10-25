package Source.GameController

import java.util.Calendar
import Source.GameEngine.{SnakeMoveRules, SnakeMoveRulesHard}

object InputMove extends Thread{
  val delay:Long = 80
  var timeNowKey1:Long = _
  var timeNowKey2:Long = _
  var timeNowMove:Long = _
  var STOPED:Boolean = false

  def close() : Unit = STOPED = true

  override def run(): Unit = {
    var key1 = -1
    var key2 = -1
    var key_AUX:Int = -1

    timeNowKey1 = Calendar.getInstance().getTimeInMillis
    timeNowKey2 = Calendar.getInstance().getTimeInMillis
    timeNowMove = Calendar.getInstance().getTimeInMillis

    while(!STOPED) {

      key_AUX = Controller.GAME_VIEW.getMovement(Controller.GAME_ENGINE.player1.Keys)
      if (key_AUX != -1){
        key1 = key_AUX
        timeNowKey1 = Calendar.getInstance().getTimeInMillis
      }else if((Calendar.getInstance().getTimeInMillis - timeNowKey1) >= delay){
        key1 = key_AUX
        timeNowKey1 = Calendar.getInstance().getTimeInMillis
      }

      key_AUX = Controller.GAME_VIEW.getMovement(Controller.GAME_ENGINE.player2.Keys)
      if (key_AUX != -1){
        key2 = key_AUX
        timeNowKey2 = Calendar.getInstance().getTimeInMillis
      }else if((Calendar.getInstance().getTimeInMillis - timeNowKey2) >= delay){
        key2 = key_AUX
        timeNowKey2 = Calendar.getInstance().getTimeInMillis
      }

      if ((Calendar.getInstance().getTimeInMillis - timeNowMove) >= delay) {
        //Movimenta as cobras
        Controller.MovementSnake(Controller.GAME_ENGINE.player1, key1, new SnakeMoveRulesHard)
        Controller.MovementSnake(Controller.GAME_ENGINE.player2, key2, new SnakeMoveRules {})

        //Solicita a verificação de colisão bean X players
        Controller.MovementBean()

        //Solicita a verificação de colisao players X killerThings
        Controller.calc_Collisions()

        timeNowMove = Calendar.getInstance().getTimeInMillis
      }
    }
  }
}
