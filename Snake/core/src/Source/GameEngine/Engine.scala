package Source.GameEngine

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

class Engine extends SnakeMoveRules {
  var width: Int = Gdx.graphics.getWidth
  var height: Int = Gdx.graphics.getHeight

  //Variaveis dos players e beans
  private final val SIZE_INITIAL = 7
  var player1: Player = new Player(100, 40)
  var player2: Player = new Player(100, height - 40)
  var bean: Bean = new Bean(width / 2, height / 2)

  //Cria as cobras iniciais
  for (i <- 1 to SIZE_INITIAL) {
    player1.addPosition(100 - i * player1.mySize, 40)
    player2.addPosition(100 - i * player2.mySize, height - 40)
  }

  //Adiciona cores ao objetos
  bean.myColor = new Color(1, 0, 0, 1)
  player1.myColor = new Color(1, 1, 1, 1)
  player2.myColor = new Color(1, 0, 1, 1)

  //Define as entradas de teclado de cada cobra
  player1.Keys = List(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT)
  player2.Keys = List(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D)

  def setSize(x: Int, y: Int): Unit = {
    width = x
    height = y
  }

  def BeanPosition(): Unit = {
    val x = new scala.util.Random
    val y = new scala.util.Random

    if ((bean.myPositions.head.P_x - 10 <= player1.myPositions.head.P_x && player1.myPositions.head.P_x <= bean.myPositions.head.P_x + 10)
      && (bean.myPositions.head.P_y - 10 <= player1.myPositions.head.P_y && player1.myPositions.head.P_y <= bean.myPositions.head.P_y + 10)) {
      bean.myPositions.head.P_x = x.nextInt(width)
      bean.myPositions.head.P_y = y.nextInt(height)
      player1.addPosition(player1.myPositions.head.P_x,player1.myPositions.head.P_y)
    }else if ((bean.myPositions.head.P_x - 10 <= player2.myPositions.head.P_x && player2.myPositions.head.P_x <= bean.myPositions.head.P_x + 10)
    && (bean.myPositions.head.P_y - 10 <= player2.myPositions.head.P_y && player2.myPositions.head.P_y <= bean.myPositions.head.P_y + 10)){
      bean.myPositions.head.P_x = x.nextInt(width)
      bean.myPositions.head.P_y = y.nextInt(height)
      player2.addPosition(player2.myPositions.head.P_x,player2.myPositions.head.P_y)
    }
  }
}
