package Source.GameView


import Source.GameController.Controller
import com.badlogic.gdx.{Game, Gdx}
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class ScreenDefault extends Game {

  //Variaveis para UI
  var batch: SpriteBatch = _
  var gameOverHud: GameOverHud=_
  var gameMenuHud: GameMenuHud=_
  var pauseHud: PauseWindow=_
  var width: Int =_
  var height: Int =_

  /**
    * Executado assim que o app eh iniciado
    */
  override def create(): Unit = {
    width = Gdx.graphics.getWidth
    height = Gdx.graphics.getHeight
    batch = new SpriteBatch()
    gameOverHud = new GameOverHud(width, height)
    gameMenuHud = new GameMenuHud(width, height)
    pauseHud = new PauseWindow(width, height)

    //Mudanca de tela
    Controller.setGameDefault(this)
    setScreen(new GameMenuScreen(this))
    dispose()
  }

  /**
    * Redefine as dimensoes da janela sempre que o jogo eh colocado em foco
    */
  override def resize(width: Int, height: Int): Unit = {
    super.resize(width, height)
  }

  /**
    * Renderiza constantemente. A mesma coisa que a update
    */
  override def render(): Unit = {
    super.render()
  }

  /**
    * Executado quando jogo fica fora de foco
    */
  override def pause(): Unit = {
    super.pause()
  }

  /**
    * Executado quando jogo volta a ser o foco
    */
  override def resume(): Unit = {
    super.resume()
  }

  /**
    * Usado para liberar espaco na memoria quando nao se utiliza mais um recurso
    */
  override def dispose(): Unit = {
    super.dispose()
  }
}
