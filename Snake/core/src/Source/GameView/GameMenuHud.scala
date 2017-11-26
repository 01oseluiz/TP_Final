package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud(var width: Int, var height: Int) {

  //Variaveis da HUD
  var stage = new Stage
  private val atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"), atlas)
  var playersLabel = new Label("Players number:", skin, "blackSmallLabel")
  private var ExitButton: TextButton =_
  private var PlayButton: Button =_

  //TODO - resolver bug de animacao dos botoes
  var textButtonStyle = new TextButtonStyle()
  textButtonStyle.up = skin.getDrawable("Button.up")
  textButtonStyle.down = skin.getDrawable("Button.down")
  textButtonStyle.font = new BitmapFont()
  textButtonStyle.fontColor = Color.BLACK
  textButtonStyle.pressedOffsetX = 0
  textButtonStyle.pressedOffsetY = -0

  //Criacao dos botoes
  ExitButton = new TextButton("EXIT", textButtonStyle)
  //  ExitButton.padTop(20).padBottom(20).padLeft(5).padRight(5)
  ExitButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Gdx.app.exit()
  })


  PlayButton = new Button(skin)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      //TODO - chamar funcao da controller que realmente aplica a selecao dos players
      println(selectBox.getSelected + " mod escolhido: " + (list.getSelectedIndex+1))
      Controller.startGame()
    }
  })


  //Criacao da lista, ScrollPane e SelectBox
  var list = new List[String](skin)
  list.setItems("mod 1", "mod 2", "mod 3", "mod 4", "mod 5", "mod 6", "mod 7", "mod 8", "mod 9", "mod 10", "mod 11", "mod 12", "mod 13", "mod 14", "mod 15")


  var scrollPane = new ScrollPane(list, skin)
  //  scrollPane.setScrollingDisabled(true, false)

  var selectBox = new SelectBox[String](skin)
  selectBox.setItems("2 players", "3 players", "4 players")


  //Posicionando a HUD e os botoes
  PlayButton.setPosition(300,254)
  ExitButton.setSize(100,50)
  ExitButton.setPosition((width - ExitButton.getWidth)/2, PlayButton.getY - 100)
  selectBox.setPosition(560, 258)
  playersLabel.setPosition(selectBox.getX-116, selectBox.getY)
  scrollPane.setPosition(50, 154)


  stage.addActor(PlayButton)
  stage.addActor(ExitButton)
  stage.addActor(scrollPane)
  stage.addActor(selectBox)
  stage.addActor(playersLabel)
}
