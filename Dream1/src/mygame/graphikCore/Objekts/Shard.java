/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.SimplexCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.util.TangentBinormalGenerator;
import java.util.HashMap;
import mygame.graphikCore.GTAPObject;
import mygame.graphikCore.GTAPObjectFactory;
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
    private RigidBodyControl collide;
    float factor;
    private final float height;

    public Shard(AssetManager assetManager, BulletAppState bulletAppState, float size, float height) {
        factor = 3;
        this.setName("shardNumber#");
        outer = new HashMap<>(4);
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
        this.size = size;
        this.height = height;
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
        this.initPhysic();
        this.setCullHint(CullHint.Dynamic);
        
        

    }

    public void initPhysic() {
        //

        collide = new RigidBodyControl(0.0f);
        collide.setCollisionShape(CollisionShapeFactory.createMeshShape(this));
        this.addControl(collide);
        collide.setKinematic(false);
        bulletAppState.getPhysicsSpace().add(collide);
        collide.setGravity(new Vector3f(0, -9.81f, 0));
        this.getControl(RigidBodyControl.class).setPhysicsLocation(this.getLocalTranslation());
    }

    public void updatePhysic() {
        this.removeControl(collide);
        bulletAppState.getPhysicsSpace().remove(collide);

        collide = new RigidBodyControl(0.0f);
        collide.setCollisionShape(CollisionShapeFactory.createMeshShape(this));

        this.addControl(collide);
        bulletAppState.getPhysicsSpace().add(collide);
        this.getControl(RigidBodyControl.class).setPhysicsLocation(this.getLocalTranslation());

    }

    public void fizzle() {
        float fMax = this.getMiddle();

        setLeftUp(((float) (fMax + randomGenerator() * size / factor)));
        setLeftDown((float) (fMax + randomGenerator() * size / factor));
        setRightUp((float) (fMax + randomGenerator() * size / factor));
        setRightDown((float) (fMax + randomGenerator() * size / factor));

        reWork();
    }

    public void fizzlein() {
        float fMax = this.getMiddle();
        //float fMax = this.getMiddle();
        if (this.getWest() == null && this.getNorth() == null) {
            setLeftUp((float) (fMax + randomGenerator() * size / factor));
        }
        if (this.getWest() == null && this.getSouth() == null) {
            setLeftDown((float) (fMax + randomGenerator() * size / factor));
        }
        if (this.getEast() == null && this.getNorth() == null) {
            setRightUp((float) (fMax + randomGenerator() * size / factor));
        }
        if (this.getEast() == null && this.getSouth() == null) {
            setRightDown((float) (fMax + randomGenerator() * size / factor));
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
        }
        if (North.getSouth() == null) {
            North.setSouth(this, false);

        }
        North.fizzlein();
        North.updateGeometricState();
        if (this.getNorth() != null && this.getSouth() != null && this.getEast() != null && this.getWest() != null) {
            this.endclean();
        }
    }

    public void setSouth(Shard South, boolean fit) {
        outer.put(Shardenum.South, South);
        if (fit) {
            South.setLocalTranslation(this.getLocalTranslation().getX() - size, South.getLocalTranslation().getY(), this.getLocalTranslation().getZ());
            South.getControl(RigidBodyControl.class).setPhysicsLocation(South.getLocalTranslation());
            South.setLeftUp(this.getLeftDown());
            South.setRightUp(this.getRightDown());
        }
        if (South.getNorth() == null) {
            South.setNorth(this, false);

        }
        South.fizzlein();
        South.updateGeometricState();
        if (this.getNorth() != null && this.getSouth() != null && this.getEast() != null && this.getWest() != null) {
            this.endclean();

        }
    }

    public void setEast(Shard East, boolean fit) {
        outer.put(Shardenum.East, East);
        if (fit) {
            East.setLocalTranslation(this.getLocalTranslation().getX(), East.getLocalTranslation().getY(), this.getLocalTranslation().getZ() + size);
            East.getControl(RigidBodyControl.class).setPhysicsLocation(East.getLocalTranslation());

            East.setLeftUp(this.getRightUp());
            East.setLeftDown(this.getRightDown());
        }
        if (East.getWest() == null) {
            East.setWest(this, false);

        }
        East.fizzlein();
        East.updateGeometricState();
        if (this.getNorth() != null && this.getSouth() != null && this.getEast() != null && this.getWest() != null) {
            this.endclean();

        }
    }

    public void setWest(Shard West, boolean fit) {
        outer.put(Shardenum.West, West);
        if (fit) {
            West.setLocalTranslation(this.getLocalTranslation().getX(), West.getLocalTranslation().getY(), this.getLocalTranslation().getZ() - size);
            West.getControl(RigidBodyControl.class).setPhysicsLocation(West.getLocalTranslation());
            West.setRightUp(this.getLeftUp());
            West.setRightDown(this.getLeftDown());
        }
        if (West.getEast() == null) {
            West.setEast(this, false);

        }
        West.fizzlein();
        West.updateGeometricState();
        if (this.getNorth() != null && this.getSouth() != null && this.getEast() != null && this.getWest() != null) {
            this.endclean();

        }
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

    public void endclean() {
        this.getNorth().setLeftDown(this.getLeftUp());
        this.getNorth().setRightDown(this.getRightUp());
        this.getNorth().updatePhysic();
        this.getSouth().setLeftUp(this.getLeftDown());
        this.getSouth().setRightUp(this.getRightDown());
        this.getSouth().updatePhysic();
        this.getEast().setLeftUp(this.getRightUp());
        this.getEast().setLeftDown(this.getRightDown());
        this.getEast().updatePhysic();
        this.getWest().setRightUp(this.getLeftUp());
        this.getWest().setRightDown(this.getLeftDown());
        this.getWest().updatePhysic();
        this.updatePhysic();
        
        //testchild
        //GTAPObjectFactory GF=new GTAPObjectFactory(assetManager, bulletAppState);
        //GF.setScale(0.01f);
        //GF.setShine(128f);
        
        //GF.setWeight(0f);
        //GF.setTrans(this.getLocalTranslation().add(new Vector3f(0, this.getMiddle()-height, 0)));
        //this.attachChild(GF.makeModel(new GTAPObject("Fullmodels/Boxes/Barrel/big_diffus.tga", 
        //        "Fullmodels/Boxes/Barrel/big_normal.tga", 
        //        "Fullmodels/Boxes/Barrel/big_specular.tga", 
        //        "Fullmodels/Boxes/Barrel/big_wood_barrel.j3o")));
    }
}
