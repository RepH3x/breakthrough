package com.reph3x.breakthrough.Particle;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;

public class StagedParticle extends Particle
{
	
	public int stages;
	
	public StagedParticle(Vector2 pos, float size, float timer, int stages, Color color) {
		super(pos, size, timer, color);
		this.stages = stages;
	}

	@Override
	public void update()
	{
		super.update();
		if(currentTime % (currentTime / stages) == 0) {
			stage();
		}
	}
	
	public void stage() {
		//do nothing
	}
	
}
