package Source.GameController

import Source.GameEngine._
import Source.GameView._

/*
TODO - em geral
Problema de processamento:
  *Com a Empty:
    -Menu: 3% - 6MB
    -Game: 60%: 70% - 380MB
    -End:  2% - 380MB
  *Sem a Empty:
    -Menu: 3% - 6MB
    -Game: 60%: 70% - 380MB
    -End:  2% - 380MB

Problema de memoria:
  *Programa sem Engines e inputs: 52MB
  *Com Engine: 55MB
  *Com Engine + GameEngine (direto): 66MB
  *Com Engine + GameEngine (pela Engine): 64MB
  *Com Engine + GameEngine (pela Engine) + inputs (sem start): 64MB
  *Com Engine + GameEngine (pela Engine) + inputs (com 1 start): 350MB - 30%CPU
  *Com Engine + GameEngine (pela Engine) + inputs (com 2 start): 355MB - 50%CPU
  *Com Engine + GameEngine (pela Engine) + 2 inputs (sem funções no while): 65MB - 50%CPU
  *
  * OBS:Apos analise foi identificado que a função Calendar.getInstance().getTimeInMillis  aloca algo com 300MB
  *
  * OBS2: com isKeyJustPressed: 355MB - 50%:60% CPU
  *       com isKeyPressed: 115Mb - 40% CPU
 */

object Controller {
  private val ENGINE:Engine = new Engine
  private var GAME_ENGINE:GameEngine = _
  var GAME_VIEW:GameScreen = _
  var GAME_OVER:GameOverScreen = _
  var GAME_MENU:GameMenuScreen=_
  var GAME_DEFAULT:ScreenDefault=_

  def setGameView(x:GameScreen): Unit = GAME_VIEW = x
  def setGameOver(x:GameOverScreen): Unit = GAME_OVER = x
  def setGameMenu(x:GameMenuScreen): Unit = GAME_MENU = x
  def setGameDefault(x:ScreenDefault): Unit = GAME_DEFAULT = x
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

    //Calcula a movimentação de objetos dinamicos
    GAME_ENGINE.dynamicsRender()

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
    Thread.sleep(80) // Garante que as Threads teram terminado de fazer as verificações
                     // Possibilitando computar um possível empate
    movePlayer1.close()
    movePlayer2.close()
  }

  def getStatistics(): Unit ={
    var name = GAME_ENGINE.player1.myName
    var player = 1
    var eatenBeans = GAME_ENGINE.player1.getEatenBeans
    var pixelRan = GAME_ENGINE.player1.getPixelRan
    var time = GAME_ENGINE.player1.getTime
    var winner = GAME_ENGINE.player1.isAlive

    //CHAMAR FUNÇÃO DA GAMEOVERHUD
    //Mudar para pegar o vencedor
    GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)

    name = GAME_ENGINE.player2.myName
    player = 2
    eatenBeans = GAME_ENGINE.player2.getEatenBeans
    pixelRan = GAME_ENGINE.player2.getPixelRan
    time = GAME_ENGINE.player2.getTime
    winner = GAME_ENGINE.player2.isAlive

    //CHAMAR FUNÇÃO DA GAMEOVERHUD
    //Mudar para pegar o vencedor real
    GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
  }
}