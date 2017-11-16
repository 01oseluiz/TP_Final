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
  var table = new Table()
  table.setBounds(0, 0, width, height)

  //Variaveis para mostras estatisticas
  var i: Int = 0
  var time : Double =_  //tempo a ser recebido da controller
  var totalPlayers:Int = 2 //numero total de players a serem exibidos

//  var name = nome recebido da controller
//  var ran = quantidade de pixeis percorridos recebido da controller
//  var eaten = quantidade de beans comidas recebida da controller
//  var player = numero do player recebido da controller
//  var playerLabel = new Label("Player " + player + ": " + name + ". Beans eated: " + eaten + ". Ran: " + ran, new Label.LabelStyle(new BitmapFont(), Color.GREEN))

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
  private var ButtonMenu, PlayButton: TextButton =_

  //Criando os botoes
  atlas = new TextureAtlas("ui/atlas.pack")
  skin = new Skin(atlas)
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
  ButtonMenu = new TextButton("BACK TO MENU", textButtonStyle)
  ButtonMenu.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.backToMenu() /*Gdx.app.exit()*/
  })

  PlayButton = new TextButton("PLAY AGAIN", textButtonStyle)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.playAgain()
  })

  def setGameOverScreenHud(): Unit ={
    this.stage.clear()

    Controller.getStatistics()

    //POSICIONANDO SEM USAR TABLE
    gameOverLabel.setPosition(width/2 - gameOverLabel.getWidth/2, height - gameOverLabel.getHeight)
    stage.addActor(gameOverLabel)

    PlayButton.setPosition(PlayButton.getWidth/4, height/10)
    stage.addActor(PlayButton)
    ButtonMenu.setPosition(width - ButtonMenu.getWidth*5/4, height/10)
    stage.addActor(ButtonMenu)
  }

  //Posicionando a HUD e os botoes

  //USANDO TABLE
//  table.top()
//  table.setFillParent(true)                       //faz a tabela ter as dimensoes da stage
//  table.add().width(table.getWidth/3)
//  table.add(gameOverLabel).padTop(10).width(table.getWidth/3).center()/*.padLeft(gameOverLabel.getWidth/4)*/ //Distancia do topo
//  table.add().width(table.getWidth/3)
//  table.getCell(gameOverLabel).spaceBottom(50)  //Distancia das celulas de baixo da tabela
//  table.row()                                   //Proxima linha da table
////  table.add(timeLabel).expandX()
////  table.row()
//  while(i<2){
//    table.add(playerLabel(i)).width(table.getWidth/4)
////    table.row()
//    i+= 1
//  }
//  table.add().width(table.getWidth/4)
//  table.add().width(table.getWidth/4)
//  table.debug()
//  table.row()
//  table.add(PlayButton).bottom().left()
////  table.add().width(table.getWidth/4)
////  table.add().width(table.getWidth/4)
////  table.row()
//  table.add(ButtonMenu).bottom().right()
//  stage.addActor(table)
}
