package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameOverHud (var width: Int, var height: Int){

  //Variaveis da HUD
  var font: BitmapFont =_
  font = new BitmapFont(Gdx.files.internal("fonts/Fonte.fnt"), false)
  var stage = new Stage
  var gameOverLabel = new Label("GAME OVER", new Label.LabelStyle(font, Color.RED))

  //Variaveis para mostras estatisticas
  var i: Int = 0
  var time : Double =_  //tempo a ser recebido da controller
  var totalPlayers:Int = 2 //numero total de players a serem exibidos

  /**
    * Mostra cada player e seu desempenho na gameOverHud
    * @param name nome a ser recebido da controller
    * @param ran  quantidade de pixeis percorridos recebida da controller
    * @param eaten  quantidade de beans comidas recebida da controller
    * @param player numero do player recebido da controller
    * @param winner venceu ou nao
    */
  def playerStatisticsShow (name: String, ran: Int, eaten: Int, player: Int, winner: Boolean, playerTime: Double): Unit = {
    var playerLabel:Label = null
    this.time = playerTime

    if (winner) {
      playerLabel = new Label( name + "\nPlayer " + player + "\nBeans eated: " + eaten + "\nRan: " + ran, new Label.LabelStyle(new BitmapFont(), Color.GOLD))
    }
    else {
      playerLabel = new Label( name + "\nPlayer " + player + "\nBeans eated: " + eaten + "\nRan: " + ran, new Label.LabelStyle(new BitmapFont(), Color.GREEN))
    }

    playerLabel.setPosition(width/6 + (player-1)*width/totalPlayers, height/2 )
    stage.addActor(playerLabel)
  }

  //Variaveis para os botoes
  private var atlas: TextureAtlas =_
  private var skin: Skin =_
  private var textButtonStyle: TextButtonStyle =_
  private var MenuButton, PlayButton: TextButton =_

  //Criando os botoes
  atlas = new TextureAtlas("ui/atlas.pack")
  skin = new Skin(atlas)
  textButtonStyle = new TextButtonStyle()

  //Imagens a serem desenhadas quando o botao eh ou nao apertado
  textButtonStyle.up = skin.getDrawable("Button.up")
  textButtonStyle.down = skin.getDrawable("Button.down")

  //Posicao do texto no botao ao ser pressionado
  textButtonStyle.pressedOffsetX = 1
  textButtonStyle.pressedOffsetY = -1

  //Fonte do texto no botao
  textButtonStyle.font = new BitmapFont()
  textButtonStyle.fontColor = Color.BLACK

  //Criacao dos botoes em si
  MenuButton = new TextButton("BACK TO MENU", textButtonStyle)
  MenuButton.setSize(126,50)
  MenuButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.backToMenu() /*Gdx.app.exit()*/
  })

  PlayButton = new TextButton("PLAY AGAIN", textButtonStyle)
  PlayButton.setSize(100,50)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.playAgain()
  })

  def setGameOverScreenHud(): Unit ={
    this.stage.clear()

    Controller.getStatistics()

    //Posicionando a HUD e os botoes
    gameOverLabel.setPosition(width/2 - gameOverLabel.getWidth/2, height - gameOverLabel.getHeight)
    PlayButton.setPosition(PlayButton.getWidth/4, height/10)
    MenuButton.setPosition(width - MenuButton.getWidth*5/4, height/10)

    stage.addActor(gameOverLabel)
    stage.addActor(PlayButton)
    stage.addActor(MenuButton)
  }
}
