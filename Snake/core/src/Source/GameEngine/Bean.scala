package Source.GameEngine

import com.badlogic.gdx.graphics.Color

/**
  * Classe para atribuir as posicoes a bean
  */
class Bean (x: Int,y: Int) {
  var myPositions:List[Position] = List(new Position(x,y))
  var myColor:Color = _
  var mySize:Int = 10

  def addPosition(x:Int, y:Int):Unit = myPositions :+= new Position(x,y)
}
