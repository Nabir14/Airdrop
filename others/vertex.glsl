#version 330 core
layout (location = 0) in vec3 attribPos;

void main(){
    gl_Position = vec4(attribPos.x, attribPos.y, attribPos.z, 1.0);
}