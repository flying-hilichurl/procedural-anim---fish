package hilichurl.proceduralanim.biology;

import hilichurl.proceduralanim.data.Bone;

import javafx.scene.paint.Color;

public class Fish extends Biology{
    public Fish(){
        super();

        color=new Color(0.4,0.4,0.8,1);
        bone=new Bone();
        bone.addNode(5,0);
        bone.addNode(5,4);
        bone.addNode(5,7);
        bone.addNode(5,9);
        bone.addNode(5,10);
        bone.addNode(5,10);
        bone.addNode(5,9.5f);
        bone.addNode(5,9f);
        bone.addNode(5,8.5f);
        bone.addNode(5,8);
        bone.addNode(5,7);
        bone.addNode(5,6);
        bone.addNode(5,4);
        bone.addNode(5,2);
        bone.addNode(5,0);
    }
}
