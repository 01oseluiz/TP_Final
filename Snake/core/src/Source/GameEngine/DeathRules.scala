package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait DeathRules {
  /**
    * Verifica se o player 1 colidiu com o player 2
    *
    * @param player1
    * @param player2
    * @return
    */
  def snakeCollisions(player1: Sprite, player2: Sprite): Unit

  /**
    * Verifica se o player colidiu com a parede
    *
    * @param player
    * @param listKillerThings
    * @return
    */
  def killerThingsCollisions(player: Sprite, listKillerThings: ListBuffer[Sprite]): Unit
}
