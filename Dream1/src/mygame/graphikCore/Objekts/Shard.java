/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.util.TangentBinormalGenerator;
import java.util.HashMap;
import mygame.graphikCore.Objekts.ShardContent.Shardenum;

/**
 *
 * @author Aismael
 */
public class Shard extends Node {

    AssetManager assetManager;
    BulletAppState bulletAppState;
    HashMap<Shardenum, Shard> outer;
    Shardmesh shardbox;
    float size;
    private RigidBodyControl landscape;
    private RigidBodyControl landscape2;

    public Shard(AssetManager assetManager, BulletAppState bulletAppState, float size, float height) {
        this.setName("shardNumber#");
        outer = new HashMap<>(4);
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
        this.size = size;
        shardbox = new Shardmesh(size, height, size);
        Geometry shardboxg = new Geometry("Shardbox", shardbox);
        shardboxg.setLocalTranslation(new Vector3f(0, -1, 0));
        Material mat = new Material(this.assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);

        Material sphereMat = new Material(this.assetManager,
                "Common/MatDefs/Light/Lighting.j3md");
        sphereMat.setTexture("DiffuseMap",
                this.assetManager.loadTexture("UTextures/Pondx128.tga"));
        sphereMat.setTexture("NormalMap",
                this.assetManager.loadTexture("UTextures/Pond_normalx128.tga"));
        sphereMat.setBoolean("UseMaterialColors", true);
        sphereMat.setColor("Diffuse", ColorRGBA.White);
        sphereMat.setColor("Specular", ColorRGBA.White);
        sphereMat.setColor("Ambient", ColorRGBA.White);

        sphereMat.setFloat("Shininess", 128f);  // [0,128]
        Material nMat = new Material(this.assetManager,
                "Common/MatDefs/Misc/ShowNormals.j3md");
        shardboxg.setMaterial(sphereMat);
        //shardboxg.setMaterial(nMat);
        TangentBinormalGenerator.generate(shardboxg);
        shardboxg.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        this.attachChild(shardboxg);

    }

    public void initPhysic() {
        landscape = new RigidBodyControl(0.0f);
        

        this.addControl(landscape);
        landscape.setKinematic(false);
        bulletAppState.getPhysicsSpace().add(landscape);
        landscape.setGravity(new Vector3f(0, -9.81f, 0));
        this.getControl(RigidBodyControl.class).setPhysicsLocation(this.getLocalTranslation());
    }

    public void updatePhysic() {
        this.removeControl(landscape);
        bulletAppState.getPhysicsSpace().remove(landscape);
        landscape = new RigidBodyControl(0.0f);

        this.addControl(landscape);
        bulletAppState.getPhysicsSpace().add(landscape);
        this.getControl(RigidBodyControl.class).setPhysicsLocation(this.getLocalTranslation());

    }

    public void fizzle() {
        float fMax = this.getMiddle();

        setLeftUp(((float) (fMax + randomGenerator() * size/3)));
        setLeftDown((float) (fMax + randomGenerator() * size/3));
        setRightUp((float) (fMax + randomGenerator() * size/3));
        setRightDown((float) (fMax + randomGenerator() * size/3));

        reWork();
    }

    public void fizzlein() {
        float fMax = this.getMiddle();
        //float fMax = this.getMiddle();
        if (this.getWest() == null && this.getNorth() == null) {
            setLeftUp((float) (fMax+randomGenerator()* size/3));
        }
        if(this.getWest()==null&&this.getSouth()==null){
        setLeftDown((float) (fMax+randomGenerator()* size/3));
        }
        if(this.getEast()==null&&this.getNorth()==null){
        setRightUp((float) (fMax+randomGenerator()* size/3));
        }
        if(this.getEast()==null&&this.getSouth()==null){
        setRightDown((float) (fMax+randomGenerator()* size/3));
        }
        reWork();
    }

    private void reWork() {
        if (this.getLeftUp() < 0) {
            setLeftUp(0);
        }
        if (this.getLeftDown() < 0) {
            setLeftDown(0);
        }
        if (this.getRightUp() < 0) {
            setRightUp(0);
        }
        if (this.getRightDown() < 0) {
            setRightDown(0);
        }

    }

    private float getMax() {
        float max = this.getLeftUp();
        if (max < this.getLeftDown()) {
            max = this.getLeftDown();
        }
        if (max < this.getRightDown()) {
            max = this.getLeftDown();
        }
        if (max < this.getRightUp()) {
            max = this.getLeftDown();
        }
        return max;

    }

    private float getMiddle() {
        float middle;
        middle = (this.getRightDown() + this.getRightUp() + this.getLeftDown() + this.getLeftUp()) / 4;
        return middle;

    }

    private float randomGenerator() {
        float x = (float) Math.random();
        float y = (float) Math.random();
        if (x >= 0.5f) {
            return y;
        } else {
            return -y;
        }

    }

    public void setLeftUp(float height) {
        shardbox.setLeftUp(height);
    }

    public void setLeftDown(float height) {
        shardbox.setLeftDown(height);
    }

    public void setRightUp(float height) {
        shardbox.setRightUp(height);
    }

    public void setRightDown(float height) {
        shardbox.setRightDown(height);
    }

    public float getLeftUp() {
        return shardbox.getLeftUp();
    }

    public float getLeftDown() {
        return shardbox.getLeftDown();
    }

    public float getRightUp() {
        return shardbox.getRightUp();
    }

    public float getRightDown() {
        return shardbox.getRightDown();
    }

    public void setNorth(Shard North, boolean fit) {
        outer.put(Shardenum.North, North);
        if (fit) {
            North.setLocalTranslation(this.getLocalTranslation().getX() + size, North.getLocalTranslation().getY(), this.getLocalTranslation().getZ());
            North.getControl(RigidBodyControl.class).setPhysicsLocation(North.getLocalTranslation());
            North.setLeftDown(this.getLeftUp());
            North.setRightDown(this.getRightUp());
            North.updateGeometricState();
            North.updatePhysic();
        }
        if (North.getSouth() == null) {
            North.setSouth(this, false);

        }
        North.fizzlein();
        North.updateGeometricState();
        North.updatePhysic();
    }

    public void setSouth(Shard South, boolean fit) {
        outer.put(Shardenum.South, South);
        if (fit) {
            South.setLocalTranslation(this.getLocalTranslation().getX() - size, South.getLocalTranslation().getY(), this.getLocalTranslation().getZ());
            South.getControl(RigidBodyControl.class).setPhysicsLocation(South.getLocalTranslation());
            South.setLeftUp(this.getLeftDown());
            South.setRightUp(this.getRightDown());
            South.updateGeometricState();
            South.updatePhysic();
        }
        if (South.getNorth() == null) {
            South.setNorth(this, false);

        }
        South.fizzlein();
        South.updateGeometricState();
        South.updatePhysic();
    }

    public void setEast(Shard East, boolean fit) {
        outer.put(Shardenum.East, East);
        if (fit) {
            East.setLocalTranslation(this.getLocalTranslation().getX(), East.getLocalTranslation().getY(), this.getLocalTranslation().getZ() + size);
            East.getControl(RigidBodyControl.class).setPhysicsLocation(East.getLocalTranslation());

            East.setLeftUp(this.getRightUp());
            East.setLeftDown(this.getRightDown());
            East.updateGeometricState();
            East.updatePhysic();


        }
        if (East.getWest() == null) {
            East.setWest(this, false);

        }
        East.fizzlein();
        East.updateGeometricState();
        East.updatePhysic();
    }

    public void setWest(Shard West, boolean fit) {
        outer.put(Shardenum.West, West);
        if (fit) {
            West.setLocalTranslation(this.getLocalTranslation().getX(), West.getLocalTranslation().getY(), this.getLocalTranslation().getZ() - size);
            West.getControl(RigidBodyControl.class).setPhysicsLocation(West.getLocalTranslation());
            West.setRightUp(this.getLeftUp());
            West.setRightDown(this.getLeftDown());
            West.updateGeometricState();
            West.updatePhysic();
        }
        if (West.getEast() == null) {
            West.setEast(this, false);

        }
        West.fizzlein();
        West.updateGeometricState();
        West.updatePhysic();
    }

    public Shard getNorth() {
        if (outer.containsKey(Shardenum.North)) {
            return outer.get(Shardenum.North);
        } else {
            return null;
        }


    }

    public Shard getSouth() {
        if (outer.containsKey(Shardenum.South)) {
            return outer.get(Shardenum.South);
        } else {
            return null;
        }

    }

    public Shard getEast() {
        if (outer.containsKey(Shardenum.East)) {
            return outer.get(Shardenum.East);
        } else {
            return null;
        }

    }

    public Shard getWest() {
        if (outer.containsKey(Shardenum.West)) {
            return outer.get(Shardenum.West);
        } else {
            return null;
        }

    }
}
