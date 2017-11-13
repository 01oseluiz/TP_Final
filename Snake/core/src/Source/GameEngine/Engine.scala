package Source.GameEngine

import Source.GameController.Controller

import scala.collection.mutable.ListBuffer

class Engine extends ObjectsVisible{
  var GAME_ENGINE:GameEngine = _

  var PLAYERS :ListBuffer[Sprite] = ListBuffer.empty
  var BEANS :ListBuffer[Sprite] = ListBuffer.empty
  var KILLER_THINGS :ListBuffer[Sprite] = ListBuffer.empty
  var DYNAMIC_THINGS :ListBuffer[Sprite] = ListBuffer.empty
  var BONUS_OBJECT :ListBuffer[Sprite] = ListBuffer.empty

  // Adiciona um sprite a lista de objetos
  def addPlayer(player:Sprite):Unit = PLAYERS += player
  def addBean(bean:Sprite):Unit = BEANS += bean
  def addKillerThing(killerThing:Sprite):Unit = KILLER_THINGS += killerThing
  def addDynamicThing(dynamic:Sprite):Unit = DYNAMIC_THINGS += dynamic
  def addBonus(bonus:Sprite):Unit = BONUS_OBJECT += bonus

  // Retira um sprite da lista de objetos
  def remPlayer(player:Sprite):Unit = PLAYERS -= player
  def remBean(bean:Sprite):Unit = BEANS -= bean
  def remKillerThing(killerThing:Sprite):Unit = KILLER_THINGS -= killerThing
  def remDynamicThing(dynamic:Sprite):Unit = DYNAMIC_THINGS -= dynamic
  def remBonus(bonus:Sprite):Unit = BONUS_OBJECT -= bonus

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
    BONUS_OBJECT.clear()
    KILLER_THINGS.clear()
    DYNAMIC_THINGS.clear()
  }
  
  /**
    * Movimenta a cobra segundo a entrada e regra escolhida para movimentação
    * @param current_Player
    * @param key
    * @param snakeMoveRules
    */
  def movementSnake(current_Player:Sprite, key: Int, snakeMoveRules: SnakeMoveRules): Unit = {
    snakeMoveRules.MovementSnake(current_Player,key)
  }

  def movementBean(current_Player: Sprite, key:Int): Unit = {
    GAME_ENGINE.BeanPosition(current_Player, BEANS, key, (x,y)=>this.isEmptyPosition(x,y))
  }

  def movementBonusObjects(current_Player: Sprite, key:Int): Unit = {
    GAME_ENGINE.movementBonusObjects(current_Player, key, BONUS_OBJECT, (x,y)=>this.isEmptyPosition(x,y))
  }

  def dynamicsUpdate(current_Player: Sprite, key:Int):Unit = {
    GAME_ENGINE.dynamicsUpdate(current_Player, key, objects, DYNAMIC_THINGS, (x,y)=>this.isEmptyPosition(x,y))
  }
  
  /**
    * Verifica se houve algum tipo de colisao e se é o fim de jogo
    * @param player player atual
    * @param key tecla pressionada
    */
  def isEndGame(player: Sprite, key:Int):Unit ={
    if(player == PLAYERS(0)){
      GAME_ENGINE.snakeCollisions(PLAYERS(0), PLAYERS(1))
      GAME_ENGINE.killerThingsCollisions(PLAYERS(0), KILLER_THINGS)
    }else if(player == PLAYERS(1)){
      GAME_ENGINE.snakeCollisions(PLAYERS(1), PLAYERS(0))
      GAME_ENGINE.killerThingsCollisions(PLAYERS(1), KILLER_THINGS)
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
