package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}

class GameOverScreen(private var game: ScreenDefault) extends Screen {

  //Variaveis para a camera
  var width:Int = Gdx.graphics.getWidth
  var height:Int = Gdx.graphics.getHeight
  val camera = new OrthographicCamera(width, height)
  camera.position.set(width/2, height/2, 0)
  camera.update()

  private var setedOnce = false

  /**
    * Muda a tela para a tela de jogo
    */
  def PlayAgain: Unit ={
    game.setScreen(new GameScreen(game))
    }

  def show(): Unit = {
    Controller.setGameOver(this)

    //Permite q o botao seja clicado
    Gdx.input.setInputProcessor(game.gameOverHud.stage)
  }

  def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 1) //setando a tela com uma cor
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    if(!setedOnce){
      game.gameOverHud.setGameOverScreenHud()
      setedOnce = true
    }

    //    game.batch.setProjectionMatrix(game.gameOverHud.stage.getCamera.combined)
    game.batch.begin() //comecar a desenhar a textura

    game.batch.end() //terminou de desenhar a textura

    game.gameOverHud.stage.draw() //Desesenha a HUD
  }

  def resize(width: Int, height: Int): Unit = {}

  def pause(): Unit = {}

  def resume(): Unit = {}

  def hide(): Unit = {}

  def dispose(): Unit = {
    game.batch.dispose()
    game.gameOverHud.stage.dispose()
    game.gameOverHud.font.dispose()
  }
}