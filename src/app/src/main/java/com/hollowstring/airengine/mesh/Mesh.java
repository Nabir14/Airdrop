package com.hollowstring.airengine.mesh;

public class Mesh {
    public static float[] triangleMesh = {
        -0.5f, -0.5f, 0.0f, 0.0f, 0.0f,
        0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
        0.0f,  0.5f, 0.0f, 0.5f, 1.0f
    };
    public static int[] triangleIndex = {
        0, 1, 2
    };
    public static float[] squareMesh = {
        -0.5f, -0.5f, 0.0f, 0.0f, 0.0f,
        0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
        -0.5f,  0.5f, 0.0f, 0.0f, 1.0f,
        0.5f, 0.5f, 0.0f, 1.0f, 1.0f
    };
    public static int[] squareIndex = {
        0, 1, 2,
        1, 2, 3
    };
}
