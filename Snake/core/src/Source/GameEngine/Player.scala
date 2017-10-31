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

class Player (x: Int, y: Int) extends Statistics{

  var myName:String = ""
  var myColor:Color = _
  var myLifes:Int = 1
  var Keys:List[Int] = List.empty
  var mySize:Int = 10
  var speed:Long = 30
  //speed 0-100
  var isAlive:Boolean = true

  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y,mySize))

  var movementSense:Int = Input.Keys.ANY_KEY

  //TODO-todos tem essas funções da pra generalizar?
  def setSizeObject(size:Int): Unit ={
    myPositions.foreach(x=> x.size = size)
    mySize = size
  }

  /**
    * adiciona mais um segnmento a cobra
    * @param x
    * @param y
    */
  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y,mySize)

  /**
    * adiciona mais um segmento a cobra
    * @param x
    * @param y
    * @param size
    */
  def addPosition(x:Int, y:Int, size:Int):Unit = myPositions :+= new Position(x,y,size)

  /**
    * remove uma dada posição da cobra
    * @param position
    */
  def remPosition(position: Position):Unit = myPositions -= position

  def makeAlive(): Unit = isAlive = true
  def kill(): Unit = isAlive = false
}
