package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera, Texture}

class GameMenuScreen(var game: ScreenDefault) extends Screen {

  //Variaveis para a camera
  var width:Int = Gdx.graphics.getWidth
  var height:Int = Gdx.graphics.getHeight
  val camera = new OrthographicCamera(width, height)
  camera.position.set(width/2, height/2, 0)
  camera.update()

  //Variaveis para o background
  var background = new Sprite(new Texture("ui/BackgroundInicial.png"))

  /**
    * Muda a tela para a tela de jogo
    */
  def StartGame: Unit ={
    game.setScreen(new GameScreen(game))
    Gdx.input.setInputProcessor(null)
  }

  def show(): Unit = {
    Controller.setGameMenu(this)
    game.gameMenuHud.ListaMods()
    //Permite q os botoes sejam clicados
    Gdx.input.setInputProcessor(game.gameMenuHud.stage)
  }

  def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(0, 0, 0, 0) //setando a tela com uma cor
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT) //limpando a tela com a cor

    game.batch.begin() //comecar a desenhar a textura
    game.batch.draw(background, 0, 0, width, height)
    //    game.batch.draw(background, background.getX, background.getY)
    game.batch.end() //terminou de desenhar a textura

    game.gameMenuHud.stage.draw() //Desesenha a hud
    game.gameMenuHud.stage.act()  //Permite interacao do stage com o usuario (ScrollPane e SelectBox)
//    game.gameMenuHud.modInfoLabel.setText("    " + game.gameMenuHud.descriptions.get(game.gameMenuHud.lista.getSelectedIndex).toString)
    game.gameMenuHud.modInfoLabel.setText("    Version: " + game.gameMenuHud.versions.get(game.gameMenuHud.lista.getSelectedIndex).toString +
      "\n\n    Author: " + game.gameMenuHud.authors.get(game.gameMenuHud.lista.getSelectedIndex).toString +
      "\n\n    Date: " + game.gameMenuHud.dates.get(game.gameMenuHud.lista.getSelectedIndex).toString)
  }

  def resize(width: Int, height: Int): Unit = {}

  def pause(): Unit = {}

  def resume(): Unit = {}

  def hide(): Unit = {}

  def dispose(): Unit = {
    game.batch.dispose()
    background.getTexture.dispose()
    game.gameMenuHud.stage.dispose()
    game.gameMenuHud.skin.dispose()
  }
}