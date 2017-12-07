package Source.GameEngine

import Source.GameController.Controller
import ClassicMod.ClassicEngine
import scala.collection.mutable.ListBuffer

class Engine extends ObjectsVisible{
  var gameEngine:GameEngine = _

  var players :ListBuffer[Sprite] = ListBuffer.empty
  var beans :ListBuffer[Sprite] = ListBuffer.empty
  var killerThings :ListBuffer[Sprite] = ListBuffer.empty
  var dynamicThings :ListBuffer[Sprite] = ListBuffer.empty
  var bonusObject :ListBuffer[Sprite] = ListBuffer.empty
  var deadSnakes :ListBuffer[Sprite] = ListBuffer.empty

  // Adiciona um sprite a lista de objetos
  def addPlayer(player:Sprite):Unit = players += player
  def addBean(bean:Sprite):Unit = beans += bean
  def addKillerThing(killerThing:Sprite):Unit = killerThings += killerThing
  def addDynamicThing(dynamic:Sprite):Unit = dynamicThings += dynamic
  def addBonus(bonus:Sprite):Unit = bonusObject += bonus
  private def addDead(player:Sprite):Unit = deadSnakes += player

  // Retira um sprite da lista de objetos
  def remPlayer(player:Sprite):Unit = players -= player
  def remBean(bean:Sprite):Unit = beans -= bean
  def remKillerThing(killerThing:Sprite):Unit = killerThings -= killerThing
  def remDynamicThing(dynamic:Sprite):Unit = dynamicThings -= dynamic
  def remBonus(bonus:Sprite):Unit = bonusObject -= bonus

  /**
    * Retornar uma nova instancia da GameEngine para novo jogo
    * @return
    */
  def getNewGameEngine(packageGameEngine:String): GameEngine = {
    cleanEngine()
    if(packageGameEngine.equals("Default")) gameEngine = new ClassicEngine
    else gameEngine = Class.forName(packageGameEngine).newInstance.asInstanceOf[GameEngine]
    gameEngine
  }

  /**
    * Limpa todos os atributos da Engine para novo jogo
    */
  def cleanEngine(): Unit ={
    objects.clear()
    players.clear()
    beans.clear()
    bonusObject.clear()
    killerThings.clear()
    dynamicThings.clear()
    deadSnakes.clear()
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
    gameEngine.BeanPositionChange(current_Player, beans, key, (x)=>this.isEmptyPosition(x))
  }

  def movementBonusObjects(current_Player: Sprite, key:Int): Unit = {
    gameEngine.movementBonusObjects(current_Player, key, bonusObject, (x)=>this.isEmptyPosition(x))
  }

  def dynamicsUpdate(current_Player: Sprite, key:Int):Unit = {
    gameEngine.dynamicsUpdate(current_Player, key, objects, dynamicThings, (x)=>this.isEmptyPosition(x))
  }

  /**
    * Verifica se e se é o fim de jogo
    * @param player player atual
    * @param key tecla pressionada
    */
  def isEndGame(player: Sprite, key:Int):Unit ={
    if(gameEngine.isEndGame(player, key, gameEngine, players, killerThings)) {
      gameEngine.FinishGame()
      Controller.finishGame()
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
