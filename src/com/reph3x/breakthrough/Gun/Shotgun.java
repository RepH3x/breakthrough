package com.reph3x.breakthrough.Gun;

import com.reph3x.breakthrough.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.reph3x.breakthrough.Bullet.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.Entity.*;

public class Shotgun extends Gun
{
	
	public int bulletCount;
	public float spread;
	
	public Shotgun(Entity entity, Bullets bullet, float damage, float size, float speed, int bulletCount, float spread, Color color) {
		super(entity, bullet, damage, size, speed, color);
		this.bulletCount = bulletCount;
		this.spread = spread;
	}
	
	public Shotgun(Entity entity, Bullets bullet, float damage, int bulletCount, float spread, Color color) {
		this(entity, bullet, damage, 20, 30, bulletCount, spread, color);
	}
	
	public void shoot(Vector2 target) {
		target.sub(entity.pos);
		ArrayList<Vector2> targetList = calcSpread(target);
		if(targetList != null) {
			for(Vector2 shotTarget : targetList) {
				entity.world.queueUpdate(createBullet(shotTarget));
			}
		}
		Color smokeColor = new Color(Color.GRAY);
		smokeColor.a = 0.3f;
		emitter.sprayCone(entity.pos, target, 50, 45, 5, 25, 15, smokeColor);
	}
	
	public void shoot(Entity target) {
		if (target != null) {
			shoot(new Vector2(target.pos));
		}
	}
	
	private ArrayList<Vector2> calcSpread(Vector2 target) {
		ArrayList<Vector2> targetList = new ArrayList<Vector2>();
		Vector2 spreadScanner = new Vector2(target);
		if(bulletCount % 2 == 0 && bulletCount > 0) {
			targetList.add(new Vector2(target.rotate(spread / 2)));
			targetList.add(new Vector2(spreadScanner.rotate(-spread / 2)));
			for(int i = 0; i < (bulletCount - 2) / 2; i++) {
				targetList.add(new Vector2(target.rotate(spread)));
				targetList.add(new Vector2(spreadScanner.rotate(-spread)));
			}
		}
		else if(bulletCount > 0) {
			targetList.add(new Vector2(target));
			for(int j = 0; j < (bulletCount - 1) / 2; j++) {
				targetList.add(new Vector2(target.rotate(spread)));
				targetList.add(new Vector2(spreadScanner.rotate(-spread)));
			}
		}
		return targetList;
	}
}
