package hilichurl.proceduralanim;

import hilichurl.proceduralanim.biology.Fish;
import hilichurl.proceduralanim.playercontroller.PlayerInput;
import hilichurl.proceduralanim.stagecontroller.StartController;
import hilichurl.proceduralanim.enums.SceneEnums;
import hilichurl.proceduralanim.renderer.RenderSystem;
import hilichurl.proceduralanim.renderer.StageRenderer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Program extends Application {
    Scene startScene;
    Scene poolScene;
    Stage mainStage;
    StageRenderer stageRenderer=new StageRenderer();
    HashMap<SceneEnums,Scene> sceneMap=new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        //读取开始界面fxml文件和css文件
        FXMLLoader fxmlLoader = new FXMLLoader(Program.class.getResource("/hilichurl/proceduralanim/start-interface.fxml"));
        startScene = new Scene(fxmlLoader.load(), 320, 240);
        startScene.getStylesheets().add(Objects.requireNonNull(Program.class.getResource("/hilichurl/proceduralanim/style/start-interface.css")).toExternalForm());
        sceneMap.put(SceneEnums.startInterface, startScene);
        StartController startController=fxmlLoader.getController();
        startController.setClickHandler(new Handler());
        stage.setOnCloseRequest(e ->Platform.exit());
        mainStage=stage;
        stageRenderer.configStage(mainStage);

        stage.setScene(startScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private class Handler implements Runnable{
        @Override
        public void run() {
            FXMLLoader poolLoader=new FXMLLoader(Program.class.getResource("/hilichurl/proceduralanim/pool.fxml"));
            //第一次进入pool场景时，加载场景的布局
            if(poolScene==null){
                try {
                    poolScene=new Scene(poolLoader.load(),1000,600);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                sceneMap.put(SceneEnums.pool,poolScene );
            }
            switchScene(mainStage,SceneEnums.pool);

            Fish fish =new Fish();
            //绑定按键映射
            PlayerInput playerInput=new PlayerInput();
            playerInput.enableGameInput(fish,poolScene);

            //启动渲染循环
            RenderSystem renderSystem=new RenderSystem();
            Timeline timeline=new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(20),event->{
                renderSystem.Render(poolLoader.getRoot());
                mainStage.show();
            });
            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
        }
    }

    private void switchScene(Stage stage, SceneEnums type){
        stage.setScene(sceneMap.get(type));
    }
}