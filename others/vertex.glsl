#version 330 core
layout (location = 0) in vec3 attribPos;
out vec2 TexCoord;

void main(){
    gl_Position = vec4(attribPos, 1.0);
    TexCoord = attribPos.xy;
}