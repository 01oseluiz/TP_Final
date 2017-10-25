package Source.GameEngine

import GameController.Statistics
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ListBuffer

/**
  * Classe para atribuir as posicoes das cobras
  * @param x posicao X do jogador
  * @param y posicao Y do jogador
  */
//TODO - adicionar nos atributos dos players: Velocidade da cobra, nome, posição inicial e tamanho minimo*/
//TODO - Padronizar a key -1 como sendo nonInput
class Player (x: Int, y: Int) extends Statistics{

  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y))
  var myColor:Color = _
  var Keys:List[Int] = List.empty
  var mySize:Int = 10

  var movementSense:Int = -1

  /**
    * adiciona mais um segnmento a cobra
    * @param x
    * @param y
    */
  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y)
}
