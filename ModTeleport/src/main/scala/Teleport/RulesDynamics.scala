package Teleport

import Source.GameController.Controller
import Source.GameEngine.{Position, Sprite}

import scala.collection.mutable.ListBuffer

trait RulesDynamics extends Source.GameEngine.DynamicMoveRules{
  var time = 0.0
  val randon = new scala.util.Random
  var x_ram:Int = 0

  /**
    * Função que atualiza somente uma vez, se necessário
    */
  override def dynamicsUpdate(current_Player: Sprite, key: Int, screenObjects: ListBuffer[Sprite],
                     listDynamicObjects: ListBuffer[Sprite], isEmpty: (Position) => Boolean): Unit = {

    val gameEngine:GameSource = Controller.getGameEngine.asInstanceOf[GameSource]
    var teleported = false

    //Verifica se passou pela ponta do teleporte_1
    gameEngine.teleportHead_1.myPositions.takeWhile { x =>
      if (x.positionIsEqual(current_Player.myPositions.head)) {
        current_Player.myPositions.head.P_x = gameEngine.width - x.P_x - 11
        current_Player.myPositions.head.P_y = gameEngine.height - x.P_y - 11
        teleported = true
      }
      !x.positionIsEqual(current_Player.myPositions.head)
    }

    //Verifica se passou pela calda do teleporte_1, caso nao tenha passado pela ponta nesta interação
    if (!teleported)
      gameEngine.teleportTail_1.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = -(9 + x.P_x - gameEngine.width)
          current_Player.myPositions.head.P_y = -(9 + x.P_y - gameEngine.height)
          teleported = true
        }

        !x.positionIsEqual(current_Player.myPositions.head)
      }

    //Verifica se passou pela cabeça do teleporte_2, caso nao tenha passado por outra ponta nesta interação
    if (!teleported)
      gameEngine.teleportHead_2.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = -(11 + x.P_x - gameEngine.width)
          current_Player.myPositions.head.P_y = gameEngine.height - x.P_y - 9
          teleported = true
        }
        !x.positionIsEqual(current_Player.myPositions.head)
      }

    //Verifica se passou pela calda do teleporte_2, caso nao tenha passado por outra ponta nesta interação
    if (!teleported)
      gameEngine.teleportTail_2.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = gameEngine.width - x.P_x - 9
          current_Player.myPositions.head.P_y = -(11 + x.P_y - gameEngine.height)
        }

        !x.positionIsEqual(current_Player.myPositions.head)
      }
  }

  /**
    * Função que roda sempre, idependente de fatores externos
    */
  override def dynamicsRender(): Unit = {
    val gameEngine:GameSource = Controller.getGameEngine.asInstanceOf[GameSource]

    if(System.currentTimeMillis() - time > 150)
      if(x_ram == 0){
        time = System.currentTimeMillis()
        createParallelBar()
        if(gameEngine.parallelBarLeft.myPositions.size == 15) x_ram = 1
      }else if(x_ram == 1){
        time = System.currentTimeMillis()-50
        createSquare()
        if(gameEngine.parallelBarLeft.myPositions.size == 29) x_ram = 2
      }else{
        time = System.currentTimeMillis()+150
        removeBar()
        if(gameEngine.parallelBarLeft.myPositions.size == 1) x_ram = 0
      }
  }

  def createParallelBar():Unit = {
    val gameEngine:GameSource = Controller.getGameEngine.asInstanceOf[GameSource]
    var initial = gameEngine.parallelBarLeft.myPositions.head

    gameEngine.parallelBarLeft.addPosition(initial.P_x, initial.P_y +(gameEngine.parallelBarLeft.myPositions.size-1)*5)
    gameEngine.parallelBarLeft.addPosition(initial.P_x, initial.P_y -(gameEngine.parallelBarLeft.myPositions.size-1)*5)

    initial = gameEngine.parallelBarRight.myPositions.head

    gameEngine.parallelBarRight.addPosition(initial.P_x, initial.P_y +(gameEngine.parallelBarRight.myPositions.size-1)*5)
    gameEngine.parallelBarRight.addPosition(initial.P_x, initial.P_y -(gameEngine.parallelBarRight.myPositions.size-1)*5)
  }

  def createSquare():Unit = {
    val gameEngine:GameSource = Controller.getGameEngine.asInstanceOf[GameSource]

    if(gameEngine.parallelBarLeft.myPositions.size >= 15){
      var initial = gameEngine.parallelBarLeft.myPositions(13)
      gameEngine.parallelBarLeft.addPosition(initial.P_x + 10 + (gameEngine.parallelBarLeft.myPositions.size-15)*5, initial.P_y)

      initial = gameEngine.parallelBarLeft.myPositions(14)
      gameEngine.parallelBarLeft.addPosition(initial.P_x + 10 + (gameEngine.parallelBarLeft.myPositions.size-16)*5, initial.P_y)
    }else createParallelBar()

    if(gameEngine.parallelBarRight.myPositions.size >= 15){
      var initial = gameEngine.parallelBarRight.myPositions(13)
      gameEngine.parallelBarRight.addPosition(initial.P_x -10-(gameEngine.parallelBarRight.myPositions.size-15)*5, initial.P_y)

      initial = gameEngine.parallelBarRight.myPositions(14)
      gameEngine.parallelBarRight.addPosition(initial.P_x -10-(gameEngine.parallelBarRight.myPositions.size-16)*5, initial.P_y)
    }else createParallelBar()
  }

  def removeBar(): Unit ={
    val gameEngine:GameSource = Controller.getGameEngine.asInstanceOf[GameSource]

    gameEngine.parallelBarLeft.remPosition(gameEngine.parallelBarLeft.myPositions.last)
    gameEngine.parallelBarLeft.remPosition(gameEngine.parallelBarLeft.myPositions.last)

    gameEngine.parallelBarRight.remPosition(gameEngine.parallelBarRight.myPositions.last)
    gameEngine.parallelBarRight.remPosition(gameEngine.parallelBarRight.myPositions.last)
  }
}
