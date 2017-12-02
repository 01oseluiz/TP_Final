package ClassicMod

import Source.GameController.Controller
import Source.GameEngine.{GameEngine, Sprite}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.{Gdx, Input}

import scala.collection.mutable.ListBuffer

class ClassicEngine extends GameEngine with
  ClassicBeansMoveRules with
  ClassicBonusMoveRules with
  ClassicDynamicMoveRules with
  ClassicDeathRules {

  private final val SIZE_INITIAL = 7




  //Variaveis dos players e beans
  var bean = new Sprite(width / 2, height / 2)
  var wall = new Sprite(0, 0)
  bean.myColor = new Color(1, 0, 0, 1)



  //Cria os objetos mortais (parede)
  wall.myColor = new Color(0.5f, 0.5f, 0.5f, 1)
  for (i <- 10 until width by wall.mySize) wall.addPosition(i, 0)
  for (i <- 10 until width by wall.mySize) wall.addPosition(i, height - 10)
  for (i <- 10 until height by wall.mySize) wall.addPosition(0, i)
  for (i <- 10 until height by wall.mySize) wall.addPosition(width - 10, i)


  //Seta os tipos dos objetos

  bean.setAsBean()
  wall.setAsKillerThing()

  /**
    * função para se finalizar algo da engine ou suas patern classes, se necessário
    */
  override def FinishGame(): Unit ={
  }
}
