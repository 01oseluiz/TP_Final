package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait DeathRules {

  /**
    * Verifica se o player 1 colidiu com o player 2
    * @param player1
    * @param player2
    * @return
    */
  def snakeCollisions(player1: Sprite, player2: Sprite): Unit = {

    player2.myPositions.foreach{x =>
      if(x.positionIsEqual(player1.myPositions.head)){
        player1.kill()
      }
    }

    player1.myPositions.foreach{x=>
      if(x != player1.myPositions.head && x.positionIsEqual(player1.myPositions.head)) {
        player1.kill()
      }
    }
  }

  /**
    * Verifica se o player colidiu com a parede
    * @param player
    * @param listKillerThings
    * @return
    */
  def killerThingsCollisions(player: Sprite, listKillerThings:ListBuffer[Sprite]): Unit ={
    val wall = listKillerThings.head
    wall.myPositions.foreach{x=>
      if(x.positionIsEqual(player.myPositions.head)){
        player.kill
      }
    }
  }
}
