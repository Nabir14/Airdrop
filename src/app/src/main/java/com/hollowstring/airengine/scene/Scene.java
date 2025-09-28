package com.hollowstring.airengine.scene;

import org.lwjgl.opengl.*;

import com.hollowstring.airengine.camera.Camera;
import com.hollowstring.airengine.object.Object;

public class Scene {
    private Camera activeCamera;
    private Object[] objectPoll;
    private int totalObjects = 0;

    public Scene(int maxObjects){
        objectPoll = new Object[maxObjects];
        GL.createCapabilities();
    }

    public Camera getActiveCamera() {
        return activeCamera;
    }

    public void setActiveCamera(Camera activeCamera) {
        this.activeCamera = activeCamera;
    }

    public void appendObject(Object obj){
        objectPoll[totalObjects] = obj;
        totalObjects++;
    }

    public void processObjects(){
        for(int i = 0; i < objectPoll.length; i++){
            if(!(objectPoll[i] == null)){
                objectPoll[i].process();
            }
        }
    }

    public void render(){
        for(int i = 0; i < objectPoll.length; i++){
            if(!(objectPoll[i] == null)){
                if(!(objectPoll[i].isHidden)){
                    GL20.glUseProgram(objectPoll[i].getMaterial().getShaderProgram());
                    GL30.glBindVertexArray(objectPoll[i].getVAO());
                    GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
                }
            }
        }
    }

}
