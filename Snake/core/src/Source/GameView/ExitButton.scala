package Source.GameView

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.{BitmapFont, TextureAtlas}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.{Skin, Table, TextButton}
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle


class ExitButton (var width: Int, var height: Int){

  //Variaveis para o botao
  private var atlas: TextureAtlas =_
  private var skin: Skin =_
  private var textButtonStyle: TextButtonStyle =_
  private var ButtonExit: TextButton =_

  //Variaveis para a UI
  private var table: Table =_
  var stage: Stage =_

  //Criando o botao
  atlas = new TextureAtlas("ui/button2.pack")
  skin = new Skin(atlas)
  table = new Table(skin)
  stage = new Stage()
  textButtonStyle = new TextButtonStyle()

  //Imagens a serem desenhadas quando o botao eh ou nao apertado
  textButtonStyle.up = skin.getDrawable("Button2.up")
  textButtonStyle.down = skin.getDrawable("Button2.down")

  //Posicao do texto no botao ao ser pressionado
  textButtonStyle.pressedOffsetX = 1
  textButtonStyle.pressedOffsetY = -1

  //Fonte do texto no botao
  textButtonStyle.font = new BitmapFont()
  textButtonStyle.fontColor = Color.BLACK

  //Criacao do botao em si
  ButtonExit = new TextButton("EXIT", textButtonStyle)

  //OU USANDO TABLE
//  ButtonExit.pad(20)  //Distancia da borda do botao ao texto
//  table.bottom()      //Escolhe as proximas linhas como parte inferior
//  table.setFillParent(true)
//  table.add(ButtonExit).expandX().padRight(10) //Define que ButtonExit ocupada a linha toda e deveria ficar a 10 pixeis do canto direito
//  stage.addActor(table)

  //OU SEM USAR TABLE
  ButtonExit.setSize(100,50)  //Desnecessario, pois ja eh o tamanho das imagens
  ButtonExit.setPosition(width*8/10, height/10)
  stage.addActor(ButtonExit)
}
