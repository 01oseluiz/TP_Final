package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameOverHud (var width: Int, var height: Int){

  //Variaveis da HUD
  var stage = new Stage
  private val atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"), atlas)
  var gameOverLabel = new Label("GAME OVER", skin, "redLabel")
  private var MenuButton, PlayButton: TextButton =_


  //Variaveis para mostras estatisticas
  var i: Int = 0
  var time : Double =_  //tempo a ser recebido da controller
  //TODO - controller deve mandar a quantidade de players escolhida no menu
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
      playerLabel = new Label( name + "\nPlayer " + player + "\nBeans eated: " + eaten + "\nRan: " + ran, skin, "arial", Color.GOLD)
    }
    else {
      playerLabel = new Label( name + "\nPlayer " + player + "\nBeans eated: " + eaten + "\nRan: " + ran, skin,"arial","green")
    }

    playerLabel.setPosition(width/6 + (player-1)*width/totalPlayers, height/2 )
    stage.addActor(playerLabel)
  }


  //Criacao dos botoes em si
  MenuButton = new TextButton("BACK TO MENU", skin, "a")
  MenuButton.setSize(126,50)
  MenuButton.getStyle.pressedOffsetX = 1
  MenuButton.getStyle.pressedOffsetY = -1
  MenuButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.backToMenu() /*Gdx.app.exit()*/
  })

  PlayButton = new TextButton("PLAY AGAIN", skin, "a")
  PlayButton.setSize(100,50)
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Controller.playAgain()
  })

  /**
    * funcao que arruma reseta e arruma a gameOverHUD
    */
  def setGameOverScreenHUD(): Unit ={
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
