package Source.GameEngine

class SnakeMoveRules {

  def MovementSnake(player: Player, key:Int): Unit = {
    val space: Int = 10
    val position_AUX:Position = new Position(player.myPositions.head.P_x,player.myPositions.head.P_y)

    if (key == player.Keys(0) && player.movementSense != player.Keys(1))
      player.myPositions.head.P_y += space
    else if (key == player.Keys(1) && player.movementSense != player.Keys(0))
      player.myPositions.head.P_y -= space
    else if (key == player.Keys(2) && player.movementSense != player.Keys(3))
      player.myPositions.head.P_x -= space
    else if (key == player.Keys(3) && player.movementSense != player.Keys(2))
      player.myPositions.head.P_x += space
    else{
      if (player.movementSense == player.Keys(0)) {
        player.myPositions.head.P_y += space
        MovementSnakeBody(player,position_AUX)
      } else if (player.movementSense == player.Keys(1)) {
        player.myPositions.head.P_y -= space
        MovementSnakeBody(player,position_AUX)
      } else if (player.movementSense == player.Keys(2)) {
        player.myPositions.head.P_x -= space
        MovementSnakeBody(player,position_AUX)
      } else if (player.movementSense == player.Keys(3)) {
        player.myPositions.head.P_x += space
        MovementSnakeBody(player,position_AUX)
      }

      return
    }
    MovementSnakeBody(player,position_AUX)

    player.movementSense = key
  }

  def MovementSnakeBody(player: Player, oldHeadPosition:Position): Unit ={
    for(i<- player.myPositions.size-1 until 1 by -1){
      player.myPositions(i) = player.myPositions(i-1)
    }
    player.myPositions(1) = oldHeadPosition
  }
}
