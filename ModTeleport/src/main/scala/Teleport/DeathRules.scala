package Teleport

import Source.GameEngine.Sprite

import scala.collection.mutable.ListBuffer

trait DeathRules extends Source.GameEngine.DeathRules{
  /**
    * Verifica se o player colidiu com a parede
    *
    * @param player
    * @param listKillerThings
    * @return
    */
  override def killerThingsCollisions(player: Sprite, listKillerThings: ListBuffer[Sprite]): Unit = {
    val wall_positions = listKillerThings.head.myPositions
    val bars_Positions_1 = listKillerThings(1).myPositions
    val bars_Positions_2 = listKillerThings(2).myPositions

    wall_positions.foreach { x =>
      if (x.positionIsEqual(player.myPositions.head)) {
        player.kill()
      }
    }

    bars_Positions_1.foreach { x =>
      if (x.positionIsEqual(player.myPositions.head)) {
        player.kill()
      }
    }

    bars_Positions_2.foreach { x =>
      if (x.positionIsEqual(player.myPositions.head)) {
        player.kill()
      }
    }
  }
}
