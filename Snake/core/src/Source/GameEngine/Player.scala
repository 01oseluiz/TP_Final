package Source.GameEngine

import com.badlogic.gdx.graphics.Color
import scala.collection.mutable.ListBuffer

/**
  * Classe para atribuir as posicoes dos jogadores
  * @param x posicao X do jogador
  * @param y posicao Y do jogador
  */
class Player (x: Int, y: Int){

  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y))
  var myColor:Color = _
  var Keys:List[Int] = List.empty
  var mySize:Int = 10

  final val STOPPED = -1
  var movementSense:Int = STOPPED

  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y)
}
