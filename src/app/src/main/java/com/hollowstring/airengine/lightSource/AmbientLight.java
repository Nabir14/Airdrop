package com.hollowstring.airengine.lightSource;

import com.hollowstring.airengine.object.Object;
import java.util.ArrayList;
import org.lwjgl.opengl.*;

public class AmbientLight {
    public float r, g, b;
    public float lightEnergy;
    ArrayList<Object> objectPoll;
    public AmbientLight(float r, float g, float b, float lightEnergy){
        this.r = r;
        this.g = g;
        this.b = b;
        this.lightEnergy = lightEnergy;
        objectPoll = new ArrayList<Object>();
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
            GL20.glUniform3f(GL20.glGetUniformLocation(objectPoll.get(i).getMaterial().getShaderProgram(), "ambientLightColor"), r * lightEnergy, g * lightEnergy, b * lightEnergy);
        }
    }
}
