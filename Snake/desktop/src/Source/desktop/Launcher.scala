package Source.desktop

import Source.GameView.ScreenDefault
import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}

/**
  * main do jogo. Responsavel por executa-lo
  */
object Launcher {
  def main(arg: Array[String]): Unit = {
    val config = new LwjglApplicationConfiguration
    new LwjglApplication(new ScreenDefault, config)

    config.width = 840
    config.height = 680
    config.title = "Snake"
    config.vSyncEnabled = true
  }
}
