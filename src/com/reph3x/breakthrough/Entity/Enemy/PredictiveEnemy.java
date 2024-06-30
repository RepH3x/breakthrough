package com.reph3x.breakthrough.Entity.Enemy;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.UI.*;

public class PredictiveEnemy extends Enemy
{
	public PredictiveEnemy(World world, OrthographicCamera camera, Vector2 pos, float size) {
		super(world, pos, size);
	}

	@Override
	protected void shootAtTarget()
	{
		if(shootTime >= shootCooldown) {
			Vector2 targetPos = new Vector2(world.player.pos);
			shootTime = 0;
		}
	}
}
