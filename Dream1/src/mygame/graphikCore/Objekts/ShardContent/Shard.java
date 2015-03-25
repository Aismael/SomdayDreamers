/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts.ShardContent;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.util.TangentBinormalGenerator;
import mygame.graphikCore.Objekts.Genarators.MaterialGenerator;

/**
 *
 * @author Aismael
 */
public class Shard extends Node {

    private static  AssetManager assetManager;
    private static  BulletAppState bulletAppState;
    private static MaterialGenerator materialGenerator;
    private Shard North,South,West,East;
    Shardmesh shardbox;
    private static float size=0;
    private RigidBodyControl collide;
    private static final float factor= 3;
    private float height;
    

    public Shard(AssetManager assetManager, BulletAppState bulletAppState, float size, float height) {
        this.setName("shardNumber#");
        if(Shard.assetManager==null){
        Shard.assetManager = assetManager;
        }
        if(Shard.bulletAppState==null){
        Shard.bulletAppState = bulletAppState;
        }
        if(Shard.materialGenerator==null){
        Shard.materialGenerator = new MaterialGenerator(assetManager);
        }
        if(Shard.size==0){
        Shard.size = size;
        }
        
        this.height = height;
        shardbox = new Shardmesh(size, height, size);
        Geometry shardboxg = new Geometry("Shardbox", shardbox);
        shardboxg.setLocalTranslation(new Vector3f(0, -1, 0));  
        shardboxg.setMaterial(Shard.materialGenerator.getMaterial(MaterialGenerator.Materialname.pondx128));
        //shardboxg.setMaterial(nMat);
        TangentBinormalGenerator.generate(shardboxg);
        shardboxg.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        this.attachChild(shardboxg);
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
        //outer.put(Shardenum.North, North);
        this.North=North;
        if (fit) {
            North.setLocalTranslation(this.getLocalTranslation().getX() + size, North.getLocalTranslation().getY(), this.getLocalTranslation().getZ());
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
        //outer.put(Shardenum.South, South);
        this.South=South;
        if (fit) {
            South.setLocalTranslation(this.getLocalTranslation().getX() - size, South.getLocalTranslation().getY(), this.getLocalTranslation().getZ());
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
        //outer.put(Shardenum.East, East);
        this.East=East;
        if (fit) {
            East.setLocalTranslation(this.getLocalTranslation().getX(), East.getLocalTranslation().getY(), this.getLocalTranslation().getZ() + size);
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
        //outer.put(Shardenum.West, West);
        this.West=West;
        if (fit) {
            West.setLocalTranslation(this.getLocalTranslation().getX(), West.getLocalTranslation().getY(), this.getLocalTranslation().getZ() - size);
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
        if (North!=null) {
            return North;
        } else {
            return null;
        }


    }

    public Shard getSouth() {
        if (South!=null) {
            return South;
        } else {
            return null;
        }

    }

    public Shard getEast() {
        if (East!=null) {
            return East;
        } else {
            return null;
        }

    }

    public Shard getWest() {
        if (West!=null) {
            return West;
        } else {
            return null;
        }

    }

    public void endclean() {
        this.getNorth().setLeftDown(this.getLeftUp());
        this.getNorth().setRightDown(this.getRightUp());
        this.getSouth().setLeftUp(this.getLeftDown());
        this.getSouth().setRightUp(this.getRightDown());
        this.getEast().setLeftUp(this.getRightUp());
        this.getEast().setLeftDown(this.getRightDown());
        this.getWest().setRightUp(this.getLeftUp());
        this.getWest().setRightDown(this.getLeftDown());
        this.initPhysic();
        
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
