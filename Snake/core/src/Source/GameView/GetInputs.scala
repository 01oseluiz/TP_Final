package Source.GameView

import com.badlogic.gdx.{Gdx, Input}

class GetInputs {
  /**
    * Verifica se dada uma lista de keys alguma foi precionada
    * @param Keys
    * @return pressedKey
    */
  def getMovement(Keys:List[Int]): Int = {
    var pressedKey:Int = Input.Keys.ANY_KEY
    var continue:Boolean = true

    Keys.takeWhile(_=> continue).foreach{x=>
      if(Gdx.input.isKeyPressed(x)){
        pressedKey=x
        continue = false
      }
    }
    pressedKey
  }
}
