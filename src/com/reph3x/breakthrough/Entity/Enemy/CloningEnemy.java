package com.reph3x.breakthrough.Entity.Enemy;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.World.*;
import com.badlogic.gdx.math.*;

public class CloningEnemy extends Enemy
{
	public float cloningCooldown;
	public float cloningCounter;
	public CloningEnemy(World world, Vector2 pos, float size, float speed, float hp, float shootCooldown, Color color) {
		super(world, pos, size, speed, hp, shootCooldown, color);
		cloningCooldown = 200;
		cloningCounter = 0;
	}
	public CloningEnemy(CloningEnemy e) {
		this(e.world, e.pos, e.size, e.speed, e.hp, e.shootCooldown, e.color);
	}

	@Override
	public void update()
	{
		super.update();
		cloningCounter++;
		if(cloningCounter > cloningCooldown) {
			cloningCounter = 0;
			cloneSelf();
		}
	}
	
	public void cloneSelf() {
		world.entityList.add(new CloningEnemy(this));
	}
}
