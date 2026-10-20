#version 120

uniform sampler2D gcolor;

varying vec2 texcoord;

void main() {
	vec3 color = texture2D(gcolor, texcoord).rgb;

	// MyEdit: boost saturation and warm the image up a little
	float luma = dot(color, vec3(0.299, 0.587, 0.114));
	color = mix(vec3(luma), color, 1.8);
	color *= vec3(1.05, 1.0, 0.92);

/* DRAWBUFFERS:0 */
	gl_FragData[0] = vec4(color, 1.0); //gcolor
}
