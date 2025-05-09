package hilichurl.proceduralanim.biology;

import hilichurl.proceduralanim.data.Bone;

import hilichurl.proceduralanim.renderer.BiologyRenderer;
import javafx.geometry.Point2D;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class Biology {
    public Bone bone=new Bone();
    public Color color;
    private final BiologyRenderer renderer;
    private Point2D target;     //将要移动到的位置

    public void setTarget(Point2D target){this.target=target;System.out.println(target);}

    public Biology(){
        renderer=new BiologyRenderer(this);
    }

    public ArrayList<ArrayList<Point2D>> getRenderLines(){
        //获取主体轮廓
        ArrayList<ArrayList<Point2D>> lines=new ArrayList<>(bone.getOutlines());

        //获取装饰线

        return lines;
    }
}
