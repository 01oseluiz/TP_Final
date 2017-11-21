package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  final val GAME_VERSION = "1.0.3"

  private val searchMods = new searchMods
  private var currentMod = "Default"
  
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
  def getGameEngine():GameEngine = GAME_ENGINE
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
    searchForMods()
    startGetMove()
    GAME_MENU.StartGame
  }

  def pauseGame():Unit = {
    movePlayer1.pause()
    movePlayer2.pause()
  }

  def playAgain(): Unit ={
    startGetMove()
    GAME_OVER.PlayAgain
  }

  def gameOver(): Unit ={
    stopGetMove()
    GAME_VIEW.GameOver
  }

  def backToMenu(): Unit = GAME_OVER.BackToMenu

  private def startGetMove(): Unit ={
    try {
      GAME_ENGINE = ENGINE.getNewGameEngine(currentMod)
    }catch{
      case _:Throwable =>
        currentMod = "Default"
        GAME_ENGINE = ENGINE.getNewGameEngine(currentMod)
    }
    movePlayer1 = new InputMove(ENGINE.PLAYERS(0), new SnakeMoveRules{})
    movePlayer2 = new InputMove(ENGINE.PLAYERS(1), new SnakeMoveRules{})
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
    var name = ENGINE.PLAYERS(0).myName
    var player = 1
    var eatenBeans = ENGINE.PLAYERS(0).getEatenBeans
    var pixelRan = ENGINE.PLAYERS(0).getPixelRan
    var time = ENGINE.PLAYERS(0).getTime
    var winner = ENGINE.PLAYERS(0).isAlive

    //CHAMAR FUNÇÃO DA GAMEOVERHUD
    //Mudar para pegar o vencedor
    GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)

    name = ENGINE.PLAYERS(1).myName
    player = 2
    eatenBeans = ENGINE.PLAYERS(1).getEatenBeans
    pixelRan = ENGINE.PLAYERS(1).getPixelRan
    time = ENGINE.PLAYERS(1).getTime
    winner = ENGINE.PLAYERS(1).isAlive

    //CHAMAR FUNÇÃO DA GAMEOVERHUD
    //Mudar para pegar o vencedor real
    GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
  }

  def searchForMods(): Unit ={
    try {
      val listmods = searchMods.getModsInfo
      currentMod = listmods(0)._2
    }catch{
      case x:Exception =>
        currentMod = "Default"
        println(x.getMessage)

        //TODO-a view deve mostrar o erro recebido aqui
    }
  }
}