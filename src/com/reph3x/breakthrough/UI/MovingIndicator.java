package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class MovingIndicator extends Indicator
{
	public Vector2 vel;
	public float timer;
	public float maxTimer;
	
	public MovingIndicator(Vector2 pos, Vector2 vel, String text, Color color, SpriteBatch batch, BitmapFont font) {
		super(pos, text, color, batch, font);
		this.vel = vel;
		this.timer = 0;
		this.maxTimer = 25;
	}
	
	@Override
	public void update() {
		timer++;
		pos.add(vel);
		
		if(timer > maxTimer) {
			isDead = true;
		}
	}
}
