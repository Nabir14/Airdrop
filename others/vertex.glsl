#version 330 core
layout (location = 0) in vec3 attribPos;
layout (location = 1) in vec2 attribUV;
out vec2 UV;

void main(){
    gl_Position = vec4(attribPos, 1.0);
    UV = attribUV;
}