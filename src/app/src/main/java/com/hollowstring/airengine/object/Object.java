package com.hollowstring.airengine.object;

import org.lwjgl.opengl.*;
import com.hollowstring.airengine.material.Material;

public class Object {
    public static float[] triangleMesh = {
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f,
         0.0f,  0.5f, 0.0f
    };
    public static float[] squareMesh = {
        -0.5f, -0.5f, 0.0f,
        0.5f, -0.5f, 0.0f,
        0.5f,  0.5f, 0.0f,
        -0.5f, -0.5f, 0.0f,
        -0.5f,  0.5f, 0.0f,
        0.5f, 0.5f, 0.0f,
    };
    public boolean isHidden;
    private int VAO, VBO;
    private Material material;
    private float[] objectMesh;

    public Object(float[] mesh, Material mat){
        this.isHidden = false;
        this.objectMesh = mesh;
        this.material = mat;
        this.VBO = GL15.glGenBuffers();
        this.VAO = GL30.glGenVertexArrays();
        material._compile();
    }
    public Material getMaterial(){
        return material;
    }
    public int getVAO() {
        return VAO;
    }
    public int getVBO() {
        return VBO;
    }
    public float[] getMesh(){
        return objectMesh;
    }
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
    public boolean isHidden() {
        return isHidden;
    }
    public void updatePosition(float x, float y, float z){
        for(int i = 0; i < objectMesh.length; i++){
            if(i * 3 < objectMesh.length){
                objectMesh[3* i] += x;
                objectMesh[3* i+1] += y;
                objectMesh[3* i+2] += z;
            }else{
                break;
            }
        }
    }
    public void setRot(float x, float y, float z){
        // Not Implemented
    }
    public void updateSize(float x, float y, float z){
        for(int i = 0; i < objectMesh.length; i++){
            if(i * 3 < objectMesh.length){
                objectMesh[3* i] *= x;
                objectMesh[3* i+1] *= y;
                objectMesh[3* i+2] *= z;
            }else{
                break;
            }
        }
    }
    public void _process(){
        GL30.glBindVertexArray(VAO);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, objectMesh, GL15.GL_STATIC_DRAW);
        material._activate();
    }
}
