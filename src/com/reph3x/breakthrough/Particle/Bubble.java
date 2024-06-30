package com.reph3x.breakthrough.Particle;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Entity.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;

public class Bubble extends Particle
{
	public float minSize;
	public float maxSize;
	
	public Bubble(Vector2 pos, float minSize, float maxSize, float timer, Color color) {
		super(pos, minSize, timer, color);
		this.minSize = minSize;
		this.maxSize = maxSize;
	}
	public Bubble(Vector2 pos, Color color) {
		this(pos, 15, 25, 10, color);
	}

	@Override
	public void update()
	{
		super.update();
		size = minSize + (maxSize-minSize)*(currentTime/timer);
	}

	@Override
	public void render(ShapeRenderer render)
	{
		render.begin(ShapeRenderer.ShapeType.Line);
		render.setColor(color);
		render.circle(pos.x, pos.y, size);
		render.end();
	}
	
	
	
	
}
