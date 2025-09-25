package src.com.hollowstring.airengine;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengles.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.joml.*;

public class airengine {
    public static long window;
    public static void init(){
        GLFW.glfwInit();
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLES32.GL_TRUE);
    }
    public static void createWindow(int width, int height, String title){
        // String dosen't terminate properly 
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuffer t = utf8.encode(title);
        window = GLFW.glfwCreateWindow(width, height, t, MemoryUtil.NULL, MemoryUtil.NULL);
        GLFW.glfwMakeContextCurrent(window);
        GLES32.glViewport(0, 0, width, height);
    }
    public static void loopDefault(){
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }
    public static void close(){
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwTerminate();
    }
}
