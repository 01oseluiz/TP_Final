package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}

class GameOverScreen(private var game: ScreenDefault) extends Screen {

  //Variaveis para a camera
  var width:Int = Gdx.graphics.getWidth
  var height:Int = Gdx.graphics.getHeight
  val camera = new OrthographicCamera(width, height)
  camera.position.set(width/2, height/2, 0)
  camera.update()

  //Variaveis para o background
  var background:Sprite = _

  //Variavel para controle de repeticoes na render
  private var setedOnce = false

  /**
    * Muda a tela para a tela de jogo
    */
  def PlayAgain: Unit ={
    game.setScreen(new GameScreen(game))
    Gdx.input.setInputProcessor(null)
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
      background = new Sprite(new Texture("ui/BackgroundOver.png"))
      game.gameOverHud.setGameOverScreenHUD()
      setedOnce = true
    }

    //    game.batch.setProjectionMatrix(game.gameOverHud.stage.getCamera.combined)
    game.batch.begin() //comecar a desenhar a textura
    game.batch.draw(background, 0, 0, width, height)

    game.gameOverHud.player1Highlight.draw(game.batch)
    if(Controller.playerNumber >= 2) game.gameOverHud.player2Highlight.draw(game.batch)
    if(Controller.playerNumber >= 3) game.gameOverHud.player3Highlight.draw(game.batch)
    if(Controller.playerNumber >= 4) game.gameOverHud.player4Highlight.draw(game.batch)
    game.batch.end() //terminou de desenhar a textura

    game.gameOverHud.stage.draw() //Desesenha a HUD
    game.gameOverHud.stage.act()  //Permite interacao do player com a HUD (botoes)
  }

  /**
    * Muda a tela para a tela de jogo
    */
  def BackToMenu: Unit ={
    game.setScreen(new GameMenuScreen(game))
  }

  def resize(width: Int, height: Int): Unit = {}

  def pause(): Unit = {}

  def resume(): Unit = {}

  def hide(): Unit = {}

  def dispose(): Unit = {
    game.batch.dispose()
    background.getTexture.dispose()
    game.gameOverHud.stage.dispose()
    game.gameOverHud.skin.dispose()
  }
}