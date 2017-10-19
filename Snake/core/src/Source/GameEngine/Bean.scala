package Source.GameEngine

/**
  * Classe para atribuir as posicoes a bean
  * @param width largura da tela de jogo
  * @param height altura da tela de jogo
  */
class Bean (width: Int,height: Int) {
  var x = new scala.util.Random
  var y = new scala.util.Random
  var posX, posY : Int =_

    posX = x.nextInt(width)
    posY = y.nextInt(height)
}
