package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameOverHud (var width: Int, var height: Int){

  //Variaveis da HUD
  var stage = new Stage
  private val atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"), atlas)
  private val gameOverLabel = new Label("GAME OVER", skin, "redLabel")
  private val playAgainLabel = new Label("Play Again", skin, "white32Label")
  private val menuLabel = new Label("Menu", skin, "white32Label")
  private val timeLabel = new Label("Game Time", skin, "white15Label")
  var time : String =_  //tempo a ser recebido da controller
  private var timerLabel : Label =_
  private var MenuButton, PlayAgainButton: Button =_
  var player1Highlight, player2Highlight, player3Highlight, player4Highlight: Sprite =_


  /**
    * Mostra cada player e seu desempenho na gameOverHud
    * @param name nome a ser recebido da controller
    * @param ran  quantidade de pixeis percorridos recebida da controller
    * @param eaten  quantidade de beans comidas recebida da controller
    * @param player numero do player recebido da controller
    * @param winner venceu ou nao
    */
  def playerStatisticsShow (name: String, ran: Int, eaten: Int, player: Int, winner: Boolean, playerTime: String): Unit = {
    var playerLabel:Label = null
    val totalPlayers = Controller.PLAYER_NUMBER //numero total de players a serem exibidos
    var playerHighlight: Sprite = null
    this.time = playerTime


    if (winner) {
      playerLabel = new Label( name + "\nPlayer " + player + "\nBeans eaten: " + eaten + "\nRan: " + ran, skin, "arial15", "gold")
      playerHighlight = new Sprite(new Texture("ui/Square_winner.9.png"))
    }
    else {
      playerLabel = new Label( name + "\nPlayer " + player + "\nBeans eaten: " + eaten + "\nRan: " + ran, skin,"arial15","green")
      playerHighlight = new Sprite(new Texture("ui/Square_loser.9.png"))
    }

    if(totalPlayers == 1){
      playerLabel.setPosition(width*3/7, height/2)
      playerHighlight.setSize(width/3.2f /*200*/, height/1.71f /*280*/)
    }else if(totalPlayers == 2){
      playerLabel.setPosition(width/6 + (player-1)*width/totalPlayers, height/2)
      playerHighlight.setSize(width/3.2f /*200*/, height/1.71f /*280*/)
    } else if(totalPlayers == 3) {
      playerLabel.setPosition(width/10 + (player-1)*width/totalPlayers, height/2)
      playerHighlight.setSize(width/3.2f /*200*/, height/1.71f /*280*/)
    } else if(totalPlayers == 4){
      playerLabel.setPosition(width/14 + (player-1)*width/totalPlayers, height/2)
      playerHighlight.setSize(width/3.88f /*165*/, height/1.92f /*250*/)
    }

    playerHighlight.setPosition(playerLabel.getX - playerHighlight.getWidth/3 + 10, playerLabel.getY - playerHighlight.getHeight/2)

    if (player == 1) {
      player1Highlight = playerHighlight
    }
    else if (player == 2) {
      player2Highlight = playerHighlight
    }
    else if (player == 3) {
      player3Highlight = playerHighlight
    }
    else if (player == 4) {
      player4Highlight = playerHighlight
    }

    stage.addActor(playerLabel)
  }


  //Criacao dos botoes em si
  MenuButton = new Button(skin, "menu")
  MenuButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.backToMenu()
  })

  PlayAgainButton = new Button(skin, "playAgain")
  PlayAgainButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.playAgain()
  })

  /**
    * funcao que arruma reseta e arruma a gameOverHUD
    */
  def setGameOverScreenHUD(): Unit ={
    this.stage.clear()

    Controller.getStatistics()
    timerLabel = new Label(time.toString, skin, "yellow15Label")

    //Posicionando a HUD e os botoes
    gameOverLabel.setPosition(width/2 - gameOverLabel.getWidth/2, height - gameOverLabel.getHeight)
    timerLabel.setPosition(width/2 - timerLabel.getWidth/2, gameOverLabel.getY - height/32)
    timeLabel.setPosition(width/2 - timeLabel.getWidth/2, gameOverLabel.getY - height/16)
    PlayAgainButton.setSize(width/3.2f /*200*/, height/12/*40*/)
    PlayAgainButton.setPosition(PlayAgainButton.getWidth/4, height/10)
    playAgainLabel.setPosition(PlayAgainButton.getX + width/10.67f/*60*/, PlayAgainButton.getY)
    MenuButton.setSize(width/4.92f /*130*/,height/9.6f /*50*/)
    MenuButton.setPosition(width - MenuButton.getWidth*7/5, height/10)
    //    MenuButton.setPosition(PlayAgainButton.getX-68, height/10)
    //TODO - arrumar a posicao X ao reescalar
    menuLabel.setPosition(MenuButton.getX - width/42.67f /*15*/, MenuButton.getY)

    stage.addActor(gameOverLabel)
    stage.addActor(timerLabel)
    stage.addActor(timeLabel)
    stage.addActor(playAgainLabel)
    stage.addActor(PlayAgainButton)
    stage.addActor(menuLabel)
    stage.addActor(MenuButton)
  }
}