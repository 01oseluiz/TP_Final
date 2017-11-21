package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait DynamicMoveRules {
  /**
    * Função que atualiza somente uma vez, se necessário
    */
  def dynamicsUpdate(current_Player: Sprite, key: Int, screenObjects: ListBuffer[Sprite],
                     listDynamicObjects: ListBuffer[Sprite], isEmpty: (Position) => Boolean): Unit = {
  }

  /**
    * Função que roda sempre, idependente de fatores externos
    */
  def dynamicsRender(): Unit = {
  }
}
