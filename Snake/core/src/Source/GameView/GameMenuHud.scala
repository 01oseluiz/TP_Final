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
  private var BackButton: TextButton =_
  private var PlayButton, ExitButton, ModsButton, PlayersButton: Button =_
  var ModsMenu = new Window("MODS", skin, "mods")
  var lista = new List[String](skin)


  def test (listagem : Array[(String,String,String,String,String,String)]): Unit = {
    val mods = new com.badlogic.gdx.utils.Array[String]

    listagem.foreach{mod=>
      mods.add("VERSION: " + mod._1 + "    |   AUTHOR: " + mod._3 + "    |   DATE: " + mod._4 +
                    "     | TITLE: " + mod._5 + "     |DESCRIPTION: " + mod._6)
    }

    lista.setItems(mods)
  }


  //ScrollPane e SelectBox
  var scrollPane = new ScrollPane(lista, skin)
  //  scrollPane.setScrollingDisabled(true, false)

  var selectBox = new SelectBox[String](skin)
  selectBox.setItems("            1 players            ","            2 players            ", "            3 players", "            4 players")


  //Criacao dos botoes
  ExitButton = new Button(skin, "exit")
  ExitButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Gdx.app.exit()
  })

  ModsButton = new Button(skin, "mods")
  ModsButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      ModsMenu.setVisible(true)
      BackButton.setVisible(true)
      scrollPane.setVisible(true)
    }
  })

  PlayersButton = new Button(skin, "players")
  PlayersButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      selectBox.showList()
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

  PlayButton = new Button(skin, "play")
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      println((selectBox.getSelectedIndex+1) + " players e o mod escolhido: " + (lista.getSelectedIndex+1))
      Controller.PLAYER_NUMBER = selectBox.getSelectedIndex+1
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

  scrollPane.setPosition(ModsMenu.getX + 50, ModsMenu.getY + 35)
  scrollPane.setSize(400, 235)
  scrollPane.setVisible(false)

  BackButton.setVisible(false)
  BackButton.setPosition(width/2 - BackButton.getWidth/2 , ModsMenu.getY + 10)

  ExitButton.setPosition(372, 108)
  ExitButton.setSize(40,30)
  ModsButton.setPosition(82, 256)
  ModsButton.setSize(170,44)
  PlayButton.setPosition(280, 250)
  PlayButton.setSize(86,56)
  PlayersButton.setPosition(397, 260)
  PlayersButton.setSize(170,44)

  selectBox.setPosition(PlayersButton.getX + 2, PlayersButton.getY + 4)



  stage.addActor(PlayButton)
  stage.addActor(ExitButton)
  stage.addActor(ModsButton)
  stage.addActor(selectBox)
  stage.addActor(PlayersButton)
  stage.addActor(ModsMenu)
  stage.addActor(scrollPane)
  stage. addActor(BackButton)
}
