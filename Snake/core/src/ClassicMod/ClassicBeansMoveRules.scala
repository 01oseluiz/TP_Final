package ClassicMod

import Source.GameEngine.{BeanMoveRules, Position, Sprite}
import com.badlogic.gdx.Gdx

import scala.collection.mutable.ListBuffer

trait ClassicBeansMoveRules extends BeanMoveRules {
  /**
    * Verifica se o player pegou a comida e gera uma nova em uma posição vazia
    *
    * @param player
    * @param listBeans
    * @param isEmpty (função que verifica se o espaço escolhido esta vazio)
    */
  override def BeanPosition(player: Sprite, listBeans: ListBuffer[Sprite], key: Int, isEmpty: (Position) => Boolean): Unit = {
    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight
    val x = new scala.util.Random
    val y = new scala.util.Random
    val bean = listBeans.head

    if (bean.myPositions.head.positionIsEqual(player.myPositions.head)) {
      do {
        bean.myPositions.head.P_x = x.nextInt(width)
        bean.myPositions.head.P_y = y.nextInt(height)
      } while (!isEmpty(bean.myPositions.head))

      player.addPosition(player.myPositions(1).P_x, player.myPositions(1).P_y, player.mySize)
      player.recordEaten
    }
  }
}
