package Source.GameEngine

import com.badlogic.gdx.graphics.Color
import scala.collection.mutable.ListBuffer

class KillerThings(x:Int, y:Int){
  var myPositions:ListBuffer[Position] = ListBuffer(new Position(x,y))
  var myColor:Color = _
  var mySize:Int = 10

  /**
    * adiciona mais um segmento de objetos que matam
    * @param x
    * @param y
    */
  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y)
}
