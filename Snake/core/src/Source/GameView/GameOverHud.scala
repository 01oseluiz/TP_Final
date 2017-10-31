package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.{Label, Skin, Table, TextButton}
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
//  var time = tempo a ser recebido da controller
//  var timeLabel = new Label("Time" + time,  new Label.LabelStyle(new BitmapFont(), Color.WHITE))
 var totalPlayers:Int = 2 //numero total de players a serem
  val playerLabel = new Array[Label](totalPlayers)

  //  var winnerName = nome do vencedor recebido da controller
  //  var winnerRan = quantidade de pixeis percorridos recebido da controller
  //  var winnerEaten = quantidade de beans comidas recebida da controller
  //  var playerWinner = numero do player recebido da controller
  //  var playerWinnerLabel = new Label("Player " + playerWinner + ": " + winnerName + ". Beans eated: " + winnerEaten + ". Ran: " + winnerRan, new Label.LabelStyle(new BitmapFont(), Color.GOLD))


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
  def playerStatisticsShow (name: String, ran: Int, eaten: Int, player: Int, winner: Boolean): Unit = {
    val PlayerLabel = new Array[Label](2)

    if (winner) {
      PlayerLabel(player-1) = new Label( name + "\nPlayer " + player + "\nBeans eated: " + eaten + "\nRan: " + ran, new Label.LabelStyle(new BitmapFont(), Color.GOLD))
      this.playerLabel(player-1) =  PlayerLabel(player-1)
    }
    else {
      PlayerLabel(player-1) = new Label( name + "\nPlayer " + player + "\nBeans eated: " + eaten + "\nRan: " + ran, new Label.LabelStyle(new BitmapFont(), Color.GREEN))
      this.playerLabel(player-1) =  PlayerLabel(player-1)
    }
  }

  playerStatisticsShow("Jose", 20, 4, 1, true)
  playerStatisticsShow("Pedro", 30, 5, 2, false)


  //Variaveis para os botoes
  private var atlas: TextureAtlas =_
  private var skin: Skin =_
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

  PlayButton = new TextButton("PLAY AGAIN", textButtonStyle)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.playAgain()
  })

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
//  table.add(ButtonExit).bottom().right()
//  stage.addActor(table)

  //SEM USAR TABLE
  gameOverLabel.setPosition(width/2 - gameOverLabel.getWidth/2, height - gameOverLabel.getHeight)
  stage.addActor(gameOverLabel)

  while(i<totalPlayers){
    playerLabel(i).setPosition(width/6 + i*width/totalPlayers, height/2 )
    stage.addActor(playerLabel(i))
    i+= 1
  }

  PlayButton.setPosition(PlayButton.getWidth/4, height/10)
  stage.addActor(PlayButton)
  ButtonExit.setPosition(width - ButtonExit.getWidth*5/4, height/10)
  stage.addActor(ButtonExit)
}
