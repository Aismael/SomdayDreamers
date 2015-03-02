/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts.Genarators;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import java.util.HashMap;

/**
 *
 * @author Aismael
 */
public class MaterialGenerator {

    private static AssetManager assetManager;
    private static HashMap<Materialname, Material> Materials=new HashMap<>();

    public MaterialGenerator(AssetManager assetManager1) {
        if (MaterialGenerator.assetManager == null) {
            MaterialGenerator.assetManager = assetManager1;
        }


    }

    private Material makeMat(Materialname name) {
        switch (name) {
            case pondx128:
                return makePondx128();
            case normalmat:
                return makeNormalMat();
            default:
                return makeDefaultMat();
        }
    }

    private Material makePondx128() {


        Material sphereMat = new Material(MaterialGenerator.assetManager,
                "Common/MatDefs/Light/Lighting.j3md");
        sphereMat.setTexture("DiffuseMap",
                MaterialGenerator.assetManager.loadTexture("UTextures/Pondx128.tga"));
        sphereMat.setTexture("NormalMap",
                MaterialGenerator.assetManager.loadTexture("UTextures/Pond_normalx128.tga"));
        sphereMat.setBoolean("UseMaterialColors", true);
        sphereMat.setColor("Diffuse", ColorRGBA.White);
        sphereMat.setColor("Specular", ColorRGBA.White);
        sphereMat.setColor("Ambient", ColorRGBA.White);

        sphereMat.setFloat("Shininess", 128f);  // [0,128]
        return sphereMat;
    }

    private Material makeDefaultMat() {
        Material mat = new Material(MaterialGenerator.assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);
        return mat;
    }

    private Material makeNormalMat() {
        Material nMat = new Material(MaterialGenerator.assetManager,
                "Common/MatDefs/Misc/ShowNormals.j3md");
        return nMat;
    }

    public static enum Materialname {

        pondx128, normalmat
    }

    public Material getMaterial(Materialname name) {
        if (Materials.containsKey(name)) {
            return Materials.get(name);
        } else {
            Materials.put(name, makeMat(name));
            return Materials.get(name);
        }

    }
}
