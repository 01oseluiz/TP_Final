package Source.GameEngine

import Source.GameController.Controller

import scala.collection.mutable.ListBuffer

class Engine extends ObjectsVisible{
  //TODO-criar funções setAsBean AsWall, AsSnake, AsKiller ....
  var GAME_ENGINE:GameEngine = _

  var PLAYERS :ListBuffer[Sprite] = ListBuffer.empty
  var BEANS :ListBuffer[Sprite] = ListBuffer.empty
  var KILLER_THINGS :ListBuffer[Sprite] = ListBuffer.empty
  
  def addPlayer(player:Sprite):Unit = PLAYERS += player
  def addBean(bean:Sprite):Unit = BEANS += bean
  def addKilerThing(killerthing:Sprite):Unit = KILLER_THINGS += killerthing

  /**
    * Retornar uma nova instancia da GameEngine para novo jogo
    * @return
    */
  def getNewGameEngine: GameEngine = {
    cleanEngine()
    GAME_ENGINE = new GameEngine
    GAME_ENGINE
  }

  /**
    * Limpa todos os atributos da Engine para novo jogo
    */
  //todo-fazer um clear? ou instanciar engine dnv na controller
  def cleanEngine(): Unit ={
    objects.clear()
    PLAYERS.clear()
    BEANS.clear()
    KILLER_THINGS.clear()
  }
  
  /**
    * Movimenta a cobra segundo a entrada e regra escolhida para movimentação
    * @param player
    * @param key
    * @param snakeMoveRules
    */
  def MovementSnake(player:Sprite, key: Int, snakeMoveRules: SnakeMoveRules): Unit = snakeMoveRules.MovementSnake(player,key)

  /**
    * Verifica se o player pegou a comida
    * e gera uma nova em algum lugar vazio
    */
  def MovementBean(player: Sprite, key:Int): Unit = {
    GAME_ENGINE.BeanPosition(player, GAME_ENGINE.bean, key, (x,y)=>this.isEmptyPosition(x,y))
  }
  
  /**
    * Verifica se houve algum tipo de colisao e se é o fim de jogo
    * @param player player atual
    * @param key tecla pressionada
    */
  def IsEndGame(player: Sprite, key:Int): Unit ={
    //TODO-fazer classe EndGameRules para verificar se é fim de jogo
    if(player == PLAYERS(0)){
      GAME_ENGINE.snakeCollisions(PLAYERS(0), PLAYERS(1))
      GAME_ENGINE.wallCollisions(PLAYERS(0), GAME_ENGINE.wall)
    }else if(player == PLAYERS(1)){
      GAME_ENGINE.snakeCollisions(PLAYERS(1), PLAYERS(0))
      GAME_ENGINE.wallCollisions(PLAYERS(1), GAME_ENGINE.wall)
    }

    if(!PLAYERS(0).isAlive && !PLAYERS(1).isAlive){
      Controller.gameOver()
      GAME_ENGINE.FinishGame()
    }else if(!PLAYERS(0).isAlive && player == PLAYERS(0)){
      Controller.gameOver()
      GAME_ENGINE.FinishGame()
    }else if(!PLAYERS(1).isAlive && player == PLAYERS(1)){
      Controller.gameOver()
      GAME_ENGINE.FinishGame()
    }

  }
}
