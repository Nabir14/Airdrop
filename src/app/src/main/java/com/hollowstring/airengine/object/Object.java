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
        -0.5f,  0.5f, 0.0f
    };
    public boolean isHidden;
    private int VAO, VBO;
    private Material material;
    private float[] objectMesh;
    public float x, y, z;
    public float sizeX, sizeY, sizeZ;
    public float rotX, rotY, rotZ;

    public Object(float[] mesh, Material mat){
        this.isHidden = false;
        this.objectMesh = mesh;
        this.material = mat;
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.sizeX = 1.0f;
        this.sizeY = 1.0f;
        this.sizeZ = 1.0f;
        this.rotX = 0.0f;
        this.rotY = 0.0f;
        this.rotZ = 0.0f;
        GL.createCapabilities();
        this.VBO = GL15.glGenBuffers();
        this.VAO = GL30.glGenVertexArrays();
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
    public void setPos(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void setRot(float x, float y, float z){
        this.rotX = x;
        this.rotY = y;
        this.rotZ = z;
    }
    public void setSize(float x, float y, float z){
        this.sizeX = x;
        this.sizeY = y;
        this.sizeZ = z;
    }
    public void process(){
        GL30.glBindVertexArray(VAO);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, objectMesh, GL15.GL_STATIC_DRAW);
        material.activate();
    }
}
