package Source.GameController

import java.io.{BufferedReader, File, FileReader}

class searchMods {
  private final val current_directory = new File(".")
  private final val mod_directory =new File (current_directory.getCanonicalPath + File.separator + "mods")

  object Errors{
    final val NoExistModsFolder = "No Exist Mods Folder"
    final val NoExistMod = "No Exist Mod"
    final val NoExistEngineFile = "No Exist Engine File"
    final val NoExistConfigFile = "No exist the file 'info.config'"
    final val IncompatibleVersion = "The mod version isn't compatible with this game version"
    final val ErrorInfoConfig = "Something is wrong on file 'info.config'"
  }

  @throws(classOf[java.lang.Exception])
  def getModsInfo: Array[(String, String, String, String, String, String)] ={

    var listPackages:Array[String] = Array.empty
    var listModsInfo:Array[(String,String,String,String,String,String)] = Array.empty
    //Array[(Versão mod, Main Engine, Autor, Data, Titulo, Descricao)]

    //Busca pelo diretorio mods
    if(mod_directory.isDirectory){
      //Busca pelos mods dentro do diretorio mods
      listPackages = mod_directory.list()

      if(listPackages == null){
        throw new Exception(Errors.NoExistMod)
      }
    } else {
      throw new Exception(Errors.NoExistModsFolder)
    }

    //Busca pelos config dentro de cada mod
    listPackages.foreach{mod=>
      val configFile =
        try{
          new FileReader(mod_directory.getName + File.separator + mod + File.separator + "info.config")
        }catch{
          case e:Throwable => throw new Exception(e.getMessage + "\n" + Errors.NoExistConfigFile)
        }

      val bufferRead:BufferedReader = new BufferedReader(configFile)

      try {
        listModsInfo +:= readConfig(bufferRead, mod)
      }catch{
        case x:Exception => listModsInfo +:= ("","","","","ERROR",x.getMessage + " in mod->" + mod )
      }

      configFile.close()
      bufferRead.close()
    }

    listModsInfo
  }

  @throws(classOf[java.lang.Exception])
  private def readConfig(readConfig:BufferedReader, mod:String): (String, String, String, String, String, String) ={
    //Lê o arquivo config
    val buff:Array[AnyRef] = readConfig.lines().toArray()

    if(buff.length < 6) throw new Exception(Errors.ErrorInfoConfig)

    val version = separateInfo(buff(0).toString)
    val mainEngine = mod + '.' + separateInfo(buff(1).toString)
    val author = separateInfo(buff(2).toString)
    val date = separateInfo(buff(3).toString)
    val title = separateInfo(buff(4).toString)
    var description = separateInfo(buff(5).toString)

    //Se tiver quebra de linha na descrição sera adicionado aqui
    if(buff.length > 6){
      for(i<- 5 until buff.length) description = description + "\n" + buff(i).toString
    }

    //criação da tupla
    val infos = Tuple6(version, mainEngine, author, date, title, description)

    //Verificação de versao e existencia da engine
    if (version != Controller.gameVersion) throw new Exception(Errors.IncompatibleVersion)
    else if(mainEngine.length < 2) throw new Exception(Errors.NoExistEngineFile)

    infos
  }

  //separa o titulo da linha das informacoes
  private def separateInfo(string: String): String = string.drop(string.lastIndexOf(": ")+2)
}
