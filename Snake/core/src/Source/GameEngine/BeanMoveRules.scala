package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait BeanMoveRules {
  /**
    * Verifica se o player pegou a comida e gera uma nova em uma posição vazia
    *
    * @param player
    * @param listBeans
    * @param isEmpty (função que verifica se o espaço escolhido esta vazio)
    */
  def BeanPositionChange(player: Sprite, listBeans: ListBuffer[Sprite], key: Int, isEmpty: (Position) => Boolean): Unit
}