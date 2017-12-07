package Source.GameEngine

import Source.GameController.Statistics
import Source.GameController.Controller
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ListBuffer

class Sprite (x: Int, y: Int, name:String = "", id:Int = 0) extends Statistics{
  private val ENGINE = Controller.getEngine
  var ID:Int = id
  var myName:String = name
  var myColor:Color = _
  var myLifes:Int = 1
  var Keys:List[Int] = List.empty
  var mySize:Int = 10
  var speed:Long = 30 //speed 0-100
  var isAlive:Boolean = true

  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y,mySize))

  var movementSense:Int = Input.Keys.ANY_KEY

  //Seta como objeto visivel por default
  this.setAsVisible()

  //Imposibilida sua visão na screen
  def setAsInvisible():Unit = Controller.getEngine.setAsInvisible(this)

  def setAsVisible():Unit = Controller.getEngine.setAsVisible(this)

  //seta o tipo do sprite
  def setAsPlayer():Unit = ENGINE.addPlayer(this)
  def setAsBean():Unit = ENGINE.addBean(this)
  def setAsKillerThing():Unit = ENGINE.addKillerThing(this)
  def setAsDynamic():Unit = ENGINE.addDynamicThing(this)
  def setAsBonus():Unit = ENGINE.addBonus(this)

  //remove as definições de tipo setadas
  def remAsPlayer():Unit = ENGINE.remPlayer(this)
  def remAsBean():Unit = ENGINE.remBean(this)
  def remAsKillerThing():Unit = ENGINE.remKillerThing(this)
  def remAsDynamic():Unit = ENGINE.remDynamicThing(this)
  def remAsBonus():Unit = ENGINE.remBonus(this)

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
