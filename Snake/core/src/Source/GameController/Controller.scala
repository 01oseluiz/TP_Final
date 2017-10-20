package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  private final val GAME_ENGINE:Engine = new Engine
  private final val GAME_VIEW:GameScreen = new GameScreen

  def getGameEngine:Engine = GAME_ENGINE

  def MovementSnake(player:Player, key: Int): Unit = GAME_ENGINE.MovementSnake(player,key)

  def MovementBean(): Unit = GAME_ENGINE.BeanPosition()
}
