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
  var PLAYER_NUMBER:Int = 4

  def setGameView(x:GameScreen): Unit = GAME_VIEW = x
  def setGameOver(x:GameOverScreen): Unit = GAME_OVER = x
  def setGameMenu(x:GameMenuScreen): Unit = GAME_MENU = x
  def setGameDefault(x:ScreenDefault): Unit = GAME_DEFAULT = x
  def setPlayerNumber(x:Int): Unit = PLAYER_NUMBER = x
  def getGameEngine():GameEngine = GAME_ENGINE
  def getEngine:Engine = ENGINE

  //Realiza a verificação de entradas de teclado em paralelo
  private var movePlayer:ListBuffer[InputMove] = ListBuffer.empty
  //  private var movePlayer1:InputMove = _
//  private var movePlayer2:InputMove = _

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
    //Chamar a setBackground
    GAME_VIEW.setBackground(1, 1, 0, 1)
  }

  def pauseGame():Unit = {
    movePlayer.foreach(x => x.pause())
//    movePlayer1.pause()
//    movePlayer2.pause()
  }

  def playAgain(): Unit ={
    startGetMove()
    GAME_OVER.PlayAgain
    //Chamar a setBackground de novo aqui
    GAME_VIEW.setBackground(0, 1, 1, 1)
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
    //    movePlayer1 = new InputMove(ENGINE.PLAYERS(0), new SnakeMoveRules{})
    //    movePlayer2 = new InputMove(ENGINE.PLAYERS(1), new SnakeMoveRules{})
    movePlayer.foreach(x => x.start())
    //    movePlayer1.start()
    //    movePlayer2.start()
  }

  def stopGetMove(): Unit ={
    Thread.sleep(80) // Garante que as Threads teram terminado de fazer as verificações
    // Possibilitando computar um possível empate
    movePlayer.foreach(x => x.close())
//    movePlayer1.close()
//    movePlayer2.close()
  }

  def getStatistics(): Unit ={
    var name = ENGINE.PLAYERS(0).myName
    var player = 1
    var eatenBeans = ENGINE.PLAYERS(0).getEatenBeans
    var pixelRan = ENGINE.PLAYERS(0).getPixelRan
    var time = ENGINE.PLAYERS(0).getTime
    var winner = ENGINE.PLAYERS(0).isAlive

    for (i <- 0 until PLAYER_NUMBER) {
      name = ENGINE.PLAYERS(i).myName
      player = i + 1
      eatenBeans = ENGINE.PLAYERS(i).getEatenBeans
      pixelRan = ENGINE.PLAYERS(i).getPixelRan
      time = ENGINE.PLAYERS(i).getTime
      winner = ENGINE.PLAYERS(i).isAlive
      GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
    }

//    //CHAMAR FUNÇÃO DA GAMEOVERHUD
//    //Mudar para pegar o vencedor
//    GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
//
//    name = ENGINE.PLAYERS(1).myName
//    player = 2
//    eatenBeans = ENGINE.PLAYERS(1).getEatenBeans
//    pixelRan = ENGINE.PLAYERS(1).getPixelRan
//    time = ENGINE.PLAYERS(1).getTime
//    winner = ENGINE.PLAYERS(1).isAlive
//
//    //CHAMAR FUNÇÃO DA GAMEOVERHUD
//    //Mudar para pegar o vencedor real
//    GAME_DEFAULT.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
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