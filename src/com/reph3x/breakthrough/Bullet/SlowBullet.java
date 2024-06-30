package com.reph3x.breakthrough.Bullet;

import com.reph3x.breakthrough.Effect.*;
import com.reph3x.breakthrough.Gun.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class SlowBullet extends EffectBullet
{
	public SlowBullet(Vector2 pos, Gun gun, float level, float damage, float slow, float ticks, float timer, float size, Vector2 target, float speed, boolean isFriendly){ 
		super(pos, gun, new EffectSlow(null, level, ticks, timer, slow), damage, size, target, speed, isFriendly);
	}
		
}
