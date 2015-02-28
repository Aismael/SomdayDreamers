/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gamplayCore;

import com.jme3.bullet.control.BetterCharacterControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;

/**
 *
 * @author Aismael
 */
public class GameCharacterControl extends BetterCharacterControl implements ActionListener, AnalogListener{

    boolean forward,backward,leftRotate,rightRotate,leftStrafe,rightStrafe;
    private float moveSpeed,sensitivity,yaw;
    Node head;
    

    public GameCharacterControl(float moveSpeed,float sensitivity,float radius,float height,float mass){
        super(radius, height, mass);
        this.moveSpeed=moveSpeed;
        this.sensitivity=sensitivity;
        head=new Node("Head");
        head.setLocalTranslation(0,1.8f,0);
        
    }
    
    
    @Override
    public void setDucked(boolean duck){
        if(duck){
                    head.setLocalTranslation(0,1.2f,0);
                    this.setHeightPercent(50);

        }else{
           head.setLocalTranslation(0,1.8f,0);
           this.setHeightPercent(200);
 
        }
    }
    
    @Override
    public void onAction(String action, boolean isPressed, float tpf) {
       switch(action){
           case "StrafeLeft":leftStrafe=isPressed;
               break;
           case "StrafeRight":rightStrafe=isPressed;
               break;
           case "MoveForward":forward=isPressed;
               break;
           case "MoveBackward":backward=isPressed;
               break;
           case "Jump":jump();
               break;
           case "Duck":setDucked(isPressed);
               break;
            default:
                break;
                
       }
            
    }
    
    @Override
    public void onAnalog(String action, float value, float tpf) {
        switch(action){
            case "RotateLeft":rotate(tpf*sensitivity);
                break;
            case "RotateRight":rotate(-tpf*sensitivity);
                break;
            case "LookUp":lookUpDown(tpf*sensitivity);
                break;
            case "LookDown":lookUpDown(-tpf*sensitivity);
                break;
            default:
                break;
        }
    }
    
    private void rotate(float value){
        Quaternion rotate= new Quaternion().fromAngleAxis(FastMath.PI*value, Vector3f.UNIT_Y);
        rotate.multLocal(viewDirection);
        setViewDirection(viewDirection);
        

    }
    private void lookUpDown(float value){
        yaw+=value;
        yaw= FastMath.clamp(yaw, -FastMath.HALF_PI, FastMath.HALF_PI);
        head.setLocalRotation(new Quaternion().fromAngles(yaw,0,0));
    }
    public void setCamera(Camera cam){
        CameraNode camNode= new CameraNode("CamNode", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        head.attachChild(camNode);
        //camNode.setLocalTranslation(0, 5, -5);
        //camNode.lookAt(head.getLocalTranslation(), Vector3f.UNIT_Y);
    }
    
    @Override
    public void setSpatial(Spatial spatial){
       super.setSpatial(spatial);
       if(spatial instanceof Node){
           ((Node) spatial).attachChild(head);
       }
    }
    @Override
    public void update(float tpf){
        //System.err.print(tpf+"\n");
        super.update(tpf);
        Vector3f modelForwardDir=spatial.getWorldRotation().mult(Vector3f.UNIT_Z);
        Vector3f modelLeftDir=spatial.getWorldRotation().mult(Vector3f.UNIT_X);
        walkDirection.set(0, 0, 0);
        if(forward){
            walkDirection.addLocal(modelForwardDir.mult(moveSpeed));
        }else if(backward){
            walkDirection.addLocal(modelForwardDir.negate().mult(moveSpeed));
        }
        if(leftStrafe){
            walkDirection.addLocal(modelLeftDir.mult(moveSpeed));
        }else if(rightStrafe){
            walkDirection.addLocal(modelLeftDir.negate().mult(moveSpeed));
        }
       setWalkDirection(walkDirection);
    }

    

     
    
    
}
