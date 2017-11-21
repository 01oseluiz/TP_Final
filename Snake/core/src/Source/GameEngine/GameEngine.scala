package Source.GameEngine

trait GameEngine extends BeanMoveRules with DeathRules with DynamicMoveRules with BonusMoveRules {
  /**
    * função para se finalizar algo da engine ou suas patern classes, se necessário
    */
  def FinishGame(): Unit ={
  }
}
