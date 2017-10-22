package Source.GameEngine

import com.badlogic.gdx.graphics.Color

class Position(x:Int, y:Int) {
  var P_x:Int = x
  var P_y :Int= y

  var P_color:Color = _

  /**
    * Verifica se uma das quatro quinas de uma posição esta contida em um objeto
    * @param position
    * @param size_this
    * @param size_p
    * @return
    */
  def positionIsEqual(position: Position, size_this:Int, size_p:Int) : Boolean = {
    val list_x_this:List[Int] = List.range(this.P_x,  this.P_x + size_this)
    val list_y_this:List[Int] = List.range(this.P_y, this.P_y + size_this)
    val list_x_p:List[Int] = List.range(position.P_x, position.P_x + size_p)
    val list_y_p:List[Int] = List.range(position.P_y, position.P_y + size_p)

    list_x_this.toSet.intersect(list_x_p.toSet).nonEmpty &&
    list_y_this.toSet.intersect(list_y_p.toSet).nonEmpty
  }

}