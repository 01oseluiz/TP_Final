package Source.GameController

import Source.GameEngine._
import Source.GameView._

import scala.collection.mutable.ListBuffer

object Controller {
  final val gameVersion = "1.0.3"

  private val searchMods = new searchMods
  private var currentMod = "Default"
  
  private val engine:Engine = new Engine
  private var gameEngine:GameEngine = _
  var gameView:GameScreen = _
  var gameOver:GameOverScreen = _
  var gameMenu:GameMenuScreen=_
  var gameDefault:ScreenDefault=_
  var playerNumber:Int =_

  def setGameView(x:GameScreen): Unit = gameView = x
  def setGameOver(x:GameOverScreen): Unit = gameOver = x
  def setGameMenu(x:GameMenuScreen): Unit = gameMenu = x
  def setGameDefault(x:ScreenDefault): Unit = gameDefault = x
  def setPlayerNumber(x:Int): Unit = playerNumber = x
  def getGameEngine:GameEngine = gameEngine
  def getEngine:Engine = engine

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
    gameEngine.dynamicsRender()

    //Desenha por completo todos os objetos (player1, player2, wall, bean)
    engine.objects.foreach{objs=>
      objs.myPositions.foreach{x=>
        gameView.drawSquare(x, objs.myColor)
      }
    }
  }

  def startGame(): Unit ={
    startGetMove()
    gameMenu.StartGame
  }

  def pauseGame():Unit = {
    movePlayer.foreach(x => x.pause())
  }

  def playAgain(): Unit ={
    startGetMove()
    gameOver.PlayAgain
  }

  def finishGame(): Unit ={
    stopGetMove()
    gameView.GameOver
  }

  def backToMenu(): Unit = gameOver.BackToMenu

  private def startGetMove(): Unit ={
    try {
      gameEngine = engine.getNewGameEngine(currentMod)
    }catch{
      case _:Throwable =>
        currentMod = "Default"
        gameEngine = engine.getNewGameEngine(currentMod)
    }
    movePlayer = ListBuffer.empty
    for (i <- 0 until playerNumber) {
      movePlayer += new InputMove(engine.players(i), new SnakeMoveRules {})
    }
    movePlayer.foreach(x => x.start())
  }

  def stopGetMove(): Unit ={
    Thread.sleep(80) // Garante que as Threads teram terminado de fazer as verificações
                     // Possibilitando computar um possível empate
    movePlayer.foreach(x => x.close())
  }

  def getStatistics(): Unit ={

    engine.deadSnakes.foreach(x => engine.addPlayer(x))

    for (i <- 0 until playerNumber) {
      val name = engine.players(i).myName
      val player = engine.players(i).ID
      val eatenBeans = engine.players(i).getEatenBeans
      val pixelRan = engine.players(i).getPixelRan
      val time = engine.players(i).getTime
      val winner = engine.players(i).isAlive
      gameDefault.gameOverHud.playerStatisticsShow(name, pixelRan, eatenBeans, player, winner, time)
    }
  }

  def searchForMods(): Array[(String,String,String,String,String,String)] ={
    try {
      val listmods = searchMods.getModsInfo
      listmods
    }catch{
      case x:Exception =>
        currentMod = "Default"
        throw x
    }
  }

  def setMod(mod_Engine:String) : Unit = {
    currentMod = mod_Engine
  }
}