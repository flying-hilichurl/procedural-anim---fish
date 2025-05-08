package hilichurl.proceduralanim.data;


import javafx.geometry.Point2D;

public class Node {
    private Point2D position;      //节点所在的位置
    private final float width;        //该节点的渲染宽度
    private Point2D direction;     //在该节点的切线方向
    //应为Java不支持用部分类拓展原生类，获取垂直向量的方法写在其他方法中不合适，于是写在Node中随direction自动更新
    private Point2D left;       //节点的垂直左方向
    private Point2D right;      //节点的垂直右方向
    private final float distance;   //与其他节点之间的稳定距离

    public Node last;
    public Node next;

    public Node(float distance,float width,Node last){
        this.distance=distance;
        this.width=width;

        //貌似存在优化空间？
        if(last!=null){
            this.last=last;
            last.next=this;
            setDirection(last.direction);
            position=last.position.add(last.direction.multiply(-last.distance));
        }
        else {
            setDirection(new Point2D(0,-1));
            position=new Point2D(500,400);
        }
    }

    public Point2D getPosition(){return position;}

    public float getWidth(){return width;}

    public Point2D getDirection(){return direction;}

    Point2D getLeft(){return left;}

    Point2D getRight(){return right;}

    private void setDirection(Point2D direction){
        if(direction.magnitude()!=1)
            direction=direction.normalize();

        this.direction=direction;
        getSides(direction);
    }

    private void getSides(Point2D direction){
        double k;   //与向量垂直的直线的斜率

        //单独处理斜率不存在的情况
        if(direction.getX()==0)
            k=0;
        else
            k=-1/(direction.getY()/ direction.getX());

        //得到两个垂直向量
        Point2D point1=new Point2D(1,k);
        point1.normalize();
        Point2D point2 = new Point2D(-1,-k);
        point1.normalize();

        //如果该向量和方向叉乘大于0，说明该向量在方向的左边。
        if(direction.getX()*point1.getX()>0){
            left=point1;
            right=point2;
        }
        else{
            right=point1;
            left=point2;
        }
    }
}
