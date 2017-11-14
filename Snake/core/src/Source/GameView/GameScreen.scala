package Source.GameView

import Source.GameEngine.Position
import Source.GameController._
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

/**
  * Tela de jogo
  */
class GameScreen(private var game: ScreenDefault) extends Screen {
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
    * @param p posicao a ser desenhada
    * @param c cor a ser desenhada
    */
  def drawSquare(p: Position, c:Color):Unit = {
    shapeRenderer.begin(ShapeType.Filled)

    if(p.P_color==null)shapeRenderer.setColor(c)
    else shapeRenderer.setColor(p.P_color)

    shapeRenderer.rect(p.P_x, p.P_y, p.size, p.size)

    shapeRenderer.end()
  }

  /**
    * Muda a tela para a tela de gameOver
    */
  def GameOver: Unit ={
    game.setScreen(new GameOverScreen(game))
//    dispose()
  }

//  /**
//    * Muda a tela para a tela de jogo
//    */
//  def PlayAgain: Unit ={
//    game.setScreen(new GameScreen(game))
////    dispose()
//  }

  /**
    * Renderiza constantemente. A mesma coisa que a update
    */
  def render(delta: Float): Unit = {
    //TODO - FAZER A CONTROLLER ESCOLHER A COR/IMAGEM DE FUNDO
    Gdx.gl.glClearColor(0, 0, 0, 1) //setando a tela com uma cor
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    game.batch.begin() //comecar a desenhar a textura
    Controller.nextInteraction()
    game.batch.end() //terminou de desenhar a textura
  }

  /**
    * Funciona como a create. É a primeira coisa executada ao ser chamada
    */
  def show(): Unit = Controller.setGameView(this)

  def resize(width: Int, height: Int): Unit = {}

  def pause(): Unit = {}

  def resume(): Unit = {}

  def hide(): Unit = {}

  /**
    * Usado para liberar espaco na memoria quando nao se utiliza mais um recurso
    */
  def dispose(): Unit = {
    game.batch.dispose()
    shapeRenderer.dispose()
  }
}
