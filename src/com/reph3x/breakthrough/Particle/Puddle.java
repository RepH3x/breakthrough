package com.reph3x.breakthrough.Particle;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;

public class Puddle extends StagedParticle
{
	public float stageSize;
	
	public Puddle(Vector2 pos, float size, float stageSize, float timer, Color color) {
		super(pos, size, timer, 5, color);
		this.stageSize = stageSize;
	}

	@Override
	public void stage()
	{
		this.size += stageSize;
	}
	
	
}
