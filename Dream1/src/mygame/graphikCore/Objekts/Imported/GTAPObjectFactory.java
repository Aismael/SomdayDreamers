/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts.Imported;

import mygame.graphikCore.Objekts.Imported.GTAPObject;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.util.TangentBinormalGenerator;

/**
 *
 * @author Aismael
 */
public class GTAPObjectFactory {

    private Spatial spatial;
    private Material material;
    private AssetManager assetManager;
    private Vector3f localGravity;//setter
    private GTAPObject GTAPO;
    private float shine;//setter
    private BulletAppState bulletAppState;
    private float weight;//setter
    private float scale;//setter
    private Vector3f trans;//setter

    public GTAPObjectFactory(AssetManager assetManager, BulletAppState bulletAppState) {
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
        localGravity = new Vector3f(0, -9.81f, 0);
        scale = 1;
        shine = 0f;
        weight = 0f;
        trans=new Vector3f(0, 0, 0);
        
    }

    private Material makeMat() {
        material = new Material(assetManager, GTAPO.getLightningmodelpath());
        material.setTexture("DiffuseMap",
                assetManager.loadTexture(GTAPO.getDiffuspath()));
        material.setTexture("NormalMap",
                assetManager.loadTexture(GTAPO.getNormalpath()));
        material.setTexture("SpecularMap",
                assetManager.loadTexture(GTAPO.getSpecularpath()));
        if(GTAPO.getTranslucentpath()!=null){
            material.setTexture("AlphaMap",
                assetManager.loadTexture(GTAPO.getTranslucentpath()));
            material.setBoolean("UseAlpha", true);
            material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
            material.getAdditionalRenderState().setAlphaTest(true); 
            material.getAdditionalRenderState().setAlphaFallOff(3f);
            

            
        }
        material.setBoolean("UseMaterialColors", true);
        material.setBoolean("VertexLighting", true);
        
        material.setColor("Diffuse", ColorRGBA.White.mult(new ColorRGBA(1, 1, 1, 10f)));
        material.setColor("Specular", ColorRGBA.White);
        material.setColor("Ambient", ColorRGBA.White);
        material.setFloat("Shininess", shine);
       
        return material;
    }

    public Node makeModel(GTAPObject GTAPO) {
        Node physnode=new Node();
        this.GTAPO = GTAPO;
        spatial = assetManager.loadModel(GTAPO.getModelpath());
        
        //spatial.setQueueBucket(RenderQueue.Bucket.Translucent);

        
        
        spatial.setMaterial(makeMat());
        TangentBinormalGenerator.generate(spatial);
        spatial.setShadowMode(GTAPO.getShadowmode());
        physnode.setLocalTranslation(trans);
        physnode.setLocalScale(scale);
                spatial.center();

        //initPhys();
        physnode.attachChild(spatial);
        initPhys();
        return physnode;
    }

    private void initPhys() {
        CollisionShape cs = CollisionShapeFactory.createDynamicMeshShape(spatial.getParent());
        RigidBodyControl RBC = new RigidBodyControl(cs, weight);
        spatial.getParent().addControl(RBC);
        RBC.setGravity(localGravity);
        bulletAppState.getPhysicsSpace().add(RBC);
        
    }

    public void setLocalGravity(Vector3f localGravity) {
        this.localGravity = localGravity;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setShine(float shine) {
        this.shine = shine;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setTrans(Vector3f trans) {
        this.trans = trans;
    }
}
