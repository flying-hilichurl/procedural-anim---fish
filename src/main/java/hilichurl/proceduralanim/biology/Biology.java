package hilichurl.proceduralanim.biology;

import hilichurl.proceduralanim.data.Bone;

import javafx.geometry.Point2D;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public abstract class Biology {
    public Bone bone=new Bone();
    public Color color;

    public ArrayList<ArrayList<Point2D>> getRenderLines(){
        //获取主体轮廓
        ArrayList<ArrayList<Point2D>> lines=new ArrayList<>(bone.getOutlines());

        //获取装饰线

        return lines;
    }
}
