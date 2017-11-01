package Source.GameEngine

import java.util.Calendar

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color

trait BeanMoveRules {
  var time=Calendar.getInstance().getTimeInMillis

  /**
    * Verifica se o player pegou a comida e gera uma nova em uma posição vazia
    * @param player
    * @param bean
    * @param isEmpty (função que verifica se o espaço escolhido esta vazio)
    */
  def BeanPosition(player: Sprite, bean: Sprite, key:Int, isEmpty: (Position, Int)=>Boolean): Unit = {
    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight
    val x = new scala.util.Random
    val y = new scala.util.Random

    bean.myPositions.foreach {b=>
      if (b.positionIsEqual(player.myPositions.head)) {

        do {
          b.P_x = x.nextInt(width)
          b.P_y = y.nextInt(height)
        } while (!isEmpty(b, bean.mySize))

        if(b != bean.myPositions.head) {
          player.addPosition(player.myPositions(1).P_x, player.myPositions(1).P_y)
          player.recordEaten
        }else{

          if(player.myPositions.head.P_x > player.myPositions(1).P_x)
            player.myPositions(1).P_x -=1
          else if(player.myPositions.head.P_x < player.myPositions(1).P_x)
            player.myPositions(1).P_x +=1
          else if(player.myPositions.head.P_y > player.myPositions(1).P_y)
            player.myPositions(1).P_y -=1
          else
            player.myPositions(1).P_y +=1

          player.myPositions.foreach{X=>
            if(X!=player.myPositions(0) && X!=player.myPositions(1)){
              //player.remPosition(X)
              X.P_x = player.myPositions(1).P_x
              X.P_y = player.myPositions(1).P_y
            }
          }
          player.setSizeObject(player.mySize+1)
        }
      }
    }
  }
}
