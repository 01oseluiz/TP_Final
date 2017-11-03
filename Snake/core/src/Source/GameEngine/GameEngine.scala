package Source.GameEngine

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

class GameEngine extends BeanMoveRules with DeathRules {
  //Dimensões do jogo
  var width: Int = Gdx.graphics.getWidth
  var height: Int = Gdx.graphics.getHeight

  //Variaveis dos players e beans
  private final val SIZE_INITIAL = 7
  var player1 = new Sprite(100, 40)
  var player2 = new Sprite(100, height - 40)
  var bean = new Sprite(width / 2, height / 2)

  //Setando nome dos players
  player1.myName = "PLAYER-1"
  player2.myName = "PLAYER-2"

  //Criaçao das cobras iniciais
  for (i <- 1 until SIZE_INITIAL) {
    player1.addPosition(100 - i * player1.mySize, 40)
    player2.addPosition(100 - i * player2.mySize, height - 40)
  }

  //Adiciona cores aos jogadores
  bean.myColor = new Color(1, 0, 0, 1)
  player1.myColor = new Color(0, 0.26f, 0, 1)
  player1.myPositions.head.P_color = new Color(0,0.64f,0,1)
  player2.myColor = new Color(0, 0, 0.27f, 1)
  player2.myPositions.head.P_color = new Color(0,0,0.53f,1)

  //Define as entradas de teclado de cada jogador
  player1.Keys = List(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT)
  player2.Keys = List(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D)

  //Variaveis de objetos mortais
  var wall = new Sprite(0,0)
  wall.myColor = new Color(0.5f,0.5f,0.5f,1)
  for(i<-10 until width by wall.mySize) wall.addPosition(i,0)
  for(i<-10 until width by wall.mySize) wall.addPosition(i,height-10)
  for(i<-10 until height by wall.mySize) wall.addPosition(0,i)
  for(i<-10 until height by wall.mySize) wall.addPosition(width-10,i)

  //Seta os tipos dos objetos
  player1.setAsPlayer()
  player2.setAsPlayer()
  bean.setAsBean()
  wall.setAsKillerThing()

  /**
    * função para se finalizar algo da engine ou suas superclasses, se necessário
    */
  def FinishGame(): Unit ={
    Thread.sleep(1500)
  }
}
