package hilichurl.proceduralanim.playercontroller;

import hilichurl.proceduralanim.biology.Biology;
import javafx.scene.Scene;
import javafx.geometry.Point2D;

public class PlayerInput {
    public void enableGameInput(Biology player, Scene scene){
        scene.setOnMouseMoved(event-> player.setTarget(new Point2D(event.getSceneX(),event.getSceneY())));
    }
}
