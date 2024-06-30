package com.reph3x.breakthrough.Entity.Enemy;
import com.reph3x.breakthrough.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.World.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Bullet.*;
import java.util.*;
import com.reph3x.breakthrough.Entity.Player.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.UI.*;

public class Enemy extends Entity
{
	public float shootCooldown;
	public float shootTime;

	public Enemy(World world, Vector2 pos, float size, float speed, float hp, float shootCooldown, Color color) {
		super(world, pos, size, speed, hp, false, color);
		this.shootCooldown = shootCooldown;
		shootTime = 0;
	}

	public Enemy(World world, Vector2 pos, float size) {
		this(world, pos, size, 5, 5, 200, Color.RED);
	}

	public void update() {
		super.update();
		if(isDead) {
			world.addUpdate(new Update("Enemy Killed!", new Color(Color.GREEN), 250));
		}
		avoidAllies();
		shootTime++;
		if(world.player != null) {
			followPlayer(world.player);
			if(isTouching(world.player)) {
				isDead = true;
				world.player.takeDamage(1);
			}
			shootAtTarget();
		}
	}
	
	protected void shootAtTarget() {
		if(shootTime >= shootCooldown) {
			shoot(world.player);
			shootTime = 0;
		}
	}

	private void followPlayer(Player player) {
		if(player != null) {
			Vector2 playerPos = new Vector2(player.pos);
			playerPos.sub(pos);
			vel.add(playerPos).clamp(0, currentSpeed);
		}

	}

	protected void avoidAllies() {
		float avoidRange = 100;
		float bounceScalar = 1;
		for (Entity entity : world.entityList) {
			if(entity != this && entity instanceof Enemy && entity instanceof ShieldedEnemy) {
				float distance = (float)Math.pow(entity.pos.x - pos.x, 2) + (float)Math.pow(entity.pos.y - pos.y, 2);
				if(distance <= Math.pow(entity.size + avoidRange, 2)) {
					Vector2 bounceDir = new Vector2(entity.pos);
					bounceDir.sub(pos).nor().scl(bounceScalar);
					bounceDir.scl(-1);
					bounce.add(bounceDir);
				}
			}
		}
	}


}
