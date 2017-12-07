package Source.GameController

import Source.GameEngine.{SnakeMoveRules, Sprite}
import Source.GameView.GetInputs
import com.badlogic.gdx.Input

class InputMove(private val player: Sprite, snakeMoveRules: SnakeMoveRules) extends Thread{
  private var delay:Long = _
  private var timeNowMove:Long = _
  private var stopped:Boolean = false
  private var paused:Boolean = false
  private val engine = Controller.getEngine

  def close() : Unit = stopped = true
  def pause() : Unit = paused = !paused

  override def run(): Unit = {

    val inputs = new GetInputs
    var key = Input.Keys.ANY_KEY
    var key_AUX = Input.Keys.ANY_KEY

    timeNowMove = System.currentTimeMillis()

    while(!stopped) {
      Thread.sleep(1)
      if(!paused) {
        delay = 110 - player.speed

        key_AUX = inputs.getMovement(player.Keys)
        if (key_AUX != Input.Keys.ANY_KEY) {
          key = key_AUX
        }

        if ((System.currentTimeMillis() - timeNowMove) >= delay) {

          //Movimenta a cobra
          engine.movementSnake(player, key, snakeMoveRules)

          //Solicita a verificação de colisão bean X players
          engine.movementBean(player, key)

          //Solicita a verificação de colisão com objetos bonus
          engine.movementBonusObjects(player, key)

          //Solicita a verificação de atualização de objetos dinamicos
          engine.dynamicsUpdate(player, key)

          //Verifica se a cobra esta morta
          if(engine.isSnakeDead(player, key)){
            this.close()
          }

          //Solicita a verificação de final de jogo
          engine.isEndGame(player, key)

          key = Input.Keys.ANY_KEY
          timeNowMove = System.currentTimeMillis()

        }
      }
    }
  }
}
