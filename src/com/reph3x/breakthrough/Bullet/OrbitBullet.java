package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;

public class OrbitBullet extends TargetedBullet
{
	
	public Vector2 initialVel;
	public float orbitSize;
	public float revolutions;
	public int revTick;
	
	public OrbitBullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, new Vector2(target), speed, isFriendly);
		this.initialVel = new Vector2(vel).nor();
		this.orbitSize = gun.entity.size + 25 + size;
		this.revolutions = 5;
		this.revTick = 1;
		setTarget(target.nor().scl(orbitSize));
	}

	@Override
	public void explode()
	{
		if(revolutions <= 0) {
			destroyBullet();
		}
		
		setTarget(stepOrbit());
	}

	@Override
	public void update()
	{
		super.update();
	}
	
	
	
	private Vector2 stepOrbit() {
		int parts = 20;
		if(revTick >= parts) {
			revolutions--;
			revTick = 1;
		}
		Vector2 newTarget = new Vector2(initialVel);
		newTarget.scl(orbitSize).rotate((360 / parts) * revTick);
		newTarget.add(gun.entity.pos).sub(pos);
		
		revTick++;
		return newTarget;
	}

	@Override
	public void render(ShapeRenderer render)
	{
		super.render(render);
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		//render.circle(targetPos.x, targetPos.y, 15);
		render.end();
	}
	
	
	
	
}
