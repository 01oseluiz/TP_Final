package Source.GameEngine

import Source.GameController.Controller
import ClassicMod.ClassicEngine
import scala.collection.mutable.ListBuffer

class Engine extends ObjectsVisible{
  var GAME_ENGINE:GameEngine = _

  var PLAYERS :ListBuffer[Sprite] = ListBuffer.empty
  var BEANS :ListBuffer[Sprite] = ListBuffer.empty
  var KILLER_THINGS :ListBuffer[Sprite] = ListBuffer.empty
  var DYNAMIC_THINGS :ListBuffer[Sprite] = ListBuffer.empty
  var BONUS_OBJECT :ListBuffer[Sprite] = ListBuffer.empty
  var DEAD_SNAKES :ListBuffer[Sprite] = ListBuffer.empty

  // Adiciona um sprite a lista de objetos
  def addPlayer(player:Sprite):Unit = PLAYERS += player
  def addBean(bean:Sprite):Unit = BEANS += bean
  def addKillerThing(killerThing:Sprite):Unit = KILLER_THINGS += killerThing
  def addDynamicThing(dynamic:Sprite):Unit = DYNAMIC_THINGS += dynamic
  def addBonus(bonus:Sprite):Unit = BONUS_OBJECT += bonus
  private def addDead(player:Sprite):Unit = DEAD_SNAKES += player

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
  def getNewGameEngine(packageGameEngine:String): GameEngine = {
    cleanEngine()
    if(packageGameEngine.equals("Default")) GAME_ENGINE = new Teleport.GameSource
    else GAME_ENGINE = Class.forName(packageGameEngine).newInstance.asInstanceOf[GameEngine]
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
    DEAD_SNAKES.clear()
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
    GAME_ENGINE.BeanPosition(current_Player, BEANS, key, (x)=>this.isEmptyPosition(x))
  }

  def movementBonusObjects(current_Player: Sprite, key:Int): Unit = {
    GAME_ENGINE.movementBonusObjects(current_Player, key, BONUS_OBJECT, (x)=>this.isEmptyPosition(x))
  }

  def dynamicsUpdate(current_Player: Sprite, key:Int):Unit = {
    GAME_ENGINE.dynamicsUpdate(current_Player, key, objects, DYNAMIC_THINGS, (x)=>this.isEmptyPosition(x))
  }
  
  /**
    * Verifica se e se é o fim de jogo
    * @param player player atual
    * @param key tecla pressionada
    */
  def isEndGame(player: Sprite, key:Int):Unit ={
    if(GAME_ENGINE.isEndGame(player, key, GAME_ENGINE, PLAYERS, KILLER_THINGS)) {
      GAME_ENGINE.FinishGame()
      Controller.gameOver()
    }

  }

  def isSnakeDead (player: Sprite, key:Int): Boolean = {
    if (!player.isAlive) {

      //des-sincroniza os threads
      //Garante que a memoria compartilhada não seja modificada ao mesmo tempo
      Thread.sleep(10*player.ID)

      addDead(player)
      remPlayer(player)

      player.setAsInvisible()
      return true
    }
    false
  }
}
