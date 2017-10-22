package Source.GameEngine

trait DeathRules {

  /**
    * Verifica se o player 1 colidiu com o player 2
    * @param player1
    * @param player2
    * @return
    */
  def snakeCollisions(player1: Player, player2: Player): Boolean = {
    var isCrashed:Boolean = false

    player2.myPositions.foreach{x =>
      if(x.positionIsEqual(player1.myPositions.head, player2.mySize, player1.mySize)) isCrashed = true
    }
    isCrashed
  }

  def wallCollisions(player: Player, wall:KillerThings): Boolean ={
    var isCrashed:Boolean = false
    wall.myPositions.foreach{x=>
      if(x.positionIsEqual(player.myPositions.head, wall.mySize, player.mySize)) isCrashed = true
    }
    isCrashed
  }
}
