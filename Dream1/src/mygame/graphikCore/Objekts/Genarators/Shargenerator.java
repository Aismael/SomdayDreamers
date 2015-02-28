/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts.Genarators;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.export.Savable;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import javax.vecmath.Vector2f;
import mygame.graphikCore.Objekts.Shard;
import sun.org.mozilla.javascript.internal.ObjToIntMap;

/**
 *
 * @author Aismael
 */
public class Shargenerator extends Node {

    float shardsize, shardnullheight;
    AssetManager assetManager;
    BulletAppState bulletAppState;
    Vector3f Playerpos;
    HashMap<Vector3f, Shard> World;
    public int shardload;
    private int x=1;
    private int z=1;

    public Shargenerator(AssetManager assetManager, BulletAppState bulletAppState) {
        shardsize = 2;
        shardnullheight = 1;
        shardload = 4;
        this.assetManager = assetManager;
        this.bulletAppState = bulletAppState;
        World = new HashMap<>(5);
        Playerpos = new Vector3f(0, 0, 0);
        this.makeWorld(Playerpos);

    }

    public void makeWorld(Vector3f PlayerposA) {
        //System.err.print(PlayerposA+"\n");
        int xt = (int) (PlayerposA.getX() / shardsize);
        int zt = (int) (PlayerposA.getZ() / shardsize);
        if (xt != x || zt != z) {
            x=xt;
            z=zt;
            //System.err.print("Playerpos x= " + x + " z= " + z + "\n");
            //Cross
            make(x, z);
            for (int row = 0; row < shardload; row++) {
                for (int column = 0; column < shardload; column++) {
                    System.err.print("r" + row + "c" + column + "\n");
                    make(x + row, z + column);
                    make(x - row, z - column);
                    make(x + row, z - column);
                    make(x - row, z + column);

                }
            }



        }

    }

    private Shard makeShard(float x, float z) {
        Shard s = new Shard(assetManager, bulletAppState, shardsize, shardnullheight);
        this.attachChild(s);
        s.setName(s.getName() + " x= " + x + " z= " + z);
        System.err.print("____________________________________________________\n");

        System.err.print("make" + " x= " + x + " z= " + z + "\n");
        s.fizzle();
        s.initPhysic();

        return s;
    }

    private void make(float x, float z) {
        Vector3f TempVec = new Vector3f();
        TempVec.set(x, 0, z);
        if (!World.containsKey(TempVec)) {
            World.put(TempVec, makeShard(x, z));
            introduce(TempVec);
        }
    }

    private void introduce(Vector3f pos) {
        Vector3f TempVec = new Vector3f();

        TempVec.set(pos.getX() - 1, 0, pos.getZ());
        System.err.println("c" + World.containsKey(TempVec) + TempVec.toString() + "\n");
        if (World.containsKey(TempVec) && World.get(TempVec).getNorth() == null) {
            System.err.print(TempVec + "\n");
            if (World.get(pos).getSouth() == null) {
                World.get(TempVec).setNorth(World.get(pos), true);
                System.err.print(true + "\n");
            } else {
                World.get(TempVec).setNorth(World.get(pos), false);
                System.err.print(false + "\n");
            }
        }

        TempVec.set(pos.getX() + 1, 0, pos.getZ());
        System.err.println("c" + World.containsKey(TempVec) + TempVec.toString() + "\n");
        if (World.containsKey(TempVec) && World.get(TempVec).getSouth() == null) {
            System.err.print(TempVec + "\n");
            if (World.get(pos).getNorth() == null) {
                World.get(TempVec).setSouth(World.get(pos), true);
                System.err.print(true + "\n");
            } else {
                World.get(TempVec).setSouth(World.get(pos), false);
                System.err.print(false + "\n");
            }
        }

        TempVec.set(pos.getX(), 0, pos.getZ() - 1);
        System.err.println("c" + World.containsKey(TempVec) + TempVec.toString() + "\n");
        if (World.containsKey(TempVec) && World.get(TempVec).getEast() == null) {
            System.err.print(TempVec + "\n");
            if (World.get(pos).getWest() == null) {
                World.get(TempVec).setEast(World.get(pos), true);
                System.err.print(true + "\n");
            } else {
                World.get(TempVec).setEast(World.get(pos), false);
                System.err.print(false + "\n");
            }
        }

        TempVec.set(pos.getX(), 0, pos.getZ() + 1);
        System.err.println("c" + World.containsKey(TempVec) + TempVec.toString() + "\n");
        if (World.containsKey(TempVec) && World.get(TempVec).getWest() == null) {
            System.err.print(TempVec + "\n");
            if (World.get(pos).getEast() == null) {
                World.get(TempVec).setWest(World.get(pos), true);
                System.err.print(true + "\n");
            } else {
                World.get(TempVec).setWest(World.get(pos), false);
                System.err.print(false + "\n");
            }
        }

    }
}
