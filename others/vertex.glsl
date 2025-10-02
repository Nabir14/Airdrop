#version 330 core
layout (location = 0) in vec3 attribPos;
layout (location = 1) in vec2 attribUV;
out vec2 UV;

uniform mat4 transform;

void main(){
    gl_Position = transform * vec4(attribPos, 1.0);
    UV = attribUV;
}