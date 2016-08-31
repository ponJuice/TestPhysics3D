package jp.ac.dendai.c.jtp.testphysics3d.Physics.Physics;

import jp.ac.dendai.c.jtp.testphysics3d.Math.Vector;
import jp.ac.dendai.c.jtp.testphysics3d.Math.Vector3;

/**
 * Created by Goto on 2016/08/31.
 */
public class PhysicsObject {
    public String name;
    public float mass;
    public Vector pos;
    public Vector rot;
    public Vector scl;
    public Vector velocity;
    Vector impulseVelocity;
    Vector bufferVelocity;
    Vector bufferPos;
    Vector bufferRot;
    Vector bufferScl;
    public boolean freeze;
    public boolean useGravity;
    Physics3D.PhysicsItem regist;

    public PhysicsObject(){
        impulseVelocity = new Vector3();
        bufferVelocity = new Vector3();
        bufferPos = new Vector3();
        bufferRot = new Vector3();
        bufferScl = new Vector3();
    }

    public Vector getBufferVelocity(){
        return bufferVelocity;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getRegistInfo(){
        return "object name:"+name+" regist"+regist+"\n regist info:"+regist.getInfo();
    }
}
