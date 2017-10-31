package Source.GameEngine

//TODO-retirar mods feitos
/*
- Modo psicodelico
- Varias Beans
- Beans com açoes diferentes
 */

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Color

class Engine extends Objects with BeanMoveRules with DeathRules {
  //Dimensões do jogo
  var width: Int = Gdx.graphics.getWidth
  var height: Int = Gdx.graphics.getHeight

  //Variaveis dos players e beans
  private final val SIZE_INITIAL = 7
  var player1: Player = new Player(100, 40)
  var player2: Player = new Player(100, height - 40)
  var bean: Bean = new Bean(width / 2, height / 2)

  bean.myPositions.head.P_color = new Color(1,0,0,1)

  //Criaçao das cobras iniciais
  for (i <- 1 until SIZE_INITIAL) {
    player1.addPosition(100 - i * player1.mySize, 40)
    player2.addPosition(100 - i * player2.mySize, height - 40)
    //Criação de varios beans
    bean.addPosition(width/2 + i*12, height/2)
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
  var wall:KillerThings = new KillerThings(0,0)
  wall.myColor = new Color(0.5f,0.5f,0.5f,1)
  for(i<-10 until width by wall.mySize) wall.addPosition(i,0)
  for(i<-10 until width by wall.mySize) wall.addPosition(i,height-10)
  for(i<-10 until height by wall.mySize) wall.addPosition(0,i)
  for(i<-10 until height by wall.mySize) wall.addPosition(width-10,i)

  //-----------------------------PSICODELIC MODE-------------------------------
  var psicodelicThread = new Thread(){
    override def run(): Unit = {
      var cColor =0
      while(true) {
        cColor+=1

        if(cColor==1){
          wall.myColor = new Color(1,0,0,1)
          player1.myColor = new Color(0,0,1,1)
          player2.myColor = new Color(0,0,1,1)
        }
        else if(cColor==2){
          wall.myColor = new Color(0,1,0,1)
          player1.myColor = new Color(0,1,0,1)
          player2.myColor = new Color(0,1,0,1)
        }
        else if(cColor==3){
          wall.myColor = new Color(0,0,1,1)
          player1.myColor = new Color(1,0,0,1)
          player2.myColor = new Color(1,0,0,1)
          cColor=0
        }
        Thread.sleep(50)
      }
    }
  }
  psicodelicThread.start()
//----------------------------------------------------------------------------

  //Setando todos os objetos como visiveis na tela
  setAsObject(bean.myPositions)
  setAsObject(player1.myPositions)
  setAsObject(player2.myPositions)
  setAsObject(wall.myPositions)

  override def isEmptyPosition(position: Position, size:Int): Boolean ={
    var isEmptyPosition:Boolean = true

    player1.myPositions.takeWhile(_=>isEmptyPosition).foreach{x=>
      if(x.positionIsEqual(position)) isEmptyPosition = false
    }
    player2.myPositions.takeWhile(_=>isEmptyPosition).foreach{x=>
      if(x.positionIsEqual(position)) isEmptyPosition = false
    }
    wall.myPositions.takeWhile(_=>isEmptyPosition).foreach{x=>
      if(x.positionIsEqual(position)) isEmptyPosition = false
    }

    isEmptyPosition
  }

  def MovementSnake(player: Player, key:Int, snakeMoveRules: SnakeMoveRules): Unit ={
    snakeMoveRules.MovementSnake(player,key)
  }

  /**
    * função para se finalizar algo da engine ou suas superclasses, se necessário
    */
  def FinishGame(): Unit ={
    //TODO-se for manter o MOD psicodelico alterar o stop para outra coisa
    psicodelicThread.stop()
    Thread.sleep(1500)
  }
}
