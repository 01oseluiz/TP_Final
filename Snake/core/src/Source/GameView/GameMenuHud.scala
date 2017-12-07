package Source.GameView

import Source.GameController.Controller
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import com.badlogic.gdx.scenes.scene2d.ui._
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

class GameMenuHud(var width: Int, var height: Int) {

  //Variaveis da HUD
  var stage = new Stage
  private val atlas = new TextureAtlas("ui/atlas.pack")
  var skin = new Skin(Gdx.files.internal("ui/skin.json"), atlas)
  private var BackButton, AplyButton, DefaultButton: TextButton =_
  private var PlayButton, ExitButton, ModsButton, PlayersButton: Button =_
  var ModsMenu = new Window("MODS", skin, "mods")
  var descriptionWindow = new Window("", skin, "description")
  var modInfoLabel = new Label("", skin, "white15Label")
  var lista = new List[String](skin)
  val mods, engines, descriptions, versions, authors, dates = new com.badlogic.gdx.utils.Array[String]


  def ListaMods(listagem : Array[(String,String,String,String,String,String)]): Unit = {

    listagem.foreach{mod=>
//      mods.add("VERSION: " + mod._1 + "    |   AUTHOR: " + mod._3 + "    |   DATE: " + mod._4 +
//        "    |   TITLE: " + mod._5 + "    |   DESCRIPTION: " + mod._6)
      mods.add("TITLE: " + mod._5 + "    |   DESCRIPTION: " + mod._6)

      dates.add(mod._4)
      authors.add(mod._3)
      versions.add(mod._1)
      engines.add(mod._2)
      descriptions.add(mod._6)
    }
    lista.setItems(mods)
  }


  //ScrollPane e SelectBox
  var scrollPane = new ScrollPane(lista, skin)
  //  scrollPane.setScrollingDisabled(true, false)

  var selectBox = new SelectBox[String](skin)
  selectBox.setItems("            1 player","            2 players", "            3 players", "            4 players")
//  selectBox.setItems("1 player", "2 players", "3 players", "4 players")


  //Criacao dos botoes
  ExitButton = new Button(skin, "exit")
  ExitButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = Gdx.app.exit()
  })

  ModsButton = new Button(skin, "mods")
  ModsButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      ModsMenu.setVisible(true)
      BackButton.setVisible(true)
      AplyButton.setVisible(true)
      DefaultButton.setVisible(true)
      scrollPane.setVisible(true)
      descriptionWindow.setVisible(true)
      modInfoLabel.setVisible(true)
    }
  })

  PlayersButton = new Button(skin, "players")
  PlayersButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      selectBox.showList()
    }
  })


  BackButton = new TextButton("BACK", skin)
  BackButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      ModsMenu.setVisible(false)
      BackButton.setVisible(false)
      AplyButton.setVisible(false)
      DefaultButton.setVisible(false)
      scrollPane.setVisible(false)
      descriptionWindow.setVisible(false)
      modInfoLabel.setVisible(false)
    }
  })


  AplyButton = new TextButton("APLY", skin)
  AplyButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      println(engines.get(lista.getSelectedIndex).toString)
      Controller.setMod(engines.get(lista.getSelectedIndex).toString)
    }
  })


  DefaultButton = new TextButton("Default", skin)
  DefaultButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      Controller.setMod("Default")
    }
  })


  PlayButton = new Button(skin, "play")
  PlayButton.addListener(new ClickListener(){
    override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
      Controller.PLAYER_NUMBER = selectBox.getSelectedIndex+1
      mods.clear()
      Controller.startGame()
    }
  })


  //Posicionando a HUD e os botoes

  scrollPane.setVisible(false)
  BackButton.setVisible(false)
  AplyButton.setVisible(false)
  DefaultButton.setVisible(false)
  ModsMenu.setVisible(false)
  descriptionWindow.setVisible(false)
  modInfoLabel.setVisible(false)


//  ModsMenu.pack()                          //Compacta a janela ao padding escolhido em relacao ao texto
//  ModsMenu.setKeepWithinStage(false)         //Impede limitacao de posicao da janela ao stage durante drag
  ModsMenu.setMovable(false)                 //Impede movimentacao da janela por meio de drag
  ModsMenu.setWidth(width/1.28f /*500*/)
  ModsMenu.setHeight(height/1.46f /*330*/)
  ModsMenu.setPosition(width/2 - ModsMenu.getWidth/2, height/2 - ModsMenu.getHeight/2)
  ModsMenu.padTop(height/8 /*60*/)
  ModsMenu.getTitleLabel.setAlignment(0)    //Alinha o titulo no meio


  scrollPane.setPosition(ModsMenu.getX + width/12.8f, ModsMenu.getY + height/18.29f)
  scrollPane.setSize(width/1.6f/*400*/, height/2.04f/*235*/)


  descriptionWindow.setSize(scrollPane.getX, ModsMenu.getHeight)
  modInfoLabel.setSize(descriptionWindow.getWidth,descriptionWindow.getHeight)
  descriptionWindow.setPosition(width - scrollPane.getX, ModsMenu.getY)
  modInfoLabel.setPosition(descriptionWindow.getX, /*(descriptionWindow.getY + descriptionWindow.getHeight/2)*9/10*/ descriptionWindow.getY)


  BackButton.setPosition(width/2 - 2*BackButton.getWidth, ModsMenu.getY + height/48)
  AplyButton.setPosition(width/2 - AplyButton.getWidth/3, ModsMenu.getY + height/48)
  DefaultButton.setPosition(width/2 + DefaultButton.getWidth, ModsMenu.getY + height/48)


  ExitButton.setPosition(width*0.58f /*372*/, height/4.44f /*108*/)
  ExitButton.setSize(width/16 /*40*/, height/16 /*30*/)
  ModsButton.setPosition(width/7.81f /*82*/, height/1.87f /*256*/)
  ModsButton.setSize(width/3.77f /*170*/, height/10.91f /*44*/)
  PlayButton.setPosition(width/2.28f /*280*/, height/1.92f /*250*/)
  PlayButton.setSize(width/7.44f /*86*/, height/8.54f /*56*/)
  PlayersButton.setPosition(width/1.61f /*397*/, height/1.84f /*260*/)
  PlayersButton.setSize(width/3.77f /*170*/,height/10.91f /*44*/)



  selectBox.setPosition(PlayersButton.getX + PlayersButton.getWidth/20, PlayersButton.getY + 4)
  //TODO - arrumar largura ao reescalar
  selectBox.setSize(PlayersButton.getWidth*9/10, PlayersButton.getHeight)
  //  selectBox.set
  //  selectBox.getList.setPosition(selectBox.getWidth/2, selectBox.getHeight/2)
  //  selectBox.getScrollPane.setPosition(selectBox.getWidth/2, selectBox.getHeight/2)
  //  selectBox.getList.setOrigin(50, 50)


  stage.addActor(PlayButton)
  stage.addActor(ExitButton)
  stage.addActor(ModsButton)
  stage.addActor(selectBox)
  stage.addActor(PlayersButton)
  stage.addActor(ModsMenu)
  stage.addActor(scrollPane)
  stage.addActor(BackButton)
  stage.addActor(AplyButton)
  stage.addActor(DefaultButton)
  stage.addActor(descriptionWindow)
  stage.addActor(modInfoLabel)
}
