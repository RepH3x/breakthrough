package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;

public class BoomerangBullet extends TargetedBullet
{
	public boolean rebound;
	
	public BoomerangBullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		this.rebound = false;
	}

	@Override
	public void update()
	{
		super.update();
		if(rebound) {
			setTarget(new Vector2(gun.entity.pos).sub(pos));
		}
	}
	
	
	
	@Override
	public void explode()
	{
		if(rebound)
			destroyBullet();
		rebound = true;
		setTarget(new Vector2(gun.entity.pos).sub(pos));
	}
}
