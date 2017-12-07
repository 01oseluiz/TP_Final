package ClassicMod

import Source.GameEngine.{GameEngine, GameOverRules, Sprite}

import scala.collection.mutable.ListBuffer

trait ClassicGameOverRules extends GameOverRules{

  override def isEndGame(player: Sprite, key: Int, GAME_ENGINE: GameEngine, PLAYERS: ListBuffer[Sprite],
                         KILLER_THINGS: ListBuffer[Sprite]): Boolean = {

    var liveCount = 0

//    Possibilita o modo single player
    if(GAME_ENGINE.playerCount == 1 ){
      val pseudoPlayer = new Sprite(-1,-1)
      pseudoPlayer.setAsInvisible()
      GAME_ENGINE.snakeCollisions(player, pseudoPlayer)
    }

    PLAYERS.foreach(x => if(player == x){PLAYERS.foreach(y => if(x != y){GAME_ENGINE.snakeCollisions(x, y)})})
    PLAYERS.foreach(x => if(player == x){GAME_ENGINE.killerThingsCollisions(x, KILLER_THINGS)})
    PLAYERS.foreach(x => liveCount += 1)

    if (liveCount <= 1 && liveCount < GAME_ENGINE.playerCount){return true}
    false
  }
}

