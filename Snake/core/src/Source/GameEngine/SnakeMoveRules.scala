package Source.GameEngine

import com.badlogic.gdx.Input

trait SnakeMoveRules {

  /**
    * realiza a movimentação da cobra (cabeca) conforme entrada de teclado
    * @param player
    * @param key
    */
  def MovementSnake(player: Player, key:Int): Unit = {
    val space: Int = player.mySize
    val position_AUX:Position = new Position(player.myPositions.head.P_x,player.myPositions.head.P_y, player.mySize)

    if(player.movementSense != Input.Keys.ANY_KEY || player.movementSense == Input.Keys.ANY_KEY && key != player.Keys(2)) {
      if (key == player.Keys(0) && player.movementSense != player.Keys(1)) {
        player.myPositions.head.P_y += space
      }
      else if (key == player.Keys(1) && player.movementSense != player.Keys(0)) {
        player.myPositions.head.P_y -= space
      }
      else if (key == player.Keys(2) && player.movementSense != player.Keys(3)) {
        player.myPositions.head.P_x -= space
      }
      else if (key == player.Keys(3) && player.movementSense != player.Keys(2)) {
        player.myPositions.head.P_x += space
      }
      else {
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
      player.movementSense = key
      MovementSnakeBody(player, position_AUX)
    }
  }

  /**
    * Realiza a movimentação do corpo da cobra conforme a cabeça
    * @param player
    * @param oldHeadPosition
    */
  def MovementSnakeBody(player: Player, oldHeadPosition:Position): Unit ={
    for(i<- player.myPositions.size-1 until 1 by -1){
      player.myPositions(i).P_y = player.myPositions(i-1).P_y
      player.myPositions(i).P_x = player.myPositions(i-1).P_x
    }
    player.myPositions(1).P_x = oldHeadPosition.P_x
    player.myPositions(1).P_y = oldHeadPosition.P_y
    player.recordRun //acrescenta movimento a estatistica de jogador
  }
}
