package Source.GameEngine

import com.badlogic.gdx.Gdx

import scala.collection.mutable.ListBuffer

trait BonusMoveRules {
  def movementBonusObjects(current_Player: Sprite, key:Int, listBonusObjects:ListBuffer[Sprite], isEmpty: (Position, Int) => Boolean): Unit ={
    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight
    val x = new scala.util.Random
    val y = new scala.util.Random
    val bean = listBonusObjects.head

    if (bean.myPositions.head.positionIsEqual(current_Player.myPositions.head)) {
      do {
        bean.myPositions.head.P_x = x.nextInt(width)
        bean.myPositions.head.P_y = y.nextInt(height)
      } while (!isEmpty(bean.myPositions.head, bean.mySize))

      if (current_Player.speed < 50){
        current_Player.speed += 2
      }
      else{
        for (i <- 1 until 5)
        current_Player.addPosition(current_Player.myPositions(1).P_x, current_Player.myPositions(1).P_y, current_Player.mySize)

        current_Player.speed = 30
      }

      current_Player.recordEaten
    }
  }
}
