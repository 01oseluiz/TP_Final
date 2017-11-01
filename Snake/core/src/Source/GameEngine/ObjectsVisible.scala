package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait ObjectsVisible{
  var objects:ListBuffer[Sprite] = ListBuffer.empty

  def setAsVisible(obj:Sprite): Unit = objects :+= obj
  def setAsInvisible(obj:Sprite): Unit = objects -= obj

  def isEmptyPosition(position: Position, size:Int):Boolean = {
    var isEmptyPosition:Boolean = true
    objects.foreach{obj=>
      obj.myPositions.takeWhile(_=>isEmptyPosition).foreach{x=>
        if(x.positionIsEqual(position) && !x.equals(position)){
          isEmptyPosition = false
        }
      }
    }
    isEmptyPosition
  }
}
