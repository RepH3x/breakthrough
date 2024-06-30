package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.reph3x.breakthrough.Entity.*;

public class DamageIndicator extends MovingIndicator
{
	public Entity entity;
	public float damage;
	
	public DamageIndicator(Vector2 pos, Entity entity, float damage, Color color, SpriteBatch batch, BitmapFont font) {
		super(pos, new Vector2(2,2), ""+damage, color, batch, font);
		this.damage = damage;
		this.entity = entity;
	}

	@Override
	public void update()
	{
		pos.set(entity.pos.x + (timer-1)*vel.x, entity.pos.y + (timer-1)*vel.y);
		super.update();
	}
	
	
}
