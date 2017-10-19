package Source.GameView

import com.badlogic.gdx.{Gdx, Input, Screen}
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.{Sprite, SpriteBatch}
import Source.GameEngine.{Bean, BeanPosition, Movement, Player}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
  * Tela de jogo
  */
class GameScreen extends Screen {

  private var batch: SpriteBatch = _ // o lugar a ser desenhada a textura/imagem (papel)

  //Variaveis para a camera
  var width = Gdx.graphics.getWidth
  var height = Gdx.graphics.getHeight
  val camera = new OrthographicCamera(width, height)
  camera.position.set(width/2, height/2, 0)
  camera.update()
  val shapeRenderer = new ShapeRenderer
  shapeRenderer.setProjectionMatrix(camera.combined)

  //Variaveis dos players e beans
  var player1: Player = new Player(40, 40)
  var player2: Player = new Player(40, height - 40)
  var bean: Bean = new Bean(width, height)

  /**
    * FAZ ALGO
    * @param p
    */
  private def drawSquare(p: Player) = {
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(1, 1, 1, 1)             //setando cor branca para os quadrados
    shapeRenderer.rect(p.posX, p.posY, 10, 10)        //10 eh o lado do quadrado
    shapeRenderer.end()
  }


  private def drawBean(b: Bean) = {
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.setColor(1,0,0,1)             //setando cor branca para os quadrados
    shapeRenderer.rect(b.posX, b.posY, 10, 10)        //10 eh o lado do quadrado
    shapeRenderer.end()
  }


  /**
    * Funciona como a create. Eh a primeira coisa executada ao ser chamada
    */
  def show(): Unit = {
    batch = new SpriteBatch //lugar a ser desenhado como um papel
  }

  /**
    * Renderiza constantemente. A mesma coisa que a update
    */
  def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1) //setando a tela com uma cor
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    //Realiza a movimentacao dos players
    new Movement(player1, 1)
    new Movement(player2, 2)

    //Spawna um novo bean
    new BeanPosition(player1, player2, bean, width, height)

    batch.begin() //comecar a desenhar a textura
    drawSquare(player1)
    drawSquare(player2)
    drawBean(bean)

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
