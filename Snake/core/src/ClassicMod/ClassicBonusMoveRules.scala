package ClassicMod

import Source.GameEngine.{BonusMoveRules, Position, Sprite}

import scala.collection.mutable.ListBuffer

trait ClassicBonusMoveRules extends BonusMoveRules{
 override def movementBonusObjects(current_Player: Sprite, key:Int, listBonusObjects:ListBuffer[Sprite], isEmpty: (Position) => Boolean): Unit = {
  }
}
