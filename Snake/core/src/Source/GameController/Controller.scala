package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  private var ENGINE:Engine = new Engine
  private var GAME_ENGINE:GameEngine = _
  var GAME_VIEW:GameScreen = _
  var GAME_OVER:GameOverScreen = _
  var GAME_MENU:GameMenuScreen=_

  def setGameView(x:GameScreen): Unit = GAME_VIEW = x
  def setGameOver(x:GameOverScreen): Unit = GAME_OVER = x
  def setGameMenu(x:GameMenuScreen): Unit = GAME_MENU = x
  def getGameEngine:GameEngine = GAME_ENGINE
  def getEngine:Engine = ENGINE

  //Realiza a verificação de entradas de teclado em paralelo
  private var movePlayer1:InputMove = _
  private var movePlayer2:InputMove = _

  /**
    * Calcula a proxima interação do jogo:
    * - entrada de teclado
    * - movimentação da cobra
    * - movimentação e captura de comida
    * - verificação de mortes
    */
  def nextInteraction(): Unit ={
    //Entrada de teclado sendo verificada em paralelo pela classe InputMove

    //Desenha por completo todos os objetos (player1, player2, wall, bean)
    ENGINE.objects.foreach{objs=>
      objs.myPositions.foreach{x=>
        GAME_VIEW.drawSquare(x, objs.myColor)
      }
    }
  }

  def startGame(): Unit ={
    startGetMove()
    GAME_MENU.StartGame
  }

  def playAgain(): Unit ={
    startGetMove()
    GAME_OVER.PlayAgain
  }

  def gameOver(): Unit ={
    stopGetMove()
    GAME_VIEW.GameOver
  }

  private def startGetMove(): Unit ={
    GAME_ENGINE = ENGINE.getNewGameEngine
    movePlayer1 = new InputMove(GAME_ENGINE.player1, new SnakeMoveRules{})
    movePlayer2 = new InputMove(GAME_ENGINE.player2, new SnakeMoveRules{})
    movePlayer1.start()
    movePlayer2.start()
  }

  def stopGetMove(): Unit ={
    movePlayer1.close()
    movePlayer2.close()
  }

  def getStatistics(): Unit ={
    var name = GAME_ENGINE.player1.myName
    var player = 1
    var eatenBeans = GAME_ENGINE.player1.getEatenBeans
    var pixelRan = GAME_ENGINE.player1.getPixelRan
    var time = GAME_ENGINE.player1.getTime

    //CHAMAR FUNÇÃO DA GAMEOVER

    name = GAME_ENGINE.player2.myName
    player = 2
    eatenBeans = GAME_ENGINE.player2.getEatenBeans
    pixelRan = GAME_ENGINE.player2.getPixelRan
    time = GAME_ENGINE.player2.getTime

    //CHAMAR FUNÇÃO DA GAMEOVER
  }
}