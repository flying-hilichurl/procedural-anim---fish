package hilichurl.proceduralanim.data;

import javafx.geometry.Point2D;
import java.util.ArrayList;

public class Bone {
    static int nodeNum=0;
    public Node head;
    public Node tail;

    //根据参数直接在尾节点后创建一个新节点
    public void addNode(float distance,float width){
        if(tail==null){
            tail=new Node(distance,width,null);
            head=tail;
        }
        else{
            tail.next=new Node(distance,width,tail);
            tail=tail.next;
        }
        nodeNum++;
    }

    public int getNodeNum(){return nodeNum;}

    //获取左右两条线的控制点
    public ArrayList<ArrayList<Point2D>> getOutlines(){
        Node move=head;
        ArrayList<ArrayList<Point2D>> lines = new ArrayList<>();
        ArrayList<Point2D> line1=new ArrayList<>();
        ArrayList<Point2D> line2=new ArrayList<>();

        while(move!=null){
            Point2D point =move.getPosition().add(move.getLeft().multiply(move.getWidth()));
            line1.add(point);
            move=move.next;
        }
        lines.add(line1);
        move=head;
        while(move!=null){
            Point2D point = move.getPosition().add(move.getRight().multiply(move.getWidth()));
            line2.add(point);
            move=move.next;
        }
        lines.add(line2);

        return lines;
    }
}
