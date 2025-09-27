package com.hollowstring.airengine.scene;

import org.lwjgl.opengl.*;

import com.hollowstring.airengine.camera.Camera;
import com.hollowstring.airengine.object.Object;

public class Scene {
    private Camera activeCamera;
    private Object[] objectPoll;
    private int VBO, VAO;
    private int totalObjects = 0;

    public Scene(int maxObjects){
        objectPoll = new Object[maxObjects];
        GL.createCapabilities();
        VBO = GL15.glGenBuffers();
        VAO = GL30.glGenVertexArrays();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
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

    public void render(){
        for(int i = 0; i < objectPoll.length; i++){
            if(!(objectPoll[i] == null)){
                GL30.glBindVertexArray(VAO);
                GL15.glBufferData(GL15.GL_ARRAY_BUFFER, objectPoll[i].getMesh(), GL15.GL_STATIC_DRAW);
                objectPoll[i].getMaterial().activate();
                GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
            }
        }
    }

}
