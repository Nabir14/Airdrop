package com.hollowstring.airengine.scene;

import org.lwjgl.opengl.*;

import com.hollowstring.airengine.camera.Camera;
import com.hollowstring.airengine.object.Object;

public class Scene {
    private Camera activeCamera;
    private Object[] objectPoll;
    private int VBO, VAO;

    public Scene(){
        GL.createCapabilities();
        VBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
    }

    public Camera getActiveCamera() {
        return activeCamera;
    }

    public void setActiveCamera(Camera activeCamera) {
        this.activeCamera = activeCamera;
    }

    public void appendObject(Object obj){
        if(objectPoll == null) {
            objectPoll = new Object[0];
            objectPoll[0] = obj;

        }else{
            objectPoll[objectPoll.length + 1] = obj;
        }
    }

    public void render(){
        for(int i = 0; i < objectPoll.length; i++){
            GL15.glBufferData(GL15.GL_ARRAY_BUFFER, objectPoll[i].getMesh(), GL15.GL_STATIC_DRAW);
        }
    }

}
