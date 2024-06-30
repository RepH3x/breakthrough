package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;

public class VJoystickReticle
{
	public Vector2 pos;
	public Vector2 vel;
	public float size;
	public float speed;
	public VJoystick joystick;
	public Color color;
	public boolean isActive;
	
	public VJoystickReticle(float x, float y, float size, float speed, VJoystick joystick, Color color){
		this.pos = new Vector2(x, y);
		this.vel = new Vector2();
		this.size = size;
		this.speed = speed;
		this.joystick = joystick;
		this.color = color;
		isActive = false;
	}
	
	public VJoystickReticle(float x, float y, float size, VJoystick joystick) {
		this(x, y, size, 25, joystick, new Color(0.1f, 0.1f, 0.1f, 0.75f));
	}
	
	public void activate() {
		if(Gdx.input.justTouched()) {
			Vector3 mouse = new Vector3(Gdx.input.getX(0), Gdx.input.getY(0), 0);
			joystick.camera.unproject(mouse);
			if(mouse.x >= joystick.pos.x - joystick.size && mouse.x <= joystick.pos.x + joystick.size) {
				if(mouse.y >= joystick.pos.y - joystick.size && mouse.y <= joystick.pos.y + joystick.size) {
					isActive = true;
				}
			}
		}
		if(!Gdx.input.isTouched(0))
			isActive = false;
	}
	
	public void update() {
		activate();
		if(isActive) {
			followTouch();
			stayInBounds();
			pos.add(vel);
			vel.setZero();
		} else {
			pos.set((joystick.pos.x + joystick.size / 2), (joystick.pos.y + joystick.size / 2));
		}
	}
	
	public void render(ShapeRenderer render) {
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		render.circle(pos.x, pos.y, size);
		render.end();
	}
	
	public void followTouch() {
		Vector3 mouse = new Vector3(Gdx.input.getX(0), Gdx.input.getY(0), 0);
		joystick.camera.unproject(mouse);
		Vector2 mouse2 = new Vector2(mouse.x, mouse.y);
		mouse2.sub(pos).clamp(0, speed);
		vel.set(mouse2);
		
	}
	
	public void stayInBounds() {
		if(pos.x + vel.x > joystick.pos.x + joystick.size) {
			pos.set(joystick.pos.x + joystick.size, pos.y);
			vel.set(0, vel.y);
		}
		if(pos.x + vel.x < joystick.pos.x) {
			pos.set(joystick.pos.x, pos.y);
			vel.set(0, vel.y);
		}
		if(pos. y + vel.y > joystick.pos.y + joystick.size) {
			pos.set(pos.x, joystick.pos.y + joystick.size);
			vel.set(vel.x, 0);
		}
		if(pos.y + vel.y < joystick.pos.y) {
			pos.set(pos.x, joystick.pos.y);
			vel.set(vel.x, 0);
		}
	}
}
