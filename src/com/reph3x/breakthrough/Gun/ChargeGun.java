package com.reph3x.breakthrough.Gun;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Bullet.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Entity.*;

public class ChargeGun extends Gun
{
	
	public int stages;
	public int currentStage;
	public int stageDur;
	public int stageTimer;
	
	public ChargeGun(Entity entity, Bullets bullet, float damage, float size, float speed, Color color) {
		super(entity, bullet, damage, size, speed, color);
		this.stages = 4;
		this.currentStage = 0;
		this.stageDur = 20;
		this.stageTimer = 0;
	}

	@Override
	public void shoot(Vector2 target)
	{
		float scale = 1 + (0.25f * currentStage);
		super.shoot(target, scale);
	}
	
	
}
