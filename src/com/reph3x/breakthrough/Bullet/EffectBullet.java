package com.reph3x.breakthrough.Bullet;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Effect.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Entity.*;

public class EffectBullet extends Bullet
{
	
	public Effect effect;
	
	public EffectBullet(Vector2 pos, Gun gun, Effect effect, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		this.effect = effect;
	}
	public EffectBullet(Vector2 pos, Gun gun, Effect effect, Vector2 target, boolean isFriendly) {
		this(pos, gun, effect, 1, 10, target, 20, isFriendly);
	}

	@Override
	public void onCollision(Entity entity)
	{
		super.onCollision(entity);
		entity.addEffect(effect);
	}

	@Override
	public void drawParticles()
	{
		Color particleColor = new Color(Color.PURPLE).mul(1, 1, 1, 0.3f);
		emitter.sprayInwardCircle(pos, 20, 10, 100, 15, 8, particleColor);
	}
	
	
}
