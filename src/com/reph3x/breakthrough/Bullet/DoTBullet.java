package com.reph3x.breakthrough.Bullet;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Effect.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class DoTBullet extends EffectBullet
{
	public DoTBullet(Vector2 pos, Gun gun, float damage, float dotDamage, float level, float ticks, float timer, float size, Vector2 target, float speed, boolean isFriendly){ 
		super(pos, gun, new EffectDoT(null, level, ticks, timer, dotDamage), damage, size, target, speed, isFriendly);
	}
}
