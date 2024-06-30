package com.reph3x.breakthrough.Particle;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.Help.*;
import com.reph3x.breakthrough.World.*;

public class ParticleEmitter
{
	public World world;
	
	public ParticleEmitter(World world) {
		this.world = world;
	}
	
	public void sprayCone(Vector2 origin, Vector2 target, int numParticles, int degrees, float size, float speed, float timer, Color color) {
		for(int i = 0; i < numParticles; i++) {
			Vector2 vel = new Vector2(QuickMaths.getRandomConeAngle(origin, target, degrees).scl(speed));
			world.addParticle(origin, vel, size, timer, color, true, QuickMaths.getRandomDegen());
		}
	}
	
	public void sprayCircle(Vector2 origin, int numParticles, float size, float cSize, float speed, float timer, Color color) {
		for(int i = 0; i < numParticles; i++) {
			Vector2 pos = new Vector2(QuickMaths.getRandomPointC(origin, cSize));
			Vector2 vel = new Vector2(pos);
			vel.sub(origin).nor().scl(speed);
			world.addParticle(pos, vel, size, timer, color, true, QuickMaths.getRandomDegen());
		}
	}
	
	public void sprayInwardCircle(Vector2 origin, int numParticles, float size, float cSize, float speed, float timer, Color color) {
		for(int i = 0; i < numParticles; i++) {
			Vector2 pos = new Vector2(QuickMaths.getRandomPointC(origin, cSize));
			Vector2 vel = new Vector2(pos);
			vel.sub(origin).nor().scl(-speed);
			world.addParticle(pos, vel, size, timer, color, true, QuickMaths.getRandomDegen());
		}
	}
	
	public void puddle(Vector2 origin, float size, float timer, Color color) {
		Puddle puddle = new Puddle(origin, size, 10, timer, color);
		world.queueUpdate(puddle);
	}

	
	
}
