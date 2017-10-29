package Source.GameEngine

import GameController.Statistics

import com.badlogic.gdx.Input
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

  var myName:String = ""
  var myColor:Color = _
  var myLifes:Int = 1
  var Keys:List[Int] = List.empty
  var mySize:Int = 10
  var speed:Long = 30 //speed 0-100
  var isAlive:Boolean = true

  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y,mySize))

  var movementSense:Int = Input.Keys.ANY_KEY

  /**
    * adiciona mais um segnmento a cobra
    * @param x
    * @param y
    */
  //TODO- criar uma função para remover posições
  def addPosition(x:Int, y:Int, size:Int):Unit = myPositions :+= new Position(x,y,size)
  def makeAlive(): Unit = isAlive = true
  def kill(): Unit = isAlive = false
}
