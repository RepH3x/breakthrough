package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Entity.*;
import com.reph3x.breakthrough.Effect.*;

public class EffectPoolBullet extends TargetedBullet
{
	public Effect effect;
	
	public EffectPoolBullet(Vector2 pos, Gun gun, float damage, Vector2 target, float speed, boolean isFriendly, Effect effect) {
		super(pos, gun, damage, target, speed, isFriendly);
		this.effect = effect;
	}

	@Override
	public void explode()
	{
		super.explode();
		gun.entity.world.updateQueue.add(new Pool(gun.entity.world, pos, 100, isFriendly, color, effect, 100));
	}
	
	
}
