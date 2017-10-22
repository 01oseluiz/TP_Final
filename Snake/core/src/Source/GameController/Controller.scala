package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  private final val GAME_ENGINE:Engine = new Engine
  private final val GAME_VIEW:GameScreen = new GameScreen

  def getGameEngine:Engine = GAME_ENGINE

  def MovementSnake(player:Player, key: Int): Unit = GAME_ENGINE.MovementSnake(player,key)

  /**
    * Verifica se o player pegou a comida
    * e gera uma nova em algum lugar vazio
    */
  def MovementBean(): Unit = {
    GAME_ENGINE.BeanPosition(GAME_ENGINE.player1, GAME_ENGINE.bean, (x,y)=>GAME_ENGINE.isEmptyPosition(x,y))
    GAME_ENGINE.BeanPosition(GAME_ENGINE.player2, GAME_ENGINE.bean, (x,y)=>GAME_ENGINE.isEmptyPosition(x,y))
  }

  /**
    * Verifica se houve algum tipo de colisao
    */
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

  /**
    * Calcula a proxima interação do jogo:
    * - entrada de teclado
    * - movimentação da cobra
    * - movimentação e captura de comida
    * - verificação de mortes
    */
  def nextInteraction(): Unit ={
    //Verifica a entrada de keys de cada player
    MovementSnake(GAME_ENGINE.player1, GAME_VIEW.getMovement(GAME_ENGINE.player1.Keys))
    MovementSnake(GAME_ENGINE.player2, GAME_VIEW.getMovement(GAME_ENGINE.player2.Keys))

    //Solicita a verificação de colisão bean X players
    MovementBean()

    //Solicita a verificação de colisao players X killerThings
    calc_Collisions()

    Thread.sleep(30)

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
