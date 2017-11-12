package Source.GameEngine

import Source.GameController.Controller

import scala.collection.mutable.ListBuffer

trait DynamicMoveRules {

  /**
    * Função que atualiza somente uma vez, se necessário
    */
  def dynamicsUpdate(current_Player: Sprite, key: Int, screenObjects: ListBuffer[Sprite],
                     listDynamicObjects: ListBuffer[Sprite], isEmpty: (Position, Int) => Boolean): Unit = {

    val G_Engine = Controller.getGameEngine
    var teleported = false

    //Verifica se passou pela ponta do teleporte_1
    G_Engine.teleportHead_1.myPositions.takeWhile { x =>
      if (x.positionIsEqual(current_Player.myPositions.head)) {
        current_Player.myPositions.head.P_x = G_Engine.width - x.P_x - 10
        current_Player.myPositions.head.P_y = G_Engine.height - x.P_y - 10
        teleported = true
      }
      !x.positionIsEqual(current_Player.myPositions.head)
    }

    //Verifica se passou pela calda do teleporte_1, caso nao tenha passado pela ponta nesta interação
    if (!teleported)
      G_Engine.teleportTail_1.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = -(10 + x.P_x - G_Engine.width)
          current_Player.myPositions.head.P_y = -(10 + x.P_y - G_Engine.height)
          teleported = true
        }

        !x.positionIsEqual(current_Player.myPositions.head)
      }

    //Verifica se passou pela cabeça do teleporte_2, caso nao tenha passado por outra ponta nesta interação
    if (!teleported)
      G_Engine.teleportHead_2.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = -(10 + x.P_x - G_Engine.width)
          current_Player.myPositions.head.P_y = G_Engine.height - x.P_y - 10
          teleported = true
        }
        !x.positionIsEqual(current_Player.myPositions.head)
      }

    //Verifica se passou pela calda do teleporte_2, caso nao tenha passado por outra ponta nesta interação
    if (!teleported)
      G_Engine.teleportTail_2.myPositions.takeWhile { x =>
        if (x.positionIsEqual(current_Player.myPositions.head)) {
          current_Player.myPositions.head.P_x = G_Engine.width - x.P_x - 10
          current_Player.myPositions.head.P_y = -(10 + x.P_y - G_Engine.height)
        }

        !x.positionIsEqual(current_Player.myPositions.head)
      }
  }

  /**
    * Função que roda sempre, idependente de fatores externos
    */
  def dynamicsRender(): Unit = {

  }
}
