package com.hollowstring.airengine.material;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import org.lwjgl.opengl.*;

import com.hollowstring.airengine.texture.*;

public class Material {
    private int vertexShader, fragmentShader;
    private String vertexShaderSource, fragmentShaderSource;
    private int shaderProgram;
    private int texture;

    public int getShaderProgram() {
        return shaderProgram;
    }
    public Material(){
        this.shaderProgram = GL20.glCreateProgram();
    }
    public void setTexture(Texture texture) {
        this.texture = texture.getTexture();
    }
    public int getTexture() {
        return texture;
    }
    public void setVertexShader(String vS){
        Path shaderPath = Path.of(vS);
        try{
            vertexShaderSource = Files.readString(shaderPath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setFragmentShader(String fS){
        Path shaderPath = Path.of(fS);
        try{
            fragmentShaderSource = Files.readString(shaderPath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void _compile(){
        vertexShader = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        GL20.glShaderSource(vertexShader, vertexShaderSource);
        GL20.glCompileShader(vertexShader);
        if(GL20.glGetShaderi(vertexShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println("ERROR: "+GL20.glGetShaderInfoLog(vertexShader));
        }else{
            System.out.println("LOG: Vertex Shader Compiled Successfully");
        }
        GL20.glAttachShader(shaderProgram, vertexShader);

        fragmentShader = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        GL20.glShaderSource(fragmentShader, fragmentShaderSource);
        GL20.glCompileShader(fragmentShader);
        if(GL20.glGetShaderi(fragmentShader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.err.println("ERROR: "+GL20.glGetShaderInfoLog(fragmentShader));
        }else{
            System.out.println("LOG: Fragment Shader Compiled Successfully");
        }
        GL20.glAttachShader(shaderProgram, fragmentShader);

        GL20.glLinkProgram(shaderProgram);
        if(GL20.glGetProgrami(shaderProgram, GL20.GL_LINK_STATUS) == GL11.GL_FALSE){
            System.err.println("ERROR: "+GL20.glGetProgramInfoLog(shaderProgram));
        }else{
            System.out.println("LOG: Shader Program Linked Successfully");
        }
    }
    public void _activate(){
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL20.glEnableVertexAttribArray(0);
    }
}
