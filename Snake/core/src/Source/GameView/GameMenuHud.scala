package Source.GameView

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Label, Table}
import com.badlogic.gdx.graphics.g2d.BitmapFont

class GameMenuHud {
  var stage = new Stage
  var gameTitle = new Label("Snake", new Label.LabelStyle(new BitmapFont(), Color.GOLD))
  var table = new Table()

  table.top()
  table.setFillParent(true)

  table.add(gameTitle).expandX().padTop(10) //Distancia do topo
  table.row()
  //table.add("Ha").expandX()

  stage.addActor(table)
}
