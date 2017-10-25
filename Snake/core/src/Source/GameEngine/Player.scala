package Source.GameEngine

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ListBuffer

/**
  * Classe para atribuir as posicoes das cobras
  * @param x posicao X do jogador
  * @param y posicao Y do jogador
  */
class Player (x: Int, y: Int){

  var myName:String = ""
  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y))
  var myColor:Color = _
  var Keys:List[Int] = List.empty
  var mySize:Int = 10
  var speed:Long = 30 //speed 0-100

  var movementSense:Int = Input.Keys.ANY_KEY

  /**
    * adiciona mais um segnmento a cobra
    * @param x
    * @param y
    */
  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y)
}
