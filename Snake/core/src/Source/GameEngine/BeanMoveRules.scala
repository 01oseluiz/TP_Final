package Source.GameEngine

import com.badlogic.gdx.Gdx

//TODO-fazer o span de bean fora de lugares preenchidos
//de forma que mantenha o encapsulamento para posterior injeção de dependencia
//(Sem acessar a classe engine)

trait BeanMoveRules {
  def BeanPosition(player: Player, bean: Bean, gE:Engine): Unit = {
    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight
    val x = new scala.util.Random
    val y = new scala.util.Random

    if (bean.myPositions.head.positionIsEqual(player.myPositions.head, bean.mySize, player.mySize)) {

      do {
        bean.myPositions.head.P_x = x.nextInt(width)
        bean.myPositions.head.P_y = y.nextInt(height)
      }while (gE.isEmptyPosition(bean.myPositions.head, bean.mySize))

      player.addPosition(player.myPositions(1).P_x,player.myPositions(1).P_y)
    }
  }
}