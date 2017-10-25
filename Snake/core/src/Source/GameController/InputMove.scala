package Source.GameController

import java.util.Calendar

import Source.GameEngine.{Player, SnakeMoveRules}
import com.badlogic.gdx.Input

class InputMove(private val player: Player, snakeMoveRules: SnakeMoveRules) extends Thread{
  private val delay:Long = 110 - player.speed
  private var timeNowkey:Long = _
  private var timeNowMove:Long = _
  private var STOPED:Boolean = false

  def close() : Unit = STOPED = true

  override def run(): Unit = {
    var key = Input.Keys.ANY_KEY
    var key_AUX = Input.Keys.ANY_KEY

    timeNowkey = Calendar.getInstance().getTimeInMillis
    timeNowMove = Calendar.getInstance().getTimeInMillis

    while(!STOPED) {

      key_AUX = Controller.GAME_VIEW.getMovement(player.Keys)
      if (key_AUX != Input.Keys.ANY_KEY){
        key = key_AUX
      }

      if ((Calendar.getInstance().getTimeInMillis - timeNowMove) >= delay) {
        //Movimenta a cobra
        Controller.MovementSnake(player, key, snakeMoveRules)
        key = Input.Keys.ANY_KEY

        //Solicita a verificação de colisão bean X players
        Controller.MovementBean(player)

        //Solicita a verificação de colisao players X killerThings
        Controller.calc_Collisions()

        timeNowMove = Calendar.getInstance().getTimeInMillis
      }
    }
  }
}
