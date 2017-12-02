package Source.GameEngine

import Source.GameController.Controller
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ListBuffer

trait GameEngine extends BeanMoveRules with DeathRules with DynamicMoveRules with BonusMoveRules {
  /**
    * função para se finalizar algo da engine ou suas patern classes, se necessário
    */
  def FinishGame(): Unit ={
  }

  private final val SIZE_INITIAL = 7

  //Dimensões do jogo
  var width: Int = Gdx.graphics.getWidth
  var height: Int = Gdx.graphics.getHeight

  //Cria uma pool de cores para as cobras
  var colorPool:ListBuffer[Color] = ListBuffer.empty
  colorPool += new Color(0, 0.26f, 0, 1)
  colorPool += new Color(0, 0.64f, 0, 1)
  colorPool += new Color(0, 0, 0.27f, 1)
  colorPool += new Color(0, 0, 0.53f, 1)
  colorPool += new Color(0.4f, 0.26f, 0, 1)
  colorPool += new Color(0.4f, 0.64f, 0, 1)
  colorPool += new Color(0.4f, 0, 0.27f, 1)
  colorPool += new Color(0.4f, 0, 0.53f, 1)

  //Cria pool de controles para jogadores
  var controllerPool:ListBuffer[List[Int]] = ListBuffer.empty
  controllerPool += List(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT)
  controllerPool += List(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D)
  controllerPool += List(Input.Keys.I, Input.Keys.K, Input.Keys.J, Input.Keys.L)
  controllerPool += List(Input.Keys.T, Input.Keys.G, Input.Keys.F, Input.Keys.H)

  //Numero de jogadores
  var playerCount:Int = Controller.PLAYER_NUMBER

  //Posiciona jogadores
  var players:ListBuffer[Sprite] = ListBuffer.empty
  for (i <- 0 until playerCount) {
    players += new Sprite(100, height/(playerCount + 1) * (i + 1), "PLAYER-" + (i + 1))
  }

  //Criaçao das cobras iniciais
  for (i <- 0 until playerCount) {
    for(j <- 1 until SIZE_INITIAL) {
      players(i).addPosition(100 - j * players(i).mySize, height/(playerCount + 1) * (i + 1))
    }
  }

  //Adiciona cor aos jogadores
  for (i <- 0 until playerCount) {
    players(i).myColor = colorPool(2 * i)
    players(i).myPositions.head.P_color = colorPool(2 * i + 1)
  }

  //Define as entradas de teclado de cada jogador
  for (i <- 0 until playerCount) {
    players(i).Keys = controllerPool(i)
  }

  //Seta sprites de jogadores
  players.foreach(x => x.setAsPlayer())
}
