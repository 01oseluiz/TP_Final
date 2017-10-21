package Source.GameEngine

import com.badlogic.gdx.Gdx

trait BeanMoveRules {
  def BeanPosition(player: Player, bean: Bean): Unit = {
    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight
    val x = new scala.util.Random
    val y = new scala.util.Random

    if ((bean.myPositions.head.P_x - 10 <= player.myPositions.head.P_x && player.myPositions.head.P_x <= bean.myPositions.head.P_x + 10)
      && (bean.myPositions.head.P_y - 10 <= player.myPositions.head.P_y && player.myPositions.head.P_y <= bean.myPositions.head.P_y + 10)) {
      bean.myPositions.head.P_x = x.nextInt(width)
      bean.myPositions.head.P_y = y.nextInt(height)
      player.addPosition(player.myPositions.head.P_x,player.myPositions.head.P_y)
    }
  }
}
