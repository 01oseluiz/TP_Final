package Source.GameController

import Source.GameEngine._
import Source.GameView._

import scala.collection.mutable.ListBuffer

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
  var PLAYER_NUMBER:Int =_

  def setGameView(x:GameScreen): Unit = GAME_VIEW = x
  def setGameOver(x:GameOverScreen): Unit = GAME_OVER = x
  def setGameMenu(x:GameMenuScreen): Unit = GAME_MENU = x
  def setGameDefault(x:ScreenDefault): Unit = GAME_DEFAULT = x
  def setPlayerNumber(x:Int): Unit = PLAYER_NUMBER = x
  def getGameEngine():GameEngine = GAME_ENGINE
  def getEngine:Engine = ENGINE

  //Realiza a verificação de entradas de teclado em paralelo
  private var movePlayer:ListBuffer[InputMove] = ListBuffer.empty

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
    movePlayer.foreach(x => x.pause())
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
    movePlayer = ListBuffer.empty
    for (i <- 0 until PLAYER_NUMBER) {
      movePlayer += new InputMove(ENGINE.PLAYERS(i), new SnakeMoveRules {})
    }
    movePlayer.foreach(x => x.start())
  }

  def stopGetMove(): Unit ={
    Thread.sleep(80) // Garante que as Threads teram terminado de fazer as verificações
                     // Possibilitando computar um possível empate
    movePlayer.foreach(x => x.close())
  }

  def getStatistics(): Unit ={
    for (i <- 0 until PLAYER_NUMBER) {
      val name = ENGINE.PLAYERS(i).myName
      val player = i + 1
      val eatenBeans = ENGINE.PLAYERS(i).getEatenBeans
      val pixelRan = ENGINE.PLAYERS(i).getPixelRan
      val time = ENGINE.PLAYERS(i).getTime
      val winner = ENGINE.PLAYERS(i).isAlive
      GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
    }
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