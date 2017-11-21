package ClassicMod

import Source.GameEngine.{DynamicMoveRules, Position, Sprite}

import scala.collection.mutable.ListBuffer

trait ClassicDynamicMoveRules extends DynamicMoveRules{
  /**
    * Função que atualiza somente uma vez, se necessário
    */
  override def dynamicsUpdate(current_Player: Sprite, key: Int, screenObjects: ListBuffer[Sprite],
                     listDynamicObjects: ListBuffer[Sprite], isEmpty: (Position) => Boolean): Unit = {
  }

  /**
    * Função que roda sempre, idependente de fatores externos
    */
  override def dynamicsRender(): Unit = {
  }
}
