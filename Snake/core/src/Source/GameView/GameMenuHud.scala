package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.{Label, Skin, Table, TextButton}
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud {

  //Variaveis da HUD
  var font: BitmapFont =_
  font = new BitmapFont(Gdx.files.internal("fonts/Fonte.fnt"), false)
  var stage = new Stage
  var gameTitle = new Label("Snake", new Label.LabelStyle(font, Color.GOLD))
  var table = new Table()

  //Variaveis para os botoes
  private var atlas: TextureAtlas =_
  var skin: Skin =_
  private var textButtonStyle: TextButtonStyle =_
  private var ButtonExit, PlayButton: TextButton =_

  //Criando os botoes
  atlas = new TextureAtlas("ui/button2.pack")
  skin = new Skin(atlas)
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

  //Posicionando a HUD e os botoes
  table.top()
  table.setFillParent(true)
  table.add(gameTitle).expandX().padTop(10) //Distancia do topo
  table.getCell(gameTitle).spaceBottom(150)  //Distancia das celulas de baixo da tabela
  table.row()                                   //Proxima linha da tablea
  table.add(PlayButton)
  table.row()
  table.add(ButtonExit)
  stage.addActor(table)
}
