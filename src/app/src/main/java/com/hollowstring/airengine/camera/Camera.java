package com.hollowstring.airengine.camera;

import org.joml.Matrix4f;
import org.joml.Vector4f;

public class Camera {
    public Vector4f position = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    public Vector4f rotation = new Vector4f(0.0f, 0.0f, 0.0f, 1.0f);
    public Matrix4f projection = null;
    public Camera(float FOV, int width, int height, float Near, float RenderDistance){
        projection = new Matrix4f().perspective((float) Math.toRadians(FOV), (float) width / height, Near, RenderDistance);
    }
}
