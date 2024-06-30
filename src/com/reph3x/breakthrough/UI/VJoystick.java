package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.reph3x.breakthrough.Entity.Player.*;

public class VJoystick
{
	public Vector2 pos;
	public float size;
	public VJoystickReticle reticle;
	public Player player;
	public Color color;
	public OrthographicCamera camera;
	
	public VJoystick(float x, float y, float size, float reticleSize, OrthographicCamera camera, Player player) {
		this.pos = new Vector2(x, y);
		this.size = size;
		this.reticle = new VJoystickReticle((pos.x + size / 2), (pos.y + size / 2), reticleSize, this);
		this.player = player;
		this.color = new Color(0.5f, 0.5f, 0.5f, 0.25f);
		this.camera = camera;
	}
	
	public void update() {
		reticle.update();
		controlPlayer();
	}
	
	public void render(ShapeRenderer render) {
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		render.rect(pos.x, pos.y, size, size);
		render.end();
		reticle.render(render);
		update();
	}
	
	public void controlPlayer() {
		float playerDecel = 0.9f;
		if(reticle.isActive) {
			Vector2 mouse = new Vector2(reticle.pos);
			mouse.sub(pos.x + size /2, pos.y + size / 2).scl(0.2f).clamp(0, player.currentSpeed);
			player.vel.set(mouse);
		} else {
			player.vel.scl(playerDecel);
		}
	}
}
