package com.reph3x.breakthrough.Gun;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Bullet.*;
import com.reph3x.breakthrough.Entity.*;

public class MineLayer extends Gun
{
	public MineLayer(Entity entity, Bullets bullet, float damage, float size, Color color) {
		super(entity, bullet, damage, size, 0, color);
	}

	@Override
public void drawParticles(Vector2 target) {} //do nothing
	
	
}
