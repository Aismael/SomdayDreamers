/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore.Objekts;

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
        vertices = new Vector3f[36];
        //front
        vertices[0] = new Vector3f(0, 0, 0);
        vertices[1] = new Vector3f(0, y, 0);
        vertices[2] = new Vector3f(x, 0, 0);
        vertices[3] = new Vector3f(x, y, 0);
        vertices[4] = new Vector3f(x, 0, 0);
        vertices[5] = new Vector3f(0, y, 0);
        //back
        vertices[6] = new Vector3f(0, 0, z);
        vertices[7] = new Vector3f(x, 0, z);
        vertices[8] = new Vector3f(0, y, z);
        vertices[9] = new Vector3f(x, y, z);
        vertices[10] = new Vector3f(0, y, z);
        vertices[11] = new Vector3f(x, 0, z);
        //left
        vertices[12] = new Vector3f(x, 0, 0);
        vertices[13] = new Vector3f(x, y, 0);
        vertices[14] = new Vector3f(x, 0, z);
        vertices[15] = new Vector3f(x, y, z);
        vertices[16] = new Vector3f(x, 0, z);
        vertices[17] = new Vector3f(x, y, 0);
        //right
        vertices[18] = new Vector3f(0, 0, 0);
        vertices[19] = new Vector3f(0, 0, z);
        vertices[20] = new Vector3f(0, y, 0);
        vertices[21] = new Vector3f(0, y, z);
        vertices[22] = new Vector3f(0, y, 0);
        vertices[23] = new Vector3f(0, 0, z);
        //bottom
        vertices[24] = new Vector3f(0, 0, 0);
        vertices[25] = new Vector3f(x, 0, 0);
        vertices[26] = new Vector3f(x, 0, z);
        vertices[27] = new Vector3f(x, 0, z);
        vertices[28] = new Vector3f(0, 0, z);
        vertices[29] = new Vector3f(0, 0, 0);
        //top
        vertices[30] = new Vector3f(0, y, 0);
        vertices[31] = new Vector3f(0, y, z);
        vertices[32] = new Vector3f(x, y, 0);
        vertices[33] = new Vector3f(x, y, z);
        vertices[34] = new Vector3f(x, y, 0);
        vertices[35] = new Vector3f(0, y, z);

        Vector2f[] texCoord = new Vector2f[36];
        //Front
        texCoord[0] = new Vector2f(1f, 0f);
        texCoord[1] = new Vector2f(1f, 1f);
        texCoord[2] = new Vector2f(0f, 0f);
        texCoord[3] = new Vector2f(0f, 1f);
        texCoord[4] = new Vector2f(0f, 0f);
        texCoord[5] = new Vector2f(1f, 1f);
        //back
        texCoord[6] = new Vector2f(0f, 0f);
        texCoord[7] = new Vector2f(1f, 0f);
        texCoord[8] = new Vector2f(0f, 1f);
        texCoord[9] = new Vector2f(1f, 1f);
        texCoord[10] = new Vector2f(0f, 1f);
        texCoord[11] = new Vector2f(1f, 0f);
        //left
        texCoord[12] = new Vector2f(1f, 0f);
        texCoord[13] = new Vector2f(1f, 1f);
        texCoord[14] = new Vector2f(0f, 0f);
        texCoord[15] = new Vector2f(0f, 1f);
        texCoord[16] = new Vector2f(0f, 0f);
        texCoord[17] = new Vector2f(1f, 1f);
        //right
        texCoord[18] = new Vector2f(0f, 0f);
        texCoord[19] = new Vector2f(1f, 0f);
        texCoord[20] = new Vector2f(0f, 1f);
        texCoord[21] = new Vector2f(1f, 1f);
        texCoord[22] = new Vector2f(0f, 1f);
        texCoord[23] = new Vector2f(1f, 0f);
        //bottom
        texCoord[24] = new Vector2f(1f, 1f);
        texCoord[25] = new Vector2f(0f, 1f);
        texCoord[26] = new Vector2f(0f, 0f);
        texCoord[27] = new Vector2f(0f, 0f);
        texCoord[28] = new Vector2f(1f, 0f);
        texCoord[29] = new Vector2f(1f, 1f);
        //top
        texCoord[30] = new Vector2f(1f, 0f);
        texCoord[31] = new Vector2f(1f, 1f);
        texCoord[32] = new Vector2f(0f, 0f);
        texCoord[33] = new Vector2f(0f, 1f);
        texCoord[34] = new Vector2f(0f, 0f);
        texCoord[35] = new Vector2f(1f, 1f);



        normal = new Vector3f[36];
        //front
        normal[0] = new Vector3f(0, 0, -1);
        normal[1] = new Vector3f(0, 0, -1);
        normal[2] = new Vector3f(0, 0, -1);
        normal[3] = new Vector3f(0, 0, -1);
        normal[4] = new Vector3f(0, 0, -1);
        normal[5] = new Vector3f(0, 0, -1);
        //back
        normal[6] = new Vector3f(0, 0, 1);
        normal[7] = new Vector3f(0, 0, 1);
        normal[8] = new Vector3f(0, 0, 1);
        normal[9] = new Vector3f(0, 0, 1);
        normal[10] = new Vector3f(0, 0, 1);
        normal[11] = new Vector3f(0, 0, 1);
        //left
        normal[12] = new Vector3f(1, 0, 0);
        normal[13] = new Vector3f(1, 0, 0);
        normal[14] = new Vector3f(1, 0, 0);
        normal[15] = new Vector3f(1, 0, 0);
        normal[16] = new Vector3f(1, 0, 0);
        normal[17] = new Vector3f(1, 0, 0);
        //right
        normal[18] = new Vector3f(-1, 0, 0);
        normal[19] = new Vector3f(-1, 0, 0);
        normal[20] = new Vector3f(-1, 0, 0);
        normal[21] = new Vector3f(-1, 0, 0);
        normal[22] = new Vector3f(-1, 0, 0);
        normal[23] = new Vector3f(-1, 0, 0);
        //bottom
        normal[24] = new Vector3f(0, -1, 0);
        normal[25] = new Vector3f(0, -1, 0);
        normal[26] = new Vector3f(0, -1, 0);
        normal[27] = new Vector3f(0, -1, 0);
        normal[28] = new Vector3f(0, -1, 0);
        normal[29] = new Vector3f(0, -1, 0);
        //top
        normal[30] = new Vector3f(0, 1, 0);
        normal[31] = new Vector3f(0, 1, 0);
        normal[32] = new Vector3f(0, 1, 0);
        normal[33] = new Vector3f(0, 1, 0);
        normal[34] = new Vector3f(0, 1, 0);
        normal[35] = new Vector3f(0, 1, 0);


        int[] indexes = {
            0, 1, 2,
            3, 4, 5,
            6, 7, 8,
            9, 10, 11,
            12, 13, 14,
            15, 16, 17,
            18, 19, 20,
            21, 22, 23,
            24, 25, 26,
            27, 28, 29,
            30, 31, 32,
            33, 34, 35
        };
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        this.setBuffer(VertexBuffer.Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        this.setBuffer(VertexBuffer.Type.Normal, 3, BufferUtils.createFloatBuffer(normal));

        this.setBuffer(VertexBuffer.Type.Index, 3, BufferUtils.createIntBuffer(indexes));

        this.updateBound();
    }

    public void setLeftUp(float height) {
         vertices[3].setY(height);
        vertices[13].setY(height);
        vertices[17].setY(height);
        vertices[32].setY(height);
        vertices[34].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();
        
        


    }

    public Vector3f[] getVertices(){
        return vertices;
    }
    public void setLeftDown(float height) {
        vertices[1].setY(height);
        vertices[5].setY(height);
        vertices[20].setY(height);
        vertices[22].setY(height);
        vertices[30].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();

    }

    public void setRightUp(float height) {
        vertices[9].setY(height);
        vertices[15].setY(height);
        vertices[33].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();

    }

    public void setRightDown(float height) {
        vertices[8].setY(height);
        vertices[10].setY(height);
        vertices[21].setY(height);
        vertices[31].setY(height);
        vertices[35].setY(height);
        this.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        settopNormals();
        this.updateBound();
       
    }

    private void settopNormals() {
        //points
        Vector3f[] first = {
            vertices[30],
            vertices[31],
            vertices[32]
        };
        Vector3f[] second = {
            vertices[35],
            vertices[33],
            vertices[34]
        };
        Vector3f normalFirst = getnormalForVertice(first);
        Vector3f normalSecond = getnormalForVertice(second);
        normal[30].set(normalFirst);
        normal[31].set(normalFirst);
        normal[32].set(normalFirst);
        normal[33].set(normalSecond);
        normal[34].set(normalSecond);
        normal[35].set(normalSecond);
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
        return vertices[32].getY();
    }

    public float getLeftDown() {
        return vertices[30].getY();
    }

    public float getRightUp() {
        return vertices[33].getY();
    }

    public float getRightDown() {
        return vertices[31].getY();
    }
}
