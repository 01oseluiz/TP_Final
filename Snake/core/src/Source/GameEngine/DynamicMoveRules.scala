package Source.GameEngine

import Source.GameController.Controller
import com.badlogic.gdx.Gdx

import scala.collection.mutable.ListBuffer

trait DynamicMoveRules {
  var time = 0.0
  val randon = new scala.util.Random
  var x_ram:Int = 0

  /**
    * Função que atualiza somente uma vez, se necessário
    */
  def dynamicsUpdate(current_Player: Sprite, key: Int, screenObjects: ListBuffer[Sprite],
                     listDynamicObjects: ListBuffer[Sprite], isEmpty: (Position) => Boolean): Unit = {

    val G_Engine = Controller.getGameEngine
    var teleported = false

    //Verifica se passou pela ponta do teleporte_1
    G_Engine.teleportHead_1.myPositions.takeWhile { x =>
      if (x.positionIsEqual(current_Player.myPositions.head)) {
        current_Player.myPositions.head.P_x = G_Engine.width - x.P_x - 11
        current_Player.myPositions.head.P_y = G_Engine.height - x.P_y - 11
        teleported = true
      }
      !x.positionIsEqual(current_Player.myPositions.head)
    }

    //Verifica se passou pela calda do teleporte_1, caso nao tenha passado pela ponta nesta interação
    if (!teleported)
      G_Engine.teleportTail_1.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = -(9 + x.P_x - G_Engine.width)
          current_Player.myPositions.head.P_y = -(9 + x.P_y - G_Engine.height)
          teleported = true
        }

        !x.positionIsEqual(current_Player.myPositions.head)
      }

    //Verifica se passou pela cabeça do teleporte_2, caso nao tenha passado por outra ponta nesta interação
    if (!teleported)
      G_Engine.teleportHead_2.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = -(11 + x.P_x - G_Engine.width)
          current_Player.myPositions.head.P_y = G_Engine.height - x.P_y - 9
          teleported = true
        }
        !x.positionIsEqual(current_Player.myPositions.head)
      }

    //Verifica se passou pela calda do teleporte_2, caso nao tenha passado por outra ponta nesta interação
    if (!teleported)
      G_Engine.teleportTail_2.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = G_Engine.width - x.P_x - 9
          current_Player.myPositions.head.P_y = -(11 + x.P_y - G_Engine.height)
        }

        !x.positionIsEqual(current_Player.myPositions.head)
      }
  }

  /**
    * Função que roda sempre, idependente de fatores externos
    */
  def dynamicsRender(): Unit = {
    val G_Engine = Controller.getGameEngine

    if(System.currentTimeMillis() - time > 150)
    if(x_ram == 0){
      time = System.currentTimeMillis()
      createParallelBar()
      if(G_Engine.parallelBarLeft.myPositions.size == 15) x_ram = 1
    }else if(x_ram == 1){
      time = System.currentTimeMillis()-50
      createSquare()
      if(G_Engine.parallelBarLeft.myPositions.size == 29) x_ram = 2
    }else{
      time = System.currentTimeMillis()+150
      removeBar()
      if(G_Engine.parallelBarLeft.myPositions.size == 1) x_ram = 0
    }
  }

  def createParallelBar():Unit = {
    val G_Engine = Controller.getGameEngine
    var initial = G_Engine.parallelBarLeft.myPositions.head

    G_Engine.parallelBarLeft.addPosition(initial.P_x, initial.P_y +(G_Engine.parallelBarLeft.myPositions.size-1)*5)
    G_Engine.parallelBarLeft.addPosition(initial.P_x, initial.P_y -(G_Engine.parallelBarLeft.myPositions.size-1)*5)

    initial = G_Engine.parallelBarRight.myPositions.head

    G_Engine.parallelBarRight.addPosition(initial.P_x, initial.P_y +(G_Engine.parallelBarRight.myPositions.size-1)*5)
    G_Engine.parallelBarRight.addPosition(initial.P_x, initial.P_y -(G_Engine.parallelBarRight.myPositions.size-1)*5)
  }

  def createSquare():Unit = {
    val G_Engine = Controller.getGameEngine

    if(G_Engine.parallelBarLeft.myPositions.size >= 15){
      var initial = G_Engine.parallelBarLeft.myPositions(13)
      G_Engine.parallelBarLeft.addPosition(initial.P_x + 10 + (G_Engine.parallelBarLeft.myPositions.size-15)*5, initial.P_y)

      initial = G_Engine.parallelBarLeft.myPositions(14)
      G_Engine.parallelBarLeft.addPosition(initial.P_x + 10 + (G_Engine.parallelBarLeft.myPositions.size-16)*5, initial.P_y)
    }else createParallelBar()

    if(G_Engine.parallelBarRight.myPositions.size >= 15){
      var initial = G_Engine.parallelBarRight.myPositions(13)
      G_Engine.parallelBarRight.addPosition(initial.P_x -10-(G_Engine.parallelBarRight.myPositions.size-15)*5, initial.P_y)

      initial = G_Engine.parallelBarRight.myPositions(14)
      G_Engine.parallelBarRight.addPosition(initial.P_x -10-(G_Engine.parallelBarRight.myPositions.size-16)*5, initial.P_y)
    }else createParallelBar()
  }

  def removeBar(): Unit ={
    val G_Engine = Controller.getGameEngine

    G_Engine.parallelBarLeft.remPosition(G_Engine.parallelBarLeft.myPositions.last)
    G_Engine.parallelBarLeft.remPosition(G_Engine.parallelBarLeft.myPositions.last)

    G_Engine.parallelBarRight.remPosition(G_Engine.parallelBarRight.myPositions.last)
    G_Engine.parallelBarRight.remPosition(G_Engine.parallelBarRight.myPositions.last)
  }
}
