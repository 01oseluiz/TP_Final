package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud(var width: Int, var height: Int) {

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
  atlas = new TextureAtlas("ui/atlas.pack")
  skin = new Skin(Gdx.files.internal("ui/skin.json"),atlas)
  table = new Table(skin)
  textButtonStyle = new TextButtonStyle()

  //Imagens a serem desenhadas quando o botao eh ou nao apertado
  textButtonStyle.up = skin.getDrawable("button.up")
  textButtonStyle.down = skin.getDrawable("button.down")

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

  //TODO - ARRUMAR A LISTA E O SCROLL
//  new List[Skin](skin)

//    var list = new List[Int](/*skin,*/ new Array(2) = ("one", "two"), new ListStyle(font, Color.BLACK, Color.WHITE, skin.getDrawable("selected")))
//    list.setPosition(50,50)
//    var list = new List(new ListStyle(font, Color.BLACK, Color.WHITE, skin.getDrawable("selected")), Array[String]("mod1", "mod2", "mod3"))
//    private var scrollPane: ScrollPane =_
//    scrollPane = new ScrollPane(list, new ScrollPaneStyle())
//    scrollPane.setPosition(80,50)

//  private var scrollPane = new ScrollPane(list, skin)

  var list = new List(skin)
  val lista = ("mod1", "mod2")
  var scrollPane = new ScrollPane(list, skin)
  scrollPane.setPosition(50, 50)

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
  stage.addActor(scrollPane)
}
