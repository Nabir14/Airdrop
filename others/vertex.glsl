#version 330 core
layout (location = 0) in vec3 attribPos;
layout (location = 1) in vec2 attribUV;
out vec2 UV;

uniform mat4 transform;
uniform mat4 cameraTransform;
uniform mat4 projection;

void main(){
    gl_Position = projection* cameraTransform* transform* vec4(attribPos, 1.0);
    UV = attribUV;
}