package com.hollowstring.airengine.scene;

import org.lwjgl.opengl.*;

import com.hollowstring.airengine.camera.Camera;
import com.hollowstring.airengine.object.Object;

public class Scene {
    private float cR, cG, cB;
    private Camera activeCamera;
    private Object[] objectPoll;
    private int totalObjects = 0;

    public Scene(int maxObjects){
        objectPoll = new Object[maxObjects];
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
    
    public void setClearColor(float cR, float cG, float cB){
        this.cR = cR;
        this.cG = cG;
        this.cB = cB;
    }

    public void render(){
        GL11.glClearColor(cR, cG, cB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        for(int i = 0; i < objectPoll.length; i++){
            if(objectPoll[i] != null){
                if(!(objectPoll[i].isHidden)){
                    objectPoll[i]._process();
                    GL20.glUseProgram(objectPoll[i].getMaterial().getShaderProgram());
                    if(objectPoll[i].getMaterial().getTexture() != 0){
                        GL11.glBindTexture(GL11.GL_TEXTURE_2D, objectPoll[i].getMaterial().getTexture());
                    }
                    GL30.glBindVertexArray(objectPoll[i].getVAO());
                    GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, objectPoll[i].getMesh().length / 5);
                }
            }
        }
    }

}
