package Source.GameView

import Source.GameEngine.Position
import Source.GameController._
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
  * Tela de jogo
  */
class GameScreen extends Screen {

  private val gameEngine = Controller.getGameEngine
  private var batch: SpriteBatch = _ // o lugar a ser desenhada a textura/imagem (papel)

  //Variaveis para a camera
  var width:Int = Gdx.graphics.getWidth
  var height:Int = Gdx.graphics.getHeight
  val camera = new OrthographicCamera(width, height)
  camera.position.set(width/2, height/2, 0)
  camera.update()
  val shapeRenderer = new ShapeRenderer
  shapeRenderer.setProjectionMatrix(camera.combined)

  /**
    * Desenha um quadrado, de tamanho, posições e cor quaisquer
    * @param p
    * @param c
    * @param size_x
    * @param size_y
    */
  private def drawSquare(p: Position, c:Color, size_x:Int, size_y:Int):Unit = {
    shapeRenderer.begin(ShapeType.Filled)
    if(p.P_color==null)shapeRenderer.setColor(c)
    else shapeRenderer.setColor(p.P_color)
    shapeRenderer.rect(p.P_x, p.P_y, size_x, size_y)
    shapeRenderer.end()
  }


  /**
    * Funciona como a create. É a primeira coisa executada ao ser chamada
    */
  def show(): Unit = {
    batch = new SpriteBatch //lugar a ser desenhado como um papel
  }

  /**
    * Verifica se dada uma lista de keys alguma foi precionada
    * @param Keys
    * @return pressedKey
    */
  //TODO-verificar a entrada de teclado em paralelo (Esta tendo delay)
  def getMovement(Keys:List[Int]): Int = {
    var pressedKey:Int = -1
    var continue:Boolean = true

    Keys.takeWhile(_=> continue).foreach{x=>
      if(Gdx.input.isKeyPressed(x)){
        pressedKey=x
        continue = false
      }
    }
    pressedKey
  }

  /**
    * Renderiza constantemente. A mesma coisa que a update
    */
  def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1) //setando a tela com uma cor
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    //Verifica a entrada de keys de cada player
    Controller.MovementSnake(gameEngine.player1, getMovement(gameEngine.player1.Keys))
    Controller.MovementSnake(gameEngine.player2, getMovement(gameEngine.player2.Keys))

    //Solicita a verificação de colisão bean X players
    Controller.MovementBean()

    //Solicita a verificação de colisao players X killerThings
    Controller.calc_Collisions()

    Thread.sleep(30)

    batch.begin() //comecar a desenhar a textura
    //Desenha por completo o player1, player2, wall, bean
    gameEngine.player1.myPositions.foreach{x=>
      drawSquare(x,gameEngine.player1.myColor,gameEngine.player1.mySize,gameEngine.player1.mySize)
    }
    gameEngine.player2.myPositions.foreach{x=>
      drawSquare(x,gameEngine.player2.myColor,gameEngine.player2.mySize,gameEngine.player2.mySize)
    }
    gameEngine.wall.myPositions.foreach{x=>
      drawSquare(x,gameEngine.wall.myColor,gameEngine.wall.mySize,gameEngine.wall.mySize)
    }
    gameEngine.bean.myPositions.foreach{x=>
      drawSquare(x,gameEngine.bean.myColor,gameEngine.bean.mySize,gameEngine.bean.mySize)
    }
    batch.end() //terminou de desenhar a textura
  }

  def resize(width: Int, height: Int): Unit = {}

  def pause(): Unit = {}

  def resume(): Unit = {}

  def hide(): Unit = {}

  /**
    * Usado para liberar espaco na memoria quando nao se utiliza mais um recurso
    */
  def dispose(): Unit = {
    batch.dispose()
  }
}
