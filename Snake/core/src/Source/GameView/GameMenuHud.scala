package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud(var width: Int, var height: Int) {

  //Variaveis da HUD
  var font: BitmapFont =_
  font = new BitmapFont(Gdx.files.internal("fonts/Fonte.fnt"), false)
  var stage = new Stage
//  var gameTitle = new Label("Snake", new Label.LabelStyle(font, Color.GOLD))

  //Variaveis para os botoes
  private val atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"),atlas)
  private val textButtonStyle = new TextButtonStyle()
  private var ExitButton/*, PlayButton*/: TextButton =_
  private val ButtonStyle = new ButtonStyle()
  private var newPlayButton: Button =_


  //Imagens a serem desenhadas quando o botao eh ou nao apertado
  textButtonStyle.up = skin.getDrawable("Button.up")
  textButtonStyle.down = skin.getDrawable("Button.down")
  ButtonStyle.up = skin.getDrawable("PlayButton.up")
  ButtonStyle.down = skin.getDrawable("PlayButton.up")

  //Posicao do texto no botao ao ser pressionado
  textButtonStyle.pressedOffsetX = 1
  textButtonStyle.pressedOffsetY = -1

  //Fonte do texto no botao
  textButtonStyle.font = new BitmapFont()
  textButtonStyle.fontColor = Color.BLACK

  //Criacao dos botoes
  ExitButton = new TextButton("EXIT", textButtonStyle)
  //  ExitButton.padTop(20).padBottom(20).padLeft(5).padRight(5)
  ExitButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Gdx.app.exit()
  })

//  PlayButton = new TextButton("START GAME", textButtonStyle)
//  PlayButton.setSize(100,50)
//  PlayButton.addListener(new ClickListener(){
//    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.startGame()
//  })

  newPlayButton = new Button(ButtonStyle)
  newPlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.startGame()
  })

  //TODO - ARRUMAR A LISTA E O SCROLL
  var listStyle = new ListStyle(new BitmapFont(), Color.BLACK, Color.WHITE, skin.getDrawable("selected"))
//  var list = new List[String](skin)
  var list = new List[String](listStyle)
  list.setItems("mod 1", "mod 2", "mod 3", "mod 4", "mod 5", "mod 6", "mod 7", "mod 8", "mod 9", "mod 10", "mod 11", "mod 41", "mod 42", "mod 43", "mod 44", "mod 45")

//  var list2 = new SelectBox[String](skin)
  var scrollStyle = new ScrollPaneStyle()
  scrollStyle.hScrollKnob = skin.getDrawable("Button.up")
  scrollStyle.vScrollKnob = skin.getDrawable("Button.down")
  var selectStyle = new SelectBoxStyle(new BitmapFont(), Color.BLACK, skin.getDrawable("selected"), scrollStyle, listStyle)
  var list2 = new SelectBox[String](selectStyle)
  list2.setItems("mod 1", "mod 2", "mod 3", "mod 4")
  list2.setPosition(400, 200)

  var scrollPane = new ScrollPane(list, skin)
  scrollPane.setPosition(50, 50)

  //Posicionando a HUD e os botoes

//  gameTitle.setPosition((width - gameTitle.getWidth)/2, height - gameTitle.getHeight)
//  PlayButton.setPosition((width - PlayButton.getWidth)/2, 100)

  //  stage.addActor(gameTitle)
  //  stage.addActor(PlayButton)

  ExitButton.setSize(100,50)
  newPlayButton.setPosition(300,254)
  ExitButton.setPosition((width - ExitButton.getWidth)/2, newPlayButton.getY - 100)

  stage.addActor(newPlayButton)
  stage.addActor(ExitButton)
  stage.addActor(scrollPane)
  stage.addActor(list2)
}
