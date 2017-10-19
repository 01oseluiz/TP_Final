package Source.GameEngine

class  BeanPosition(player1: Player, player2: Player, bean: Bean, width: Int, height: Int) {

  var x = new scala.util.Random
  var y = new scala.util.Random

  if(((bean.posX-10 <= player1.posX && player1.posX<= bean.posX+10)
    && (bean.posY-10 <= player1.posY && player1.posY<= bean.posY+10))
    || ((bean.posX-10 <= player2.posX && player2.posX<= bean.posX+10)
    && (bean.posY-10 <= player2.posY && player2.posY<= bean.posY+10))){
    bean.posX = x.nextInt(width)
    bean.posY = y.nextInt(height)
  }
}
