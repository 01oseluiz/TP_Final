package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  private final val GAME_ENGINE:Engine = new Engine
  private final val GAME_VIEW:GameScreen = new GameScreen

  def getGameEngine:Engine = GAME_ENGINE

  def MovementSnake(player:Player, key: Int): Unit = GAME_ENGINE.MovementSnake(player,key)

  def MovementBean(): Unit = {
    GAME_ENGINE.BeanPosition(GAME_ENGINE.player1, GAME_ENGINE.bean, GAME_ENGINE)
    GAME_ENGINE.BeanPosition(GAME_ENGINE.player2, GAME_ENGINE.bean, GAME_ENGINE)
  }

  def calc_Collisions(): Unit ={
    //TODO-alterar o exit para um gameover
    if(GAME_ENGINE.snakeCollisions(GAME_ENGINE.player1, GAME_ENGINE.player2) ||
       GAME_ENGINE.wallCollisions(GAME_ENGINE.player1, GAME_ENGINE.wall)){
      println("!!GAME-OVER!!\nPlayer 2 Win!")
      sys.exit(0)
    }
    if(GAME_ENGINE.snakeCollisions(GAME_ENGINE.player2, GAME_ENGINE.player1) ||
       GAME_ENGINE.wallCollisions(GAME_ENGINE.player2, GAME_ENGINE.wall)){
      println("!!GAME-OVER!!\nPlayer 1 Win!")
      sys.exit(0)
    }
  }

  def nextInteraction(): Unit ={
  }
}