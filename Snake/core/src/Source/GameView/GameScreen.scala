package Source.GameView

import Source.GameEngine.Position
import Source.GameController._
import com.badlogic.gdx.{Gdx, Input, Screen}
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

  //Variaveis para UI
  var paused: Boolean = false
  var isBackgroundSet: Boolean = false
  var background: Color =_

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
  }

  def isPaused: Unit ={
    if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
      paused = !paused
    }
  }

  /**
    * Renderiza constantemente. A mesma coisa que a update
    */
  def render(delta: Float): Unit = {

    if(isBackgroundSet){
      //TODO - PENSAR EM COMO RECEBER O VETOR COM AS CORES
      Gdx.gl.glClearColor(0, 1, 0, 1) //setando a tela com a cor escolhida pela controller
    }
    else{
      Gdx.gl.glClearColor(0, 0, 0, 1) //setando a tela com uma cor default
    }
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    isPaused
    game.batch.begin() //comecar a desenhar a
    if(paused)
      {
        game.pauseHud.stage.draw()
      }
    else {
      Controller.nextInteraction()
    }
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
    game.pauseHud.stage.dispose()
    shapeRenderer.dispose()
  }
}
