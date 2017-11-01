package Source.GameController

import java.util.Calendar

import Source.GameEngine.{SnakeMoveRules, Sprite}
import Source.GameView.GetInputs
import com.badlogic.gdx.Input

class InputMove(private val player: Sprite, snakeMoveRules: SnakeMoveRules) extends Thread{
  private var delay:Long = _
  private var timeNowkey:Long = _
  private var timeNowMove:Long = _
  private var STOPED:Boolean = false
  private val ENGINE = Controller.getEngine

  def close() : Unit = STOPED = true

  override def run(): Unit = {
    
    val inputs = new GetInputs
    var key = Input.Keys.ANY_KEY
    var key_AUX = Input.Keys.ANY_KEY

    timeNowkey = Calendar.getInstance().getTimeInMillis
    timeNowMove = Calendar.getInstance().getTimeInMillis

    while(!STOPED) {
      delay = 110 - player.speed

      key_AUX = inputs.getMovement(player.Keys)
      if (key_AUX != Input.Keys.ANY_KEY){
        key = key_AUX
      }

      if ((Calendar.getInstance().getTimeInMillis - timeNowMove) >= delay) {
        //Movimenta a cobra
        ENGINE.MovementSnake(player, key, snakeMoveRules)

        //Solicita a verificação de colisão bean X players
        ENGINE.MovementBean(player, key)

        //Solicita a verificação de final de jogo
        ENGINE.IsEndGame(player, key)

        key = Input.Keys.ANY_KEY
        timeNowMove = Calendar.getInstance().getTimeInMillis
      }
    }
  }
}
