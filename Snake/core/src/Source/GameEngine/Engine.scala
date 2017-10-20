package Source.GameEngine

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

class Engine {
  var width:Int = Gdx.graphics.getWidth
  var height:Int = Gdx.graphics.getHeight

  //Variaveis dos players e beans
  var player1: Player = new Player(40, 40)
  var player2: Player = new Player(40, height - 40)
  var bean: Bean = new Bean(width, height)

  //Adiciona cores ao objetos
  bean.myColor = new Color(1,0,0,1)
  player1.myColor = new Color(1,1,1,1)
  player2.myColor = new Color(1,0,1,1)

  //Define as entradas de teclado de cada cobra
  player1.Keys = List(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT)
  player2.Keys = List(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D)

  def setSize(x:Int, y:Int): Unit ={
    width = x
    height = y
  }

  def BeanPosition(): Unit ={
    val x = new scala.util.Random
    val y = new scala.util.Random

    if(((bean.posX-10 <= player1.posX && player1.posX<= bean.posX+10)
      && (bean.posY-10 <= player1.posY && player1.posY<= bean.posY+10))
      || ((bean.posX-10 <= player2.posX && player2.posX<= bean.posX+10)
      && (bean.posY-10 <= player2.posY && player2.posY<= bean.posY+10))){
      bean.posX = x.nextInt(width)
      bean.posY = y.nextInt(height)
    }
  }

  def MovementSnake(player: Player, key:Int): Unit = {
    val speed: Int = 10

    if (key == player1.Keys.head || key == player2.Keys.head)  player.posY += speed
    else if (key == player1.Keys(1) || key == player2.Keys(1)) player.posY -= speed
    else if (key == player1.Keys(2) || key == player2.Keys(2))  player.posX -= speed
    else if (key == player1.Keys(3) || key == player2.Keys(3)) player.posX += speed
  }
}
