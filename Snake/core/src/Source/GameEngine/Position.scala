package Source.GameEngine

import com.badlogic.gdx.graphics.Color

class Position(x:Int, y:Int, sizeP:Int) {
  var P_x:Int = x
  var P_y :Int= y
  var size:Int = sizeP

  var P_color:Color = _

  /**
    * Verifica se uma das quatro quinas de uma posição esta contida em um objeto
    * @param position
    * @return
    */
  def positionIsEqual(position: Position) : Boolean = {
    val list_x_this:List[Int] = List.range(this.P_x,  this.P_x + this.size)
    val list_y_this:List[Int] = List.range(this.P_y, this.P_y + this.size)
    val list_x_p:List[Int] = List.range(position.P_x, position.P_x + position.size)
    val list_y_p:List[Int] = List.range(position.P_y, position.P_y + position.size)

    list_x_this.toSet.intersect(list_x_p.toSet).nonEmpty &&
    list_y_this.toSet.intersect(list_y_p.toSet).nonEmpty
  }

}