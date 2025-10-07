package com.hollowstring.airengine.lightSource;

import com.hollowstring.airengine.object.Object;
import java.util.ArrayList;

import org.joml.Vector3f;
import org.lwjgl.opengl.*;

public class PointLight {
    public Vector3f position;
    public float r, g, b;
    public float lightEnergy;
    ArrayList<Object> objectPoll;
    public PointLight(float r, float g, float b, float lightEnergy){
        this.position = new Vector3f(0.0f, 0.0f, 0.0f);
        this.r = r;
        this.g = g;
        this.b = b;
        this.lightEnergy = lightEnergy;
        objectPoll = new ArrayList<Object>();
    }
    public Vector3f getPosition() {
        return position;
    }
    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }
    public void appendObject(Object obj){
        objectPoll.add(obj);
    }
    public void applyLighting(){
        for(int i = 0; i < objectPoll.size(); i++){
            if(objectPoll.size() == 0){
                break;
            }
            GL20.glUseProgram(objectPoll.get(i).getMaterial().getShaderProgram());
            GL20.glUniform3f(GL20.glGetUniformLocation(objectPoll.get(i).getMaterial().getShaderProgram(), "pointLightColor"), r * lightEnergy, g * lightEnergy, b * lightEnergy);
            GL20.glUniform3f(GL20.glGetUniformLocation(objectPoll.get(i).getMaterial().getShaderProgram(), "pointLightPos"), position.x, position.y, position.z);
        }
    }
}
