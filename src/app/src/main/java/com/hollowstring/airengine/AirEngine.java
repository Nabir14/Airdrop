package com.hollowstring.airengine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class AirEngine {
    public static final String AirEngineVersion = "0.1a";
    private long window;
    private int width, height;
    private String title;

    public AirEngine(int w, int h, String t){
        this.width = w;
        this.height = h;
        this.title = t;
        GLFW.glfwInit();
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL11.GL_TRUE);
    }

    public long getWindow(){
        return window;
    }

    public static String getVersion(){
        return (
            "AirEngine Version: " + AirEngineVersion + "\n" +
            "LWJGL Version: " + Version.getVersion() + "\n"
        );
    }

    public void createWindow(){
        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);
        GL.createCapabilities();
        GL11.glViewport(0, 0, width, height);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void processDefault(){
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    public boolean checkKey(int keyKey, int checkKey){
        return (GLFW.glfwGetKey(window, keyKey) == checkKey);
    }

    public void setDrawMode(int mode){
        GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, mode);
    }
    
    public void close(){
        GLFW.glfwWindowShouldClose(this.window);
        GLFW.glfwTerminate();
    }
}
