package com.hollowstring.airengine.object;

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

    private Material material;
    private float[] objectMesh;
    public float x, y, z;
    public float sizeX, sizeY, sizeZ;
    public float rotX, rotY, rotZ;

    public Object(float[] mesh, Material mat){
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
        this.material.activate();
    }
    public Material getMaterial(){
        return material;
    }
    public float[] getMesh(){
        return objectMesh;
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
}
