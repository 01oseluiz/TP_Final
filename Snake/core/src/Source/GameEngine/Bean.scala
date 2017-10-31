package Source.GameEngine

import com.badlogic.gdx.graphics.Color
import scala.collection.mutable.ListBuffer

/**
  * Classe para atribuir as posicoes a bean
  */
class Bean (x: Int,y: Int) {
  var myColor:Color = _
  var mySize:Int = 10
  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y,mySize))

  /**
    * Adiciona mais um segmento de bean ao jogo
    * @param x
    * @param y
    */
  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y,mySize)

  /**
    * Adiciona mais um segmento de bean ao jogo
    * @param x
    * @param y
    * @param size
    */
  def addPosition(x:Int, y:Int, size:Int):Unit = myPositions :+= new Position(x,y,size)

  /**
    * Remove um dado segmento de bean ao jogo
    * @param position
    */
  def remPosition(position: Position):Unit = myPositions -= position
}
