package hilichurl.proceduralanim.renderer;

import javafx.stage.Screen;
import javafx.stage.Stage;

public class StageRenderer {
    public void configStage(Stage stage){
        //设置窗口样式
        stage.setTitle("程序性动画-鱼");
        stage.setWidth(1000);
        stage.setHeight(600);
        stage.setResizable(false);

        //获取屏幕尺寸，使屏幕居中
        double screenWidth= Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight= Screen.getPrimary().getVisualBounds().getHeight();
        stage.setX((screenWidth-stage.getWidth())/2);
        stage.setY((screenHeight-stage.getHeight())/2);
    }
}
