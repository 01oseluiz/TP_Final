package Source.GameEngine

import java.util.Calendar

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color

import scala.collection.mutable.ListBuffer

trait BeanMoveRules {
  /**
    * Verifica se o player pegou a comida e gera uma nova em uma posição vazia
    *
    * @param player
    * @param listBeans
    * @param isEmpty (função que verifica se o espaço escolhido esta vazio)
    */
  def BeanPosition(player: Sprite, listBeans: ListBuffer[Sprite], key: Int, isEmpty: (Position, Int) => Boolean): Unit = {
    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight
    val x = new scala.util.Random
    val y = new scala.util.Random
    val bean = listBeans.head

    if (bean.myPositions.head.positionIsEqual(player.myPositions.head)) {
      do {
        bean.myPositions.head.P_x = x.nextInt(width)
        bean.myPositions.head.P_y = y.nextInt(height)
      } while (!isEmpty(bean.myPositions.head, bean.mySize))

      player.addPosition(player.myPositions(1).P_x, player.myPositions(1).P_y, player.mySize)
      player.recordEaten
    }
  }
}