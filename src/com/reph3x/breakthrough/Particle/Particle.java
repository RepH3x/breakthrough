package com.reph3x.breakthrough.Particle;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;

public class Particle
{
	public Vector2 pos;
	public Vector2 vel;
	public float size;
	public float timer;
	public float currentTime;
	public float velScale;
	public boolean isDead;
	public boolean scaleVel;
	public Color color;
	
	public Particle(Vector2 pos, Vector2 vel, float size, float timer, Color color, boolean scaleVel, float velScale) {
		this.pos = new Vector2(pos);
		this.vel = vel;
		this.size = size;
		this.timer = timer;
		this.currentTime = 0;
		isDead = false;
		this.color = color;
		this.scaleVel = scaleVel;
		this.velScale = velScale;
	}
	public Particle(Vector2 pos, Vector2 vel, float size, float timer, Color color) {
		this(pos, vel, size, timer, color, false, 1);
	}
	public Particle(Vector2 pos, float size, float timer, Color color) {
		this(pos, new Vector2(), size, timer, color, false, 1);
	}
	
	public void update() {
		pos.add(vel);
		currentTime++;
		if(currentTime >= timer)
			isDead = true;
		if(scaleVel) {
			vel.scl(velScale);
		}
	}
	
	public void render(ShapeRenderer render) {
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		render.circle(pos.x, pos.y, size);
		render.end();
	}
}
