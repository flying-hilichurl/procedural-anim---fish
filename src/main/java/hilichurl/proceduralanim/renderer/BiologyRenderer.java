package hilichurl.proceduralanim.renderer;

import hilichurl.proceduralanim.biology.Biology;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class BiologyRenderer {
    Path path=new Path();

    public void renderContour(Biology biology,Pane root)
    {
        ArrayList<ArrayList<Point2D>> lines;

        //清空上一帧的绘制。
        root.getChildren().clear();
        root.getChildren().add(path);
        path.getElements().clear();
        lines=biology.getRenderLines();

        //依次绘制线段
        for(ArrayList<Point2D> line:lines){
            renderLine(line);
        }

        path.setFill(biology.color);
    }

    private void renderLine(ArrayList<Point2D> line){
        if(line.size()<2)
            return ;
        else if(line.size()==2){
            path.getElements().add(new MoveTo(line.getFirst().getX(),line.getFirst().getY()));
            path.getElements().add(new LineTo(line.getLast().getX(),line.getLast().getY()));
        }//当节点数为单数时，全部使用二阶贝塞尔曲线连接。
        else if(line.size()%2==1){
            path.getElements().add(new MoveTo(line.getFirst().getX(),line.getFirst().getY()));
            int i=1,j=line.size();
            while(i<j) {
                path.getElements().add(new QuadCurveTo(line.get(i).getX(), line.get(i).getY(), line.get(i+1).getX(), line.get(i+1).getY()));
                i+=2;
            }
        }//当节点数为双数时，先进行一个三阶贝塞尔曲线，然后全部使用二阶贝塞尔曲线连接。
        else{
            path.getElements().add(new MoveTo(line.getFirst().getX(),line.getFirst().getY()));
            path.getElements().add(new CubicCurveTo(line.get(1).getX(),line.get(1).getY(),line.get(2).getX(),line.get(2).getY(),line.get(3).getX(),line.get(3).getY()));
            int i=4,j=line.size();
            while(i<j){
                path.getElements().add(new QuadCurveTo(line.get(i).getX(), line.get(i).getY(), line.get(i+1).getX(), line.get(i+1).getY()));
                i+=2;
            }
        }
    }
}
