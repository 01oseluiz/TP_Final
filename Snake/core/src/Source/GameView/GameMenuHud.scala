package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud {

  //Variaveis da HUD
  var font: BitmapFont =_
  font = new BitmapFont(Gdx.files.internal("fonts/Fonte.fnt"), false)
  var stage = new Stage
  var gameTitle = new Label("Snake", new Label.LabelStyle(font, Color.GOLD))
  var table :Table=_

  //Variaveis para os botoes
  private var atlas: TextureAtlas =_
  var skin: Skin =_
  private var textButtonStyle: TextButtonStyle =_
  private var ButtonExit, PlayButton: TextButton =_

  //Criando os botoes
  atlas = new TextureAtlas("ui/button2.pack")
  skin = new Skin(atlas)
  table = new Table(skin)
  textButtonStyle = new TextButtonStyle()

  //Imagens a serem desenhadas quando o botao eh ou nao apertado
  textButtonStyle.up = skin.getDrawable("Button2.up")
  textButtonStyle.down = skin.getDrawable("Button2.down")

  //Posicao do texto no botao ao ser pressionado
  textButtonStyle.pressedOffsetX = 2
  textButtonStyle.pressedOffsetY = -2

  //Fonte do texto no botao
  textButtonStyle.font = new BitmapFont()
  textButtonStyle.fontColor = Color.BLACK

  //Criacao dos botoes em si
  ButtonExit = new TextButton("EXIT", textButtonStyle)
  ButtonExit.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Gdx.app.exit()
  })

  PlayButton = new TextButton("START GAME", textButtonStyle)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.startGame()
  })

//  //TODO - ADICIONAR UM DEFAULT AO WINDOWSTYLE
//    var PauseMenu = new Window("PAUSE", skin)
//    var windowStyle = new WindowStyle(font, Color.BLACK,)
//    stage.addActor(PauseMenu)

//  new List[Skin](skin)
//  val list = List(skin,("one", "two", "three"))
//  private var scrollPane = new ScrollPane(list, skin)


  //Posicionando a HUD e os botoes
  table.top()
  table.setFillParent(true)                 //Faz a tabela ter as dimensoes do stage
  table.add(gameTitle).expandX().padTop(10) //Distancia do topo
  table.getCell(gameTitle).spaceBottom(150)  //Distancia das celulas de baixo da tabela
  table.row()                                   //Proxima linha da tablea
  table.add(PlayButton)
  table.row()
  table.add(ButtonExit)
  stage.addActor(table)
}
