attribute vec4 a_position;
uniform mat4 u_matrix;
void main() {

gl_Position =u_matrix*a_position;
gl_PointSize =10.0;

}
