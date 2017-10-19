package Source.GameEngine

import com.badlogic.gdx.Game
import Source.GameView.GameScreen


class GameEngine extends Game {

  /**
    * Executado assim que o app eh iniciado
    */
  def create(): Unit = {
    setScreen(new GameScreen) //Mudando de tela
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
