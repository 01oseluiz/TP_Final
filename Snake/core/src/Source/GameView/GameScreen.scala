package Source.GameView

//TODO- fazer matriz das posicoes

import Source.GameEngine.{Bean, Player}
import Source.GameController._

import com.badlogic.gdx.{Gdx,Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
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

  private def drawSquare(p: Player):Unit = {
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(p.myColor)             //setando cor branca para os quadrados
    p.myPositions.foreach(i=> shapeRenderer.rect(i.P_x, i.P_y, p.mySize, p.mySize)) //10 eh o lado do quadrado
    shapeRenderer.end()
  }


  private def drawBean(b: Bean):Unit = {
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(b.myColor)             //setando cor branca para os quadrados
    shapeRenderer.rect(b.myPositions.head.P_x, b.myPositions.head.P_y, b.mySize, b.mySize)        //10 eh o lado do quadrado
    shapeRenderer.end()
  }


  /**
    * Funciona como a create. Eh a primeira coisa executada ao ser chamada
    */
  def show(): Unit = {
    batch = new SpriteBatch //lugar a ser desenhado como um papel
  }

  //Todo-refazer com função lambda
  def getMovement(Keys:List[Int]): Int = {
    if (Gdx.input.isKeyPressed(Keys.head)) Keys.head
    else if (Gdx.input.isKeyPressed(Keys(1))) Keys(1)
    else if (Gdx.input.isKeyPressed(Keys(2))) Keys(2)
    else if (Gdx.input.isKeyPressed(Keys(3))) Keys(3)
    else -1
  }
  /**
    * Renderiza constantemente. A mesma coisa que a update
    */
  def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1) //setando a tela com uma cor
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    Controller.MovementSnake(gameEngine.player1, getMovement(gameEngine.player1.Keys))
    Controller.MovementSnake(gameEngine.player2, getMovement(gameEngine.player2.Keys))

    Controller.MovementBean()

    Thread.sleep(40)

    batch.begin() //comecar a desenhar a textura
    drawSquare(gameEngine.player1)
    drawSquare(gameEngine.player2)
    drawBean(gameEngine.bean)

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
