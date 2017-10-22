package Source.GameEngine

import com.badlogic.gdx.graphics.Color
import scala.collection.mutable.ListBuffer

/**
  * Classe para atribuir as posicoes a bean
  */
class Bean (x: Int,y: Int) {
  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y))
  var myColor:Color = _
  var mySize:Int = 10

  /**
    * Adiciona mais um segmento de bean ao jogo
    * @param x
    * @param y
    */
  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y)
}
