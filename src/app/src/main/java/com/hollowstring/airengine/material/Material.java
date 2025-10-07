package com.hollowstring.airengine.material;

import java.io.*;

import org.joml.*;
import org.lwjgl.opengl.*;

import com.hollowstring.airengine.texture.*;

public class Material {
    private int vertexShader, fragmentShader;
    private String vertexShaderSource, fragmentShaderSource;
    private int shaderProgram;
    private int[] texture;
    private String[] textureIds;
    private int totalTextures;
    public Material(){
        this.texture = new int[GL11.glGetInteger(GL20.GL_MAX_TEXTURE_IMAGE_UNITS)];
        this.textureIds = new String[GL11.glGetInteger(GL20.GL_MAX_TEXTURE_IMAGE_UNITS)];
        this.totalTextures = 0;
    }
    public int getShaderProgram() {
        return shaderProgram;
    }
    public void setTexture(Texture texture, String textureId) {
        this.texture[totalTextures]= texture.getTexture();
        this.textureIds[totalTextures] = textureId;
        totalTextures++;
    }
    public int[] getTexture() {
        return this.texture;
    }
    public String[] getTextureIds() {
        return this.textureIds;
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
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 8 * Float.BYTES, 0);
        GL20.glEnableVertexAttribArray(0);
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        GL20.glEnableVertexAttribArray(1);
        GL20.glVertexAttribPointer(2, 3, GL11.GL_FLOAT, false, 8 * Float.BYTES, 5 * Float.BYTES);
        GL20.glEnableVertexAttribArray(2);
    }
    public void setUniformValue(String uniform, float v){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform1f(GL20.glGetUniformLocation(shaderProgram, uniform), v);
    }
    public void setUniformValue(String uniform, float v, float v1){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform2f(GL20.glGetUniformLocation(shaderProgram, uniform), v, v1);
    }
    public void setUniformValue(String uniform, float v, float v1, float v2){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform3f(GL20.glGetUniformLocation(shaderProgram, uniform), v1, v1, v2);
    }
    public void setUniformValue(String uniform, float v, float v1, float v2, float v3){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform4f(GL20.glGetUniformLocation(shaderProgram, uniform), v, v1, v2, v3);
    }
    public void setUniformValue(String uniform, int v){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform1i(GL20.glGetUniformLocation(shaderProgram, uniform), v);
    }
    public void setUniformValue(String uniform, int v, int v1){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform2i(GL20.glGetUniformLocation(shaderProgram, uniform), v, v1);
    }
    public void setUniformValue(String uniform, int v, int v1, int v2){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform3i(GL20.glGetUniformLocation(shaderProgram, uniform), v, v1, v2);
    }
    public void setUniformValue(String uniform, int v, int v1, int v2, int v3){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniform4i(GL20.glGetUniformLocation(shaderProgram, uniform), v, v1, v2, v3);
    }
    public void setUniformValue(String uniform, Matrix2f m){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniformMatrix2fv(GL20.glGetUniformLocation(shaderProgram, uniform), false, m.get(new float[4]));
    }
    public void setUniformValue(String uniform, Matrix3f m){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniformMatrix3fv(GL20.glGetUniformLocation(shaderProgram, uniform), false, m.get(new float[9]));
    }
    public void setUniformValue(String uniform, Matrix4f m){
        GL20.glUseProgram(shaderProgram);
        GL20.glUniformMatrix4fv(GL20.glGetUniformLocation(shaderProgram, uniform), false, m.get(new float[16]));
    }
}
