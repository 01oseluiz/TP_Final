package Teleport

import Source.GameEngine.Sprite
import ClassicMod.ClassicEngine
import com.badlogic.gdx.graphics.Color

class GameSource extends ClassicEngine with BeanSpeedBonus with DeathRules with RulesDynamics{
  //Dimensões do jogo
  //var width: Int = Gdx.graphics.getWidth
  //var height: Int = Gdx.graphics.getHeight

  //Variaveis dos players e beans
  //var player1 = new Sprite(100, 40, "PLAYER-1")
  //var player2 = new Sprite(100, height - 40, "PLAYER-2")
  //var bean = new Sprite(width / 2, height / 2)
  var speedBean = new Sprite((width + 100) / 2, height / 2)
  //var wall = new Sprite(0,0)
  var teleportHead_1 = new Sprite(10,10)
  var teleportTail_1 = new Sprite(width-20, height-20)
  var teleportHead_2 = new Sprite(10,height-20)
  var teleportTail_2 = new Sprite(width-20, 10)
  var parallelBarLeft = new Sprite((width-150)/2, height/2)
  var parallelBarRight = new Sprite((width+150)/2, height/2)

  speedBean.myColor = new Color(0,1,1,1)

  //Cria as Barras parallelas
  parallelBarLeft.myColor = new Color(0.8f, 0.8f, 0.8f, 1)
  parallelBarRight.myColor = new Color(0.8f, 0.8f, 0.8f, 1)

  //Cria as quatro pontas de teleporte
  teleportHead_1.myColor = new Color(1,0,1,1)
  teleportTail_1.myColor = new Color(1,0,1,1)
  teleportHead_2.myColor = new Color(1,1,0,1)
  teleportTail_2.myColor = new Color(1,1,0,1)

  //inferior esquerda
  for(i<- 2 to 12) teleportHead_1.addPosition(11, i*11)
  for(i<- 2 to 12) teleportHead_1.addPosition(i*11, 11)
  //superior esquerda
  for(i<- 3 to 13) teleportHead_2.addPosition(11, height - i*11)
  for(i<- 2 to 12) teleportHead_2.addPosition(i*11, height - 21)
  //inferior direita
  for(i<- 3 to 13) teleportTail_2.addPosition(width - i*11, 11)
  for(i<- 2 to 12) teleportTail_2.addPosition(width - 21, i*11)
  //superior direita
  for(i<- 3 to 13) teleportTail_1.addPosition(width - 21, height - i*11)
  for(i<- 3 to 13) teleportTail_1.addPosition(width - i*11, height - 21)


  /*//Cria os objetos mortais (parede)
  wall.myColor = new Color(0.5f,0.5f,0.5f,1)
  for(i<-10 until width by wall.mySize) wall.addPosition(i,0)
  for(i<-10 until width by wall.mySize) wall.addPosition(i,height-10)
  for(i<-10 until height by wall.mySize) wall.addPosition(0,i)
  for(i<-10 until height by wall.mySize) wall.addPosition(width-10,i)*/

  /*//Define as entradas de teclado de cada jogador
  player1.Keys = List(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.LEFT, Input.Keys.RIGHT)
  player2.Keys = List(Input.Keys.W, Input.Keys.S, Input.Keys.A, Input.Keys.D)*/

  //Seta os tipos dos objetos
  /*player1.setAsPlayer()
  player2.setAsPlayer()
  bean.setAsBean()
  wall.setAsKillerThing()*/
  speedBean.setAsBonus()
  teleportHead_1.setAsDynamic()
  teleportTail_1.setAsDynamic()
  teleportHead_2.setAsDynamic()
  teleportTail_2.setAsDynamic()
  parallelBarLeft.setAsKillerThing()
  parallelBarRight.setAsKillerThing()


  /**
    * função para se finalizar algo da engine ou suas patern classes, se necessário
    */
  override def FinishGame(): Unit ={
  }
}
