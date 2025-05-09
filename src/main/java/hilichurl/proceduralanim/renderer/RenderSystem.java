package hilichurl.proceduralanim.renderer;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class RenderSystem  {
    protected static ArrayList<BiologyRenderer> allRenders=new ArrayList<>();

    public void Render(Pane root){
        //清空上一帧的绘制
        root.getChildren().clear();

        for(BiologyRenderer renderer:allRenders){
            renderer.renderContour(root);
        }
    }
}
