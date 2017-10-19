package Source.GameEngine

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

/**
  * Classe responsavel pelo movimento dos jogadores
  * @param player jogador a ser movido
  * @param number identificador de quais teclar devem move-lo
  */
class Movement (player: Player, number: Int) {
  private var speed: Int = 10

   if(number == 1) {
     if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
       player.posY += speed
     }
     else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
       player.posY -= speed
     } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
       player.posX -= speed
     } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
       player.posX += speed
     }
   }
   else if(number == 2) {
    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      player.posY += speed
    }
    else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      player.posY -= speed
    } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      player.posX -= speed
    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      player.posX += speed
    }
  }
}
