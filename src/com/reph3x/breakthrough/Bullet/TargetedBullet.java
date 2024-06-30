package com.reph3x.breakthrough.Bullet;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Help.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;

public class TargetedBullet extends Bullet
{
	public Vector2 origin;
	public float distance;
	
	public TargetedBullet(Vector2 pos, Gun gun, float damage, float size, Vector2 target, float speed, boolean isFriendly) {
		super(pos, gun, damage, size, target, speed, isFriendly);
		origin = new Vector2(pos);
		this.distance = QuickMaths.getQuickDistance(targetPos, origin);
	}
	public TargetedBullet(Vector2 pos, Gun gun, float damage, Vector2 target, float speed, boolean isFriendly) {
		this(pos, gun, damage, 15, target, speed, isFriendly);
	}
	
	public void update() {
		super.update();
		if(QuickMaths.getQuickDistance(pos, origin) >= distance) {
			explode();
		}
		
	}
	
	public void explode() {
		destroyBullet();
		drawTargetParticles();
	}

	@Override
	public void setTarget(Vector2 target)
	{
		super.setTarget(target);
		origin = new Vector2(pos);
		distance = QuickMaths.getQuickDistance(targetPos, origin);
	}
	
	
	
	
	
	public void drawTargetParticles() {
		
	}

	/* @Override
	public void render(ShapeRenderer render)
	{
		super.render(render);
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.rectLine(origin, targetPos, 5);
		render.end();
	} */
	
	
}
