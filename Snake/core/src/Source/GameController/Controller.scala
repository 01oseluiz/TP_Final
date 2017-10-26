package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  val GAME_ENGINE:Engine = new Engine
  var GAME_VIEW:GameScreen = _
  var GAME_OVER:GameOverScreen = _

  def setGameView(x:GameScreen): Unit = GAME_VIEW = x
  def setGameOver(x:GameOverScreen): Unit = GAME_OVER = x
  def getGameEngine:Engine = GAME_ENGINE

  //Iniciando a verificação de entradas de teclado em paralelo
  val movePlayer1 = new InputMove(GAME_ENGINE.player1, new SnakeMoveRulesHard)
  val movePlayer2 = new InputMove(GAME_ENGINE.player2, new SnakeMoveRules{})

  movePlayer1.start()
  movePlayer2.start()

  /**
    * Movimenta a cobra segundo a entrada e regra escolhida para movimentação
    * @param player
    * @param key
    * @param ob
    */
  def MovementSnake(player:Player, key: Int, ob: SnakeMoveRules): Unit = GAME_ENGINE.MovementSnake(player,key,ob)

  /**
    * Verifica se o player pegou a comida
    * e gera uma nova em algum lugar vazio
    */
  def MovementBean(player: Player): Unit = {
    GAME_ENGINE.BeanPosition(player, GAME_ENGINE.bean, (x,y)=>GAME_ENGINE.isEmptyPosition(x,y))
  }

  /**
    * Verifica se houve algum tipo de colisao
    */
  def calc_Collisions(): Unit ={
    //TODO-alterar o exit para um gameover
    if(GAME_ENGINE.snakeCollisions(GAME_ENGINE.player1, GAME_ENGINE.player2) ||
       GAME_ENGINE.wallCollisions(GAME_ENGINE.player1, GAME_ENGINE.wall)){
      movePlayer1.close()
      movePlayer2.close()
      println("!!GAME-OVER!!\nPlayer 2 Win!")
      GAME_VIEW.GameOver
    }
    if(GAME_ENGINE.snakeCollisions(GAME_ENGINE.player2, GAME_ENGINE.player1) ||
       GAME_ENGINE.wallCollisions(GAME_ENGINE.player2, GAME_ENGINE.wall)){
      movePlayer1.close()
      movePlayer2.close()
      println("!!GAME-OVER!!\nPlayer 1 Win!")
//      sys.exit(0)
      GAME_VIEW.GameOver
    }
  }

  /**
    * Calcula a proxima interação do jogo:
    * - entrada de teclado
    * - movimentação da cobra
    * - movimentação e captura de comida
    * - verificação de mortes
    */
  def nextInteraction(): Unit ={
    //Entrada de teclado sendo verificada em paralelo pela classe InputMove

    //Desenha por completo o player1, player2, wall, bean
    GAME_ENGINE.player1.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.player1.myColor,GAME_ENGINE.player1.mySize,GAME_ENGINE.player1.mySize)
    }
    GAME_ENGINE.player2.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.player2.myColor,GAME_ENGINE.player2.mySize,GAME_ENGINE.player2.mySize)
    }
    GAME_ENGINE.wall.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.wall.myColor,GAME_ENGINE.wall.mySize,GAME_ENGINE.wall.mySize)
    }
    GAME_ENGINE.bean.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.bean.myColor,GAME_ENGINE.bean.mySize,GAME_ENGINE.bean.mySize)
    }
  }
}
