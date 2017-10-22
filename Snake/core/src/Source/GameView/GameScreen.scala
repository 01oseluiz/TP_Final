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
  def drawSquare(p: Position, c:Color, size_x:Int, size_y:Int):Unit = {
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

    batch.begin() //comecar a desenhar a textura
    Controller.nextInteraction()
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
