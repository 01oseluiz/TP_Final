package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait BonusMoveRules {
  def movementBonusObjects(current_Player: Sprite, key:Int, listBonusObjects:ListBuffer[Sprite], isEmpty: (Position) => Boolean): Unit
}
