package com.hollowstring.airengine.material;

import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.opengl.*;

public class Material {
    public int vertexShader, fragmentShader;
    public int shaderProgram;

    public Material(){
        GL.createCapabilities();
        this.shaderProgram = GL20.glCreateProgram();
    }
    public void setVertexShader(String vS){
        Path shaderPath = Path.of(vS);
        String shaderSrc = null;
        try{
            shaderSrc = Files.readString(shaderPath);
        }catch(IOException e){
            e.printStackTrace();
        }
        vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, shaderSrc);
        GL20.glCompileShader(vertexShader);
        GL20.glAttachShader(shaderProgram, vertexShader);
        GL20.glLinkProgram(shaderProgram);
    }
    public void setFragmentShader(String fS){
        Path shaderPath = Path.of(fS);
        String shaderSrc = null;
        try{
            shaderSrc = Files.readString(shaderPath);
        }catch(IOException e){
            e.printStackTrace();
        }
        fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader, shaderSrc);
        GL20.glCompileShader(fragmentShader);
        GL20.glAttachShader(shaderProgram, fragmentShader);
        GL20.glLinkProgram(shaderProgram);
    }
    public void activate(){
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(0);
        GL20.glUseProgram(shaderProgram);
    }
}
