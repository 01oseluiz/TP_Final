package Source.GameView


import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class ScreenDefault extends Game {

  //Variaveis que servirao para desenho
  var batch: SpriteBatch = _
  var gameOverHud: GameOverHud=_
  var gameTitle: GameMenuHud=_

  /**
    * Executado assim que o app eh iniciado
    */
  override def create(): Unit = {
    batch = new SpriteBatch()
    gameOverHud = new GameOverHud
    gameTitle = new GameMenuHud
    setScreen(new GameScreen(this)) //mudando de tela
//    setScreen(new GameMenuScreen(this))
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
