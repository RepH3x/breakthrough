package com.reph3x.breakthrough.Effect;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Entity.*;

public class EffectDoT extends Effect
{
	public float damage;
	
	public EffectDoT(Entity entity, float level, float ticks, float timer, float damage) {
		super(entity, 0, level, ticks, timer);
		this.timer = timer;
		this.ticks = ticks;
		currentTimer = 0;
		this.damage = damage;
	}

	@Override
	public void causeEffect()
	{
		entity.takeDamage(damage);
	}

	@Override
public EffectDoT clone()
{
	return new EffectDoT(entity, level, ticks, timer, damage);
}
	
	
}
