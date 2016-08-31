package jp.ac.dendai.c.jtp.testphysics3d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import jp.ac.dendai.c.jtp.testphysics3d.Math.Vector3;
import jp.ac.dendai.c.jtp.testphysics3d.Physics.Physics.*;

public class MainActivity extends AppCompatActivity {
    Button button;
    boolean on = false;
    Physics3D physics;
    List<PhysicsObject> objects;
    Thread thread;
    PhysicsThread physicsThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PhysicsInfo info = new PhysicsInfo();
        info.maxObject = 4;
        info.enabled = true;
        info.gravity = new Vector3(0,0,9.8f);

        physics = new Physics3D(info);
        physics.setName("testWorld");
        objects = new LinkedList<PhysicsObject>();

        PhysicsObject object = null;
        for(int n = 0; n < info.maxObject;n++) {
            object = new PhysicsObject();
            object.setName(String.valueOf(n));
            object.mass = 1;
            object.pos = new Vector3();
            object.rot = new Vector3();
            object.scl = new Vector3();
            object.velocity = new Vector3();
            object.useGravity = true;
            object.freeze = false;
            objects.add(object);
            physics.addObject(object);
        }
        physicsThread = new PhysicsThread(physics,object);
        thread = new Thread(physicsThread);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!on){
                    physicsThread.stop = false;
                    thread.start();
                    button.setText("STOP");
                    on = true;
                }else{
                    physicsThread.stop = true;
                    button.setText("START");
                    on = false;
                }
            }
        });
    }
    class PhysicsThread implements Runnable{
        public volatile boolean stop = false;
        private Physics3D world;
        private PhysicsObject target;
        private float time = 0;
        public PhysicsThread(Physics3D world,PhysicsObject target){
            this.world = world;
            this.target = target;
        }
        @Override
        public void run() {
            while(!stop){
                world.simulate();
                //if(time == 0 || (physics.getCurrentTime() - time) >= 0.01f){
                    Log.d("Physics", "CurrentTime"+physics.getCurrentTime()+" DeltaTimeSum"+physics.getDeltaTimeSum()+" DeltaTime:"+physics.getDeltaTime()+" Pos:" + target.pos.toString()+" vel:"+target.velocity.toString()+" b_vel:"+target.getBufferVelocity().toString());
                    //time = physics.getCurrentTime();
               // }
            }
        }
    }
}
