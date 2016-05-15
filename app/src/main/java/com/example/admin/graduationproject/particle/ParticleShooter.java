package com.example.admin.graduationproject.particle;



import com.example.admin.graduationproject.geometry.Point;
import com.example.admin.graduationproject.geometry.Vector;

import java.util.Random;

import static android.opengl.Matrix.multiplyMV;
import static android.opengl.Matrix.setRotateEulerM;

public class ParticleShooter {
	private final Point position;
	private final int color;
	
	private final float angleVariance;
	private final float speedVariance;
	
	private final Random random = new Random();
	
	private float[] rotationMatrix = new float[16];
	private float[] directionVector = new float[4];
	private float[] resultVector = new float[4];
	
	public ParticleShooter(Point position, Vector direction, int color,
						   float angleVariance, float speedVariance){
		this.position = position;
		this.color = color;
		
		this.angleVariance = angleVariance;
		this.speedVariance = speedVariance;
		
		directionVector[0] = direction.x;
		directionVector[1] = direction.y;
		directionVector[2] = direction.z;
	}
	
	public void addParticles(ParticleSystem particleSystem, float currentTime, int count){
		for(int i = 0; i < count; i++){
			
			//��ת����
			setRotateEulerM(rotationMatrix, 0,
					(random.nextFloat() - 0.5f)*angleVariance,
					(random.nextFloat() - 0.5f)*angleVariance,
					(random.nextFloat() - 0.5f)*angleVariance);
			
			//�õ���ת������
			multiplyMV(
					resultVector,0,
					rotationMatrix,0,
					directionVector,0);
			
			float speedAdjustment = 1f + random.nextFloat()*speedVariance;
			
			Vector thisDirection = new Vector(
					resultVector[0]*speedAdjustment, 
					resultVector[1]*speedAdjustment, 
					resultVector[2]*speedAdjustment);
			
			particleSystem.addParticle(position, color, thisDirection, currentTime);
		}
	}
}
