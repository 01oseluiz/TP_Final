package Source.GameView

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class ScreenDefault extends Game {
  //Variavel que serve como plano de desenho (papel)
  var batch: SpriteBatch = _

  /**
    * Executado assim que o app eh iniciado
    */
  override def create(): Unit = {
    batch = new SpriteBatch()
    this.screen = new GameScreen(this) //mudando de tela
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
