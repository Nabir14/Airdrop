package com.hollowstring.airengine.camera;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Camera {
    public Vector3f position = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector4f rotation = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    public Matrix4f projection = null;
    public Camera(float FOV, int width, int height, float Near, float RenderDistance){
        projection = new Matrix4f().perspective((float) Math.toRadians(FOV), (float) width / height, Near, RenderDistance);
    }
    public Vector3f getTarget(){
        Vector3f direction = new Vector3f(0.0f, 0.0f, 0.0f);
        direction.x = (float) ((Math.cos(Math.toRadians(rotation.y)) * Math.cos(Math.toRadians(rotation.x))));
        direction.y = (float) Math.sin(Math.toRadians(rotation.x));
        direction.z = (float) ((Math.sin(Math.toRadians(rotation.y)) * Math.cos(Math.toRadians(rotation.x))));
        direction.normalize();
        return (position.add(direction));
    }
    public Vector3f getPosition() {
        return position;
    }
    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }
}
