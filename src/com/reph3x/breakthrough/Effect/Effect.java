package com.reph3x.breakthrough.Effect;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Entity.*;

public class Effect
{
	public Entity entity;
	public boolean isDead;
	public float id;
	public float level;
	public float ticks;
	public float timer;
	public float currentTimer;
	
	public Effect(Entity entity, float id, float level, float ticks, float timer) {
		this.entity = entity;
		isDead = false;
		this.id = id;
		this.level = level;
		this.ticks = ticks;
		this.timer = timer;
		this.currentTimer = 0;
	}
	
	public void update() {
		if(currentTimer >= timer) {
			causeEffect();
			currentTimer = 0;
			ticks--;
			if(ticks <= 0) {
				onDeath();
			}
		}
		currentTimer++;
		
	}
	
	public void causeEffect() {}
	
	public void onDeath() {
		isDead = true;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	//must be overridden for cloning child effects
	public Effect clone() {
		return new Effect(entity, id, level, ticks, timer);
	}
}
