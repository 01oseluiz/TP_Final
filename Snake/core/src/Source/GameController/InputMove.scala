package Source.GameController

import Source.GameEngine.{SnakeMoveRules, Sprite}
import Source.GameView.GetInputs
import com.badlogic.gdx.Input

class InputMove(private val player: Sprite, snakeMoveRules: SnakeMoveRules) extends Thread{
  private var delay:Long = _
  private var timeNowkey:Long = _
  private var timeNowMove:Long = _
  private var STOPED:Boolean = false
  private var PAUSED:Boolean = false
  private val ENGINE = Controller.getEngine

  def close() : Unit = STOPED = true
  def pause() : Unit = PAUSED = !PAUSED

  override def run(): Unit = {

    val inputs = new GetInputs
    var key = Input.Keys.ANY_KEY
    var key_AUX = Input.Keys.ANY_KEY

    timeNowMove = System.currentTimeMillis()

    while(!STOPED) {
      Thread.sleep(1)
      if(!PAUSED) {
        delay = 110 - player.speed

        key_AUX = inputs.getMovement(player.Keys)
        if (key_AUX != Input.Keys.ANY_KEY) {
          key = key_AUX
        }

        if ((System.currentTimeMillis() - timeNowMove) >= delay) {
          //Movimenta a cobra
          ENGINE.movementSnake(player, key, snakeMoveRules)

          //Solicita a verificação de colisão bean X players
          ENGINE.movementBean(player, key)

          //Solicita a verificação de final de jogo
          ENGINE.isEndGame(player, key)

          //Solicita a verificação de colisão com objetos bonus
          ENGINE.movementBonusObjects(player, key)

          //Solicita a verificação de atualização de objetos dinamicos
          ENGINE.dynamicsUpdate(player, key)


          key = Input.Keys.ANY_KEY
          timeNowMove = System.currentTimeMillis()
        }
      }
    }
  }
}
