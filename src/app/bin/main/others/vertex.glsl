#version 330 core
layout (location = 0) in vec3 attribPos;
layout (location = 1) in vec2 attribUV;
layout (location = 2) in vec3 attribNormal;
out vec2 UV;
out vec3 Normal;
out vec3 FragPos;

uniform mat4 transform;
uniform mat4 cameraTransform;
uniform mat4 projection;

void main(){
    gl_Position = projection * cameraTransform * transform * vec4(attribPos, 1.0);
    UV = attribUV;
    Normal= mat3(transpose(inverse(transform))) * attribNormal;
    FragPos= vec3(transform * vec4(attribPos, 1.0));
}