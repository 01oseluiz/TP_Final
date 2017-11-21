package ClassicMod

import Source.GameEngine.{GameEngine, Sprite}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.{Gdx, Input}

class ClassicEngine extends GameEngine with
  ClassicBeansMoveRules with
  ClassicBonusMoveRules with
  ClassicDynamicMoveRules with
  ClassicDeathRules {

  //Dimensões do jogo
  var width: Int = Gdx.graphics.getWidth
  var height: Int = Gdx.graphics.getHeight

  //Variaveis dos players e beans
  private final val SIZE_INITIAL = 7
  var player1 = new Sprite(100, 40, "PLAYER-1")
  var player2 = new Sprite(100, height - 40, "PLAYER-2")
  var bean = new Sprite(width / 2, height / 2)
  var wall = new Sprite(0, 0)

  //Criaçao das cobras iniciais
  for (i <- 1 until SIZE_INITIAL) {
    player1.addPosition(100 - i * player1.mySize, 40)
    player2.addPosition(100 - i * player2.mySize, height - 40)
  }

  //Adiciona cores aos jogadores e beans
  bean.myColor = new Color(1, 0, 0, 1)
  player1.myColor = new Color(0, 0.26f, 0, 1)
  player1.myPositions.head.P_color = new Color(0, 0.64f, 0, 1)
  player2.myColor = new Color(0, 0, 0.27f, 1)
  player2.myPositions.head.P_color = new Color(0, 0, 0.53f, 1)

  //Cria os objetos mortais (parede)
  wall.myColor = new Color(0.5f, 0.5f, 0.5f, 1)
  for (i <- 10 until width by wall.mySize) wall.addPosition(i, 0)
  for (i <- 10 until width by wall.mySize) wall.addPosition(i, height - 10)
  for (i <- 10 until height by wall.mySize) wall.addPosition(0, i)
  for (i <- 10 until height by wall.mySize) wall.addPosition(width - 10, i)

  //Define as entradas de teclado de cada jogador
  player1.Keys = List(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT)
  player2.Keys = List(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D)

  //Seta os tipos dos objetos
  player1.setAsPlayer()
  player2.setAsPlayer()
  bean.setAsBean()
  wall.setAsKillerThing()

  /**
    * função para se finalizar algo da engine ou suas patern classes, se necessário
    */
  override def FinishGame(): Unit ={
  }
}
