package Source.GameEngine

class SnakeMoveRulesHard extends SnakeMoveRules{
  /**
    * realiza a movimentação da cobra (cabeca) conforme entrada de teclado
    *
    * @param player
    * @param key
    */
  override def MovementSnake(player: Player, key: Int): Unit = {
    val space: Int = 10
    val position_AUX: Position = new Position(player.myPositions.head.P_x, player.myPositions.head.P_y)

    if (player.movementSense == -1 && key == player.Keys(3)) player.movementSense = player.Keys(1)

    if (key == player.Keys(0) && player.movementSense == player.Keys(0) ||
      (key == player.Keys(0) && player.movementSense == player.Keys(1))) {
      player.myPositions.head.P_x -= space
      player.movementSense = player.Keys(2)
    } else if (key == player.Keys(0) && player.movementSense == player.Keys(2) ||
      (key == player.Keys(0) && player.movementSense == player.Keys(3))) {
      player.myPositions.head.P_y += space
      player.movementSense = player.Keys(0)
    } else if (key == player.Keys(3) && player.movementSense == player.Keys(0) ||
      (key == player.Keys(3) && player.movementSense == player.Keys(1))) {
      player.myPositions.head.P_x += space
      player.movementSense = player.Keys(3)
    } else if (key == player.Keys(3) && player.movementSense == player.Keys(2) ||
      (key == player.Keys(3) && player.movementSense == player.Keys(3))) {
      player.myPositions.head.P_y -= space
      player.movementSense = player.Keys(1)
    } else {
      if (player.movementSense == player.Keys(0)) {
        player.myPositions.head.P_y += space
        MovementSnakeBody(player, position_AUX)
      } else if (player.movementSense == player.Keys(1)) {
        player.myPositions.head.P_y -= space
        MovementSnakeBody(player, position_AUX)
      } else if (player.movementSense == player.Keys(2)) {
        player.myPositions.head.P_x -= space
        MovementSnakeBody(player, position_AUX)
      } else if (player.movementSense == player.Keys(3)) {
        player.myPositions.head.P_x += space
        MovementSnakeBody(player, position_AUX)
      }
      return
    }
    MovementSnakeBody(player, position_AUX)
  }

  /**
    * Realiza a movimentação do corpo da cobra conforme a cabeça
    *
    * @param player
    * @param oldHeadPosition
    */
  override def MovementSnakeBody(player: Player, oldHeadPosition: Position): Unit = {
    for (i <- player.myPositions.size - 1 until 1 by -1) {
      player.myPositions(i) = player.myPositions(i - 1)
    }
    player.myPositions(1) = oldHeadPosition
  }
}
