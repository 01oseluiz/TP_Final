package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait Objects{
  var objects:ListBuffer[ListBuffer[Position]] = ListBuffer.empty

  //TODO - set infuncional, pois os tamanhos não são atualizados
  //verificar como adicionar a lista uma lista que se modificara conforme a original
  //semelhante a ponteiros
  def setAsObject(obj:ListBuffer[Position]): Unit = objects :+= obj
  def remObject(obj:ListBuffer[Position]): Unit = objects -= obj

  def setSizeObject(obj:ListBuffer[Position],size:Int): Unit ={
    obj.foreach(x=> x.size=size)
  }

  def isEmptyPosition(position: Position, size:Int):Boolean = {
    var isEmptyPosition:Boolean = true
    objects.foreach{obj=>
      println(obj.size)
      obj.takeWhile(_=>isEmptyPosition).foreach{x=>
        if(x.positionIsEqual(position) && !x.equals(position)){
          isEmptyPosition = false
        }
      }
    }
    isEmptyPosition
  }
}
