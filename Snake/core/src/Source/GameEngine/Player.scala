package Source.GameEngine

import com.badlogic.gdx.graphics.Color

/**
  * Classe para atribuir as posicoes dos jogadores
  * @param x posicao X do jogador
  * @param y posicao Y do jogador
  */
class Player (x: Int, y: Int){
  var posX : Int= x
  var posY : Int = y
  var myColor:Color = _
  var Keys:List[Int] = List.empty
}
