package com.hollowstring.airengine.material;

import java.io.*;

import org.lwjgl.opengl.*;

import com.hollowstring.airengine.texture.*;

public class Material {
    private int vertexShader, fragmentShader;
    private String vertexShaderSource, fragmentShaderSource;
    private int shaderProgram;
    private int[] texture;
    private int totalTextures;
    public Material(){
        this.texture = new int[GL11.glGetInteger(GL20.GL_MAX_TEXTURE_IMAGE_UNITS)];
    }
    public int getShaderProgram() {
        return shaderProgram;
    }
    public void setTexture(Texture texture) {
        this.texture[totalTextures]= texture.getTexture();
        totalTextures++;
    }
    public int[] getTexture() {
        return texture;
    }
    public void setVertexShader(String vS){
        try{
            vertexShaderSource = new String(getClass().getClassLoader().getResourceAsStream(vS).readAllBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setFragmentShader(String fS){
        try{
            fragmentShaderSource = new String(getClass().getClassLoader().getResourceAsStream(fS).readAllBytes());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void _compile(){
        shaderProgram = GL20.glCreateProgram();
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
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 5 * Float.BYTES, 0);
        GL20.glEnableVertexAttribArray(0);
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        GL20.glEnableVertexAttribArray(1);
    }
}
