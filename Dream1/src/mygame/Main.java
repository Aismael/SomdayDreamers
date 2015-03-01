package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.CartoonEdgeFilter;
import com.jme3.post.filters.FogFilter;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Node;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.PssmShadowRenderer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import mygame.gamplayCore.GameCharacterControl;
import mygame.gamplayCore.InputAppState;
import mygame.graphikCore.Objekts.Root;
import mygame.graphikCore.Objekts.Genarators.Shargenerator;
import mygame.graphikCore.configInitializer;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {


        Main app = new Main();
        configInitializer cI = new configInitializer();
        app.setSettings(cI.getSettings());
        app.start();
    }
    private BulletAppState bulletAppState;
    private Shargenerator Sg;
    private Node playerNode;
   

    @Override
    public void simpleInitApp() {
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        viewPort.setBackgroundColor(new ColorRGBA(0.7f, 0.8f, 1f, 1f));
        flyCam.setMoveSpeed(100);
        setUpLight();
         
        playerNode = new Node("Player");
        playerNode.setLocalTranslation(0, 3, 0);
        GameCharacterControl charControl=new GameCharacterControl(6, 1, 1.2f, 2.5f, 8f);
        //TODO later set in conf
        cam.setFrustumFar(100);
        charControl.setCamera(cam);
        playerNode.addControl(charControl);
        charControl.setGravity(new Vector3f(0, -9.81f, 0));
                bulletAppState.getPhysicsSpace().add(charControl);
       
        Root r = new Root(this.getAssetManager(), bulletAppState);
        
        //charControl.setDuckedFactor(-0.5f);
        InputAppState in = new InputAppState(inputManager, charControl);
        rootNode.attachChild(r);
                //playerNode.setLocalTranslation(10,10,10);
        playerNode.setShadowMode(ShadowMode.Cast);
        Sg=new Shargenerator(assetManager, bulletAppState);
        //TODO Sg.shardload Later set in config
        rootNode.attachChild(Sg);
        rootNode.attachChild(playerNode);
        





    }

    private void setUpLight() {
        // We add light so we see the scene
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.5f));
        rootNode.addLight(al);

        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White.mult(0.5f));
        dl.setDirection(new Vector3f(5f, -5f, 5f).normalizeLocal());
        rootNode.addLight(dl);
        
        
        final int SHADOWMAP_SIZE=1024;
        DirectionalLightShadowRenderer dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
        dlsr.setLight(dl);
        viewPort.addProcessor(dlsr);
 
        DirectionalLightShadowFilter dlsf = new DirectionalLightShadowFilter(assetManager, SHADOWMAP_SIZE, 3);
        dlsf.setLight(dl);
        dlsf.setEnabled(true);
        
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(dlsf);
        viewPort.addProcessor(fpp);
        
        //PssmShadowRenderer pssmRenderer = new PssmShadowRenderer(assetManager, 1024, 3);
        //pssmRenderer.setDirection(new Vector3f(-.5f,-.5f,-.5f).normalizeLocal()); // light direction
        //viewPort.addProcessor(pssmRenderer);
        
                
        //SSAOFilter ssaoFilter = new SSAOFilter(12.94f, 43.92f, 0.33f, 0.61f);
        //fpp.addFilter(ssaoFilter);
        //viewPort.addProcessor(fpp);
        
        //CartoonEdgeFilter ct = new CartoonEdgeFilter();
        //fpp.addFilter(ct);
        //viewPort.addProcessor(fpp);
        
        FogFilter ff = new FogFilter(ColorRGBA.Gray, 0.5f, 100f);
        fpp.addFilter(ff);
        viewPort.addProcessor(fpp);
        
        
    }

    

    @Override
    public void simpleUpdate(float tpf) {
        //System.err.print(playerNode.getLocalTranslation().getX()+"||"+playerNode.getLocalTranslation().getZ()+"\n");
        Sg.makeWorld(playerNode.getLocalTranslation());
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public void stop() {
        try {
            this.settings.save("settings.pref");
        } catch (BackingStoreException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.stop();
    }
}
