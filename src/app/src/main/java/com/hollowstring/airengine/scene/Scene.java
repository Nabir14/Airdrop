package com.hollowstring.airengine.scene;

import org.joml.Matrix4f;
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
    public void process(){
        for(int i = 0; i < objectPoll.length; i++){
            if(objectPoll[i] != null){
                if(!(objectPoll[i].isHidden)){
                    objectPoll[i]._process();
                }
            }
        }
    }
    public void render(){
        GL11.glClearColor(cR, cG, cB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        for(int i = 0; i < objectPoll.length; i++){
            if(objectPoll[i] != null){
                if(!(objectPoll[i].isHidden)){
                    GL20.glUseProgram(objectPoll[i].getMaterial().getShaderProgram());
                    if(objectPoll[i].getMaterial().getTexture() != 0){
                        GL11.glBindTexture(GL11.GL_TEXTURE_2D, objectPoll[i].getMaterial().getTexture());
                    }
                    Matrix4f objectTranslation = new Matrix4f().identity();
                    objectTranslation.translate(objectPoll[i].position.x, objectPoll[i].position.y, objectPoll[i].position.z);
                    objectTranslation.rotateX((float)Math.toRadians(objectPoll[i].rotation.x));
                    objectTranslation.rotateY((float)Math.toRadians(objectPoll[i].rotation.y));
                    objectTranslation.rotateZ((float)Math.toRadians(objectPoll[i].rotation.z));
                    objectTranslation.scale(objectPoll[i].scale.x, objectPoll[i].scale.y, objectPoll[i].scale.z);
                    int transformLocation = GL20.glGetUniformLocation(objectPoll[i].getMaterial().getShaderProgram(), "transform");
                    GL20.glUniformMatrix4fv(transformLocation, false, objectTranslation.get(new float[16]));
                    GL30.glBindVertexArray(objectPoll[i].getVAO());
                    GL11.glDrawElements(GL11.GL_TRIANGLES, (objectPoll[i].getMesh().length / 5) + (objectPoll[i].getMeshIndex().length / 3), GL11.GL_UNSIGNED_INT, 0);
                }
            }
        }
    }

}
