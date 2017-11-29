package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud(var width: Int, var height: Int) {

  //Variaveis da HUD
  var stage = new Stage
  private val atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"), atlas)
  var playersLabel = new Label("Players number:", skin, "blackSmallLabel")
  private var ExitButton, ModsButton, BackButton: TextButton =_
  private var PlayButton: Button =_
  var ModsMenu = new Window("MODS", skin, "mods")

  //Criacao da lista, ScrollPane e SelectBox
  var list = new List[String](skin)
  list.setItems(  "mod 1", "mod 2", "mod 3", "mod 4", "mod 5", "mod 6", "mod 7", "mod 8", "mod 9",
    "mod 10", "mod 11", "mod 12", "mod 13", "mod 14", "mod 15", "mod 16", "mod 17",
    "mod 18", "mod 19", "mod 20", "mod 21", "mod 22", "mod 23", "mod 24", "mod 25",
    "mod 26", "mod 27", "mod 28", "mod 29", "mod 30", "mod 31", "mod 32", "mod 33",
    "mod 34", "mod 35", "mod 36", "mod 37", "mod 38", "mod 39", "mod 40")

  var scrollPane = new ScrollPane(list, skin)
  //  scrollPane.setScrollingDisabled(true, false)

  var selectBox = new SelectBox[String](skin)
  selectBox.setItems("2 players", "3 players", "4 players")


  //Criacao dos botoes
  ExitButton = new TextButton("Exit", skin)
  //  ExitButton.padTop(20).padBottom(20).padLeft(5).padRight(5)
  ExitButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Gdx.app.exit()
  })

  ModsButton = new TextButton("Mods", skin)
  ModsButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      ModsMenu.setVisible(true)
      BackButton.setVisible(true)
      scrollPane.setVisible(true)
    }
  })

  BackButton = new TextButton("BACK", skin)
  BackButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      ModsMenu.setVisible(false)
      BackButton.setVisible(false)
      scrollPane.setVisible(false)
    }
  })

  PlayButton = new Button(skin)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      //TODO - chamar funcao da controller que realmente aplica a selecao dos players
      println(selectBox.getSelected + " mod escolhido: " + (list.getSelectedIndex+1))
      Controller.startGame()
    }
  })


  //Posicionando a HUD e os botoes
  ModsMenu.setWidth(500)
  ModsMenu.setHeight(330)
  ModsMenu.padTop(60)
  ModsMenu.padLeft(150)
  //  ModsMenu.pack()                          //Compacta a janela ao padding escolhido em relacao ao texto
  //  ModsMenu.setKeepWithinStage(false)         //Impede limitacao de posicao da janela ao stage durante drag
  ModsMenu.setMovable(false)                 //Impede movimentacao da janela por meio de drag
  ModsMenu.setVisible(false)
  ModsMenu.setPosition(width/2 - ModsMenu.getWidth/2, height/2 - ModsMenu.getHeight/2)

  //  selectBox.setPosition(560, 258)
  selectBox.setPosition(600, 258)
  playersLabel.setPosition(selectBox.getX-116, selectBox.getY)
  //  scrollPane.setPosition(50, 154)
  scrollPane.setPosition(ModsMenu.getX + 50, ModsMenu.getY + 60)
  scrollPane.setSize(400, 200)
  scrollPane.setVisible(false)

  BackButton.setVisible(false)
  BackButton.setPosition(width/2 - BackButton.getWidth/2 , ModsMenu.getY + 10)
  PlayButton.setPosition(300,254)
  ExitButton.setSize(100,50)
  ExitButton.setPosition((width - ExitButton.getWidth)/2, PlayButton.getY - 100)
  ModsButton.setSize(100, 50)
  ModsButton.setPosition(100, 210)



  stage.addActor(PlayButton)
  stage.addActor(ExitButton)
  stage.addActor(ModsButton)
  stage.addActor(selectBox)
  stage.addActor(playersLabel)
  stage.addActor(ModsMenu)
  stage.addActor(scrollPane)
  stage. addActor(BackButton)
}
