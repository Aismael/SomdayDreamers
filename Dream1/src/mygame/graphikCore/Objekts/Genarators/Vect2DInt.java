/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts.Genarators;

import com.jme3.math.Vector3f;

/**
 *
 * @author Aismael
 */
public class Vect2DInt {
    public int x,z;

    public Vect2DInt(int x,int z){
        this.x=x;
        this.z=z;
    }
     public Vect2DInt(){
        this.x=0;
        this.z=0;
    }
     
     public Vect2DInt(Vect2DInt tv){
        this.x=tv.getX();
        this.z=tv.getZ();
    }
    
   public void set(int x,int z){
        this.x=x;
        this.z=z;
    }
   
   public Vect2DInt setX(int x){
       this.x=x;
       return this;
   }
   public Vect2DInt setZ(int z){
       this.z=z;
       return this;
   }
   public int getX(){
       return x;
   }
    public int getZ(){
       return z;
   }
    
    @Override
   public boolean equals(Object o) {
        if (!(o instanceof Vect2DInt)) { return false; }

        if (this == o) { return true; }

        Vect2DInt comp = (Vect2DInt) o;
        if (Integer.compare(x,comp.x) != 0) return false;
        if (Integer.compare(z,comp.z) != 0) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 37;
        hash += 37 * hash + x;
        hash += 37 * hash + z;
        return hash;
    }
    
    @Override
    public Vect2DInt clone() {
        try {
            return (Vect2DInt) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // can not happen
        }
    }
    
    @Override
     public String toString() {
        return "(" + x + ", " + z + ")";
    }
}
