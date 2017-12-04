package Source.GameEngine

import scala.collection.mutable.ListBuffer

trait GameOverRules {

  /**
    * Verifica se o jogo acabou
    *
    * @param player jogador na thread atual
    * @param key tecla recebida
    * @param GAME_ENGINE Game Engine atual
    * @param PLAYERS lista de jogadores
    * @param KILLER_THINGS lista de objetos mortais no mapa
    *
    */
  def isEndGame(player: Sprite, key:Int, GAME_ENGINE: GameEngine, PLAYERS: ListBuffer[Sprite],
                KILLER_THINGS:ListBuffer[Sprite]):Boolean

}
