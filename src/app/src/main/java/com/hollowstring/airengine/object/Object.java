package com.hollowstring.airengine.object;

import org.lwjgl.opengl.*;

import org.joml.Vector3f;
import com.hollowstring.airengine.material.Material;

public class Object {
    public boolean isHidden;
    private int VAO, VBO;
    private Material material;
    private float[] objectMesh;
    public Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f rotation = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f scale = new Vector3f(1.0f, 1.0f, 1.0f);
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
    public void setPosition(float x, float y, float z){
        position.x = x;
        position.y = y;
        position.z = z;
    }
    public void setRot(float x, float y, float z){
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }
    public void setSize(float x, float y, float z){
        scale.x = x;
        scale.y = y;
        scale.z = z;
    }
    public void _process(){
        GL30.glBindVertexArray(VAO);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, objectMesh, GL15.GL_STATIC_DRAW);
        material._activate();
    }
}