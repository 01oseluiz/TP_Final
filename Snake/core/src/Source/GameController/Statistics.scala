package Source.GameController

trait Statistics {

  private var eatenBeans = 0
  private var pixelRan = 0
  private var tStart = System.nanoTime()

  def getEatenBeans = eatenBeans

  def recordEaten = eatenBeans += 1

  def getPixelRan = pixelRan

  def recordRun = pixelRan += 1

  def getTime = System.nanoTime() - tStart

}
