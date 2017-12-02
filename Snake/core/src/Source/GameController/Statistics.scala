package Source.GameController

trait Statistics {

  private var eatenBeans = 0
  private var pixelRan = 0
  private var tStart = System.currentTimeMillis()

  def getEatenBeans = eatenBeans

  def recordEaten = eatenBeans += 1

  def getPixelRan = pixelRan

  def recordRun = pixelRan += 1

  def getTime:String = {
    val time = System.currentTimeMillis() - tStart
    val time_h_m_s = (time/1000/60/60).toInt.toString +':'+ (time/1000/60).toInt.toString +':'+ (time/1000).toInt.toString
    time_h_m_s
  }

}
