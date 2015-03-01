/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;

import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Box;
import com.jme3.util.TangentBinormalGenerator;
import mygame.graphikCore.GTAPObject;
import mygame.graphikCore.GTAPObjectFactory;

/**
 *
 * @author Aismael
 */
public class Root extends Node {

    public Root(AssetManager assetManager, BulletAppState bulletAppState) {
        Material nMat = new Material(assetManager, 
        "Common/MatDefs/Misc/ShowNormals.j3md");
        //this.setQueueBucket(RenderQueue.Bucket.Translucent);
       
        
       
        GTAPObjectFactory GF=new GTAPObjectFactory(assetManager, bulletAppState);
        GF.setScale(0.01f);
        GF.setShine(128f);
        
        GF.setWeight(10f);
        GF.setTrans(new Vector3f(4, 0.1f, 2));
        this.attachChild(GF.makeModel(new GTAPObject("Fullmodels/Boxes/Barrel/big_diffus.tga", 
                "Fullmodels/Boxes/Barrel/big_normal.tga", 
                "Fullmodels/Boxes/Barrel/big_specular.tga", 
                "Fullmodels/Boxes/Barrel/big_wood_barrel.j3o")));
        
        GF.setTrans(new Vector3f(2, 0.1f, 2));
        GF.setWeight(3f);
        
        this.attachChild(GF.makeModel(new GTAPObject("Fullmodels/Boxes/Barrel/mini_diffus.tga", 
                "Fullmodels/Boxes/Barrel/mini_normal.tga", 
                "Fullmodels/Boxes/Barrel/mini_specular.tga", 
                "Fullmodels/Boxes/Barrel/mini_wood_barrel.j3o")));
        
        GF.setTrans(new Vector3f(2, 0.3f, 0));
        GF.setWeight(50f);

        //this.attachChild(GF.makeModel(new GTAPObject("Fullmodels/Rocks/7/diffuse.tga", 
         //       "Fullmodels/Rocks/7/normal.tga", 
          //      "Fullmodels/Rocks/7/specular.tga", 
           //     "Fullmodels/Rocks/7/rocks_03.j3o")));
        
        GF.setTrans(new Vector3f(8, 0.1f, 0));
        GF.setWeight(0f);
        GF.setScale(1f);
                GF.setShine(0f);

        GTAPObject x=new GTAPObject("test/1/ef_UV.jpg", 
                "test/1/ef_UV_Normal.jpg", 
                "Fullmodels/Plants/Palm/2/specular.tga", 
                "test/1/Tree_.j3o");
        x.setTranslucentpath("test/1/ef_UV_Alpha.jpg");
                x.setShadowmode(RenderQueue.ShadowMode.Cast);
        this.attachChild(GF.makeModel(x));
        
       GF.setTrans(new Vector3f(8, 0.1f, 8));
        GF.setScale(0.01f);
        
        
         GTAPObject y=new GTAPObject("Fullmodels/Plants/Palm/2/diffuse.tga", 
                "Fullmodels/Plants/Palm/2/normal.tga", 
                "Fullmodels/Plants/Palm/2/specular.tga", 
                "Fullmodels/Plants/Palm/2/palm_straight.j3o");
        y.setShadowmode(shadowMode.Cast);
        y.setTranslucentpath("Fullmodels/Plants/Palm/2/translucency3.tga");
        this.attachChild(GF.makeModel(y));
        
        
        
        Box shardbox= new Box(1f,1f,1f);
        Geometry shardboxg = new Geometry("Shardbox", shardbox);
        shardboxg.setLocalTranslation(new Vector3f(0, 0.5f, 0));
        Material mat = new Material(assetManager,
          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);
        
        Material sphereMat = new Material(assetManager, 
        "Common/MatDefs/Light/Lighting.j3md");
        
        sphereMat.setTexture("DiffuseMap", 
        assetManager.loadTexture("Textures/Terrain/Pond/Pond.jpg"));
        sphereMat.setTexture("NormalMap", 
        assetManager.loadTexture("Textures/Terrain/Pond/Pond_normal.png"));
        sphereMat.setBoolean("UseMaterialColors",true);    
        sphereMat.setColor("Diffuse",ColorRGBA.White);
        sphereMat.setColor("Specular",ColorRGBA.White);
        sphereMat.setColor("Ambient", ColorRGBA.White);

        sphereMat.setFloat("Shininess", 128f);  // [0,128]
        shardboxg.setMaterial(sphereMat);
        nMat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        shardboxg.setCullHint(cullHint.Never);
        //shardboxg.setMaterial(nMat);


       
        shardboxg.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        //shardboxg.setQueueBucket(RenderQueue.Bucket.Translucent);
        if (shardboxg.getMesh().getBuffer(VertexBuffer.Type.Normal) != null) {		
            TangentBinormalGenerator.generate(shardboxg);
        }
        
        this.attachChild(shardboxg);
        
        
        RigidBodyControl landscape = new RigidBodyControl(0.0f);
        shardboxg.addControl(landscape);
        landscape.setKinematic(false);
        bulletAppState.getPhysicsSpace().add(landscape);
        landscape.setGravity(new Vector3f(0, -9.81f, 0));


    }
    
}
