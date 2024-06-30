package com.reph3x.breakthrough.Effect;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Entity.*;

public class EffectSlow extends Effect
{
	
	public float slow;
	public float entityOriginalSpeed;
	
	public EffectSlow(Entity entity, float level, float ticks, float timer, float slow) {
		super(entity, 1, level, ticks, timer);
		this.slow = slow;
	}

	@Override
	public void causeEffect()
	{
		entity.currentSpeed = entity.speed * (1 - (slow/100));
	}

	@Override
	public void onDeath()
	{
		super.onDeath();
		entity.currentSpeed = entity.speed;
	}

	@Override
	public void setEntity(Entity entity)
	{
		super.setEntity(entity);
	}

	@Override
	public EffectSlow clone()
	{
		return new EffectSlow(entity, level, ticks, timer, slow);
	}
	
	
}
