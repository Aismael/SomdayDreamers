/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts.ShardContent;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Aismael
 */
public class Shardmesh extends Mesh {

    Vector3f[] vertices;
    Vector3f[] normal;

    public Shardmesh(float x, float y, float z) {
        super();
        vertices = new Vector3f[6];
        
        //top
        vertices[0] = new Vector3f(0, y, 0);
        vertices[1] = new Vector3f(0, y, z);
        vertices[2] = new Vector3f(x, y, 0);
        vertices[3] = new Vector3f(x, y, z);
        vertices[4] = new Vector3f(x, y, 0);
        vertices[5] = new Vector3f(0, y, z);

        Vector2f[] texCoord = new Vector2f[6];
       
        //top
        texCoord[0] = new Vector2f(1f, 0f);
        texCoord[1] = new Vector2f(1f, 1f);
        texCoord[2] = new Vector2f(0f, 0f);
        texCoord[3] = new Vector2f(0f, 1f);
        texCoord[4] = new Vector2f(0f, 0f);
        texCoord[5] = new Vector2f(1f, 1f);



        normal = new Vector3f[6];
        //front
        
        //top
        normal[0] = new Vector3f(0, 1, 0);
        normal[1] = new Vector3f(0, 1, 0);
        normal[2] = new Vector3f(0, 1, 0);
        normal[3] = new Vector3f(0, 1, 0);
        normal[4] = new Vector3f(0, 1, 0);
        normal[5] = new Vector3f(0, 1, 0);


        int[] indexes = {
           
            0, 1, 2,
            3, 4, 5
        };
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        this.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        this.setBuffer(VertexBuffer.Type.Normal, 3, BufferUtils.createFloatBuffer(normal));

        this.setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(indexes));

        this.updateBound();
    }

    public void setLeftUp(float height) {
        
        vertices[2].setY(height);
        vertices[4].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();
        
        


    }

    public Vector3f[] getVertices(){
        return vertices;
    }
    public void setLeftDown(float height) {
        
        vertices[0].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();

    }

    public void setRightUp(float height) {
        vertices[3].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();

    }

    public void setRightDown(float height) {
        vertices[1].setY(height);
        vertices[5].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();
       
    }

    private void settopNormals() {
        //points
        Vector3f[] first = {
            vertices[0],
            vertices[1],
            vertices[2]
        };
        Vector3f[] second = {
            vertices[5],
            vertices[3],
            vertices[4]
        };
        Vector3f normalFirst = getnormalForVertice(first);
        Vector3f normalSecond = getnormalForVertice(second);
        normal[0].set(normalFirst);
        normal[1].set(normalFirst);
        normal[2].set(normalFirst);
        normal[3].set(normalSecond);
        normal[4].set(normalSecond);
        normal[5].set(normalSecond);
        this.setBuffer(VertexBuffer.Type.Normal, 3, BufferUtils.createFloatBuffer(normal));

    }

    private Vector3f getnormalForVertice(Vector3f[] vertice) {
        Vector3f normalV = new Vector3f();
        Vector3f V1 = vertice[0].subtract(vertice[1]);
        Vector3f V2 = vertice[1].subtract(vertice[2]);
        normalV.setX(V1.y * V2.z - V1.z * V2.y);
        normalV.setY(V1.z * V2.x - V1.x * V2.z);
        normalV.setZ(V1.x * V2.y - V1.y * V2.x);
        return normalV;
    }

  
    public float getLeftUp() {
        return vertices[2].getY();
    }

    public float getLeftDown() {
        return vertices[0].getY();
    }

    public float getRightUp() {
        return vertices[3].getY();
    }

    public float getRightDown() {
        return vertices[1].getY();
    }
}
