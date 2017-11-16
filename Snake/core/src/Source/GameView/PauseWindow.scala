package Source.GameView

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, Window}

//TODO - FAZER DECENTEMENTE BONITO
class PauseWindow (var width: Int, var height: Int){

  //Variaveis para a HUD
  var atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"),atlas)
  var stage = new Stage

  var PauseMenu = new Window("PAUSE", skin)
  PauseMenu.setWidth(500)
  PauseMenu.setHeight(300)
  PauseMenu.padTop(64)
  PauseMenu.padLeft(142)
  //    PauseMenu.pack()                          //Compacta a janela ao padding escolhido em relacao ao texto
  PauseMenu.setKeepWithinStage(false)         //Impede limitacao de posicao da janela ao stage durante drag
//  PauseMenu.setMovable(false)                 //Impede movimentacao da janela por meio de drag
  PauseMenu.setPosition(width/2 - PauseMenu.getWidth/2, height/2 - PauseMenu.getHeight/2)
  stage.addActor(PauseMenu)
}
