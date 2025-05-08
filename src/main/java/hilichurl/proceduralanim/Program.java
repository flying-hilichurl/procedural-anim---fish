package hilichurl.proceduralanim;

import hilichurl.proceduralanim.biology.Fish;
import hilichurl.proceduralanim.controller.StartController;
import hilichurl.proceduralanim.enums.SceneEnums;
import hilichurl.proceduralanim.renderer.BiologyRenderer;
import hilichurl.proceduralanim.renderer.StageRenderer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Program extends Application {
    Scene startScene;
    Scene poolScene;
    Stage mainStage;
    StageRenderer stageRenderer=new StageRenderer();
    BiologyRenderer bioRenderer=new BiologyRenderer();
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
            bioRenderer.renderContour(new Fish(),poolLoader.getRoot());
            mainStage.show();
        }
    }

    private void switchScene(Stage stage, SceneEnums type){
        stage.setScene(sceneMap.get(type));
    }
}