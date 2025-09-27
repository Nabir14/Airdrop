package com.hollowstring.airengine.material;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.opengl.*;

public class Material {
    private int vertexShader, fragmentShader;
    private int shaderProgram;

    public int getShaderProgram() {
        return shaderProgram;
    }
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
        if(!(GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)){
            System.err.println("ERROR: "+GL20.glGetShaderInfoLog(vertexShader));
        }else{
            System.out.println("LOG: Vertex Shader Compiled Successfully");
        }
        GL20.glAttachShader(shaderProgram, vertexShader);
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
        if(!(GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)){
            System.err.println("ERROR: "+GL20.glGetShaderInfoLog(fragmentShader));
        }else{
            System.out.println("LOG: Fragment Shader Compiled Successfully");
        }
        GL20.glAttachShader(shaderProgram, fragmentShader);
    }
    public void activate(){
        GL20.glLinkProgram(shaderProgram);
        if(!(GL20.glGetProgrami(shaderProgram, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)){
            System.out.println("LOG: Shader Program Linked Successfully");
        }else{
            System.err.println("ERROR: "+GL20.glGetProgramInfoLog(shaderProgram));
        }
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(0);
    }
}
