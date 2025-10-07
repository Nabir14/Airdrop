package com.hollowstring.airengine.scene;

import org.joml.Matrix4f;
import org.joml.Vector3f;
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

    public void processObjects(){
        for(int i = 0; i < objectPoll.length; i++){
            if(objectPoll[i] == null){
                break;
            }
            if(objectPoll[i].isHidden){
                continue;
            }
            objectPoll[i]._process();
        }
    }
    public void render(){
        GL11.glClearColor(cR, cG, cB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        for(int i = 0; i < objectPoll.length; i++){
            if(objectPoll[i] == null){
                break;
            }
            if(objectPoll[i].isHidden){
                continue;
            }
            GL20.glUseProgram(objectPoll[i].getMaterial().getShaderProgram());
            if(objectPoll[i].getMaterial().getTexture().length != 0){
                for(int j = 0; j < objectPoll[i].getMaterial().getTexture().length; j++){
                    if(objectPoll[i].getMaterial().getTexture()[j] == 0){
                        break;
                    }
                    GL13.glActiveTexture(GL13.GL_TEXTURE0+j);
                    GL11.glBindTexture(GL11.GL_TEXTURE_2D, objectPoll[i].getMaterial().getTexture()[j]);
                    GL20.glUniform1i(GL20.glGetUniformLocation(objectPoll[i].getMaterial().getShaderProgram(), objectPoll[i].getMaterial().getTextureIds()[j]), j);
                }
            }
            Matrix4f objectTranslation = new Matrix4f().identity();
            objectTranslation.translate(objectPoll[i].position);
            objectTranslation.rotateX((float)Math.toRadians(objectPoll[i].rotation.x));
            objectTranslation.rotateY((float)Math.toRadians(objectPoll[i].rotation.y));
            objectTranslation.rotateZ((float)Math.toRadians(objectPoll[i].rotation.z));
            objectTranslation.scale(objectPoll[i].scale);
            Matrix4f cameraTranslation = new Matrix4f().identity();
            cameraTranslation.lookAt(activeCamera.position, activeCamera.getTarget(), new Vector3f(0.0f, 1.0f, 0.0f));
            int transformLocation = GL20.glGetUniformLocation(objectPoll[i].getMaterial().getShaderProgram(), "transform");
            int cameraTransformLocation = GL20.glGetUniformLocation(objectPoll[i].getMaterial().getShaderProgram(), "cameraTransform");
            int projectionLocation = GL20.glGetUniformLocation(objectPoll[i].getMaterial().getShaderProgram(), "projection");
            GL20.glUniformMatrix4fv(transformLocation, false, objectTranslation.get(new float[16]));
            GL20.glUniformMatrix4fv(cameraTransformLocation, false, cameraTranslation.get(new float[16]));
            GL20.glUniformMatrix4fv(projectionLocation, false, activeCamera.projection.get(new float[16]));
            GL30.glBindVertexArray(objectPoll[i].getVAO());
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, objectPoll[i].getMesh().length / 8);
        }
    }

}
