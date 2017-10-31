package Source.GameController

import Source.GameEngine._
import Source.GameView._

object Controller {
  var GAME_ENGINE:Engine = _
  var GAME_VIEW:GameScreen = _
  var GAME_OVER:GameOverScreen = _
  var GAME_MENU:GameMenuScreen=_

  def setGameView(x:GameScreen): Unit = GAME_VIEW = x
  def setGameOver(x:GameOverScreen): Unit = GAME_OVER = x
  def setGameMenu(x:GameMenuScreen): Unit = GAME_MENU = x
  def getGameEngine:Engine = GAME_ENGINE

  //Realiza a verificação de entradas de teclado em paralelo
  private var movePlayer1:InputMove = _
  private var movePlayer2:InputMove = _

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
  def MovementBean(player: Player, key:Int): Unit = {
    GAME_ENGINE.BeanPosition(player, GAME_ENGINE.bean, key, (x,y)=>GAME_ENGINE.isEmptyPosition(x,y))
  }

  /**
    * Verifica se houve algum tipo de colisao e se é o fim de jogo
    */
  def IsEndGame(player: Player, key:Int): Unit ={
    //TODO-fazer classe EndGameRules para verificar se é fim de jogo
    if(player == GAME_ENGINE.player1){
      GAME_ENGINE.snakeCollisions(GAME_ENGINE.player1, GAME_ENGINE.player2)
      GAME_ENGINE.wallCollisions(GAME_ENGINE.player1, GAME_ENGINE.wall)
    }else if(player == GAME_ENGINE.player2){
      GAME_ENGINE.snakeCollisions(GAME_ENGINE.player2, GAME_ENGINE.player1)
      GAME_ENGINE.wallCollisions(GAME_ENGINE.player2, GAME_ENGINE.wall)
    }

    if(!GAME_ENGINE.player1.isAlive && !GAME_ENGINE.player2.isAlive){
      movePlayer1.close()
      movePlayer2.close()
      println("LOSERS")
      GAME_ENGINE.FinishGame()
      GAME_VIEW.GameOver
    }else if(!GAME_ENGINE.player1.isAlive && player == GAME_ENGINE.player1){
      movePlayer1.close()
      movePlayer2.close()
      println("PLAYER 1 LOSER")
      GAME_ENGINE.FinishGame()
      GAME_VIEW.GameOver
    }else if(!GAME_ENGINE.player2.isAlive && player == GAME_ENGINE.player2){
      movePlayer1.close()
      movePlayer2.close()
      println("PLAYER 2 LOSER")
      GAME_ENGINE.FinishGame()
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
  //TODO - criar função que seta um array de posições como objeto a ser mostrado na tela
  // desenhando todos os objetos setados no array de posições
  // definir onde este array deve ficar e onde esta função deve ser criada
  def nextInteraction(): Unit ={
    //Entrada de teclado sendo verificada em paralelo pela classe InputMove

    //Desenha por completo o player1, player2, wall, bean
    GAME_ENGINE.player1.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.player1.myColor)
    }
    GAME_ENGINE.player2.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.player2.myColor)
    }
    GAME_ENGINE.wall.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.wall.myColor)
    }
    GAME_ENGINE.bean.myPositions.foreach{x=>
      GAME_VIEW.drawSquare(x,GAME_ENGINE.bean.myColor)
    }
  }

  def playAgain(): Unit ={
    startGetMove()
    GAME_VIEW.PlayAgain
  }

  def startGame(): Unit ={
    startGetMove()
    GAME_MENU.StartGame
  }

  private def startGetMove(): Unit ={
    GAME_ENGINE = new Engine
    movePlayer1 = new InputMove(GAME_ENGINE.player1, new SnakeMoveRules{})
    movePlayer2 = new InputMove(GAME_ENGINE.player2, new SnakeMoveRules{})
    movePlayer1.start()
    movePlayer2.start()
  }
}
