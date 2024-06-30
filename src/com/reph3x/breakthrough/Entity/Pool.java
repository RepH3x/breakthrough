package com.reph3x.breakthrough.Entity;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.Effect.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.*;
import com.reph3x.breakthrough.Particle.*;
import com.reph3x.breakthrough.Help.*;
import java.util.*;
import com.reph3x.breakthrough.Entity.Enemy.*;

public class Pool extends Entity
{
	public Effect effect;
	public int timer;
	public int currentTime;
	public float bubbleSize;
	public int bubbleCount;
	public List<Bubble> bubbleList;
	
	public Pool(World world, Vector2 pos, float size, boolean isFriendly, Color color, Effect effect, int timer) {
		super(world, pos, size, 0, 1, isFriendly, color);
		this.effect = effect;
		this.timer = timer;
		this.currentTime = 0;
		this.bubbleSize = 15;
		this.bubbleCount = 5;
		this.bubbleList = new ArrayList<Bubble>();
	}

	@Override
	public void update()
	{
		super.update();
		cleanBubbles();
		if(currentTime >= timer)
			isDead = true;
		this.currentTime++;
		if(bubbleList.size() < bubbleCount) {
			addBubble();
		}
	}

	@Override
	public void render(ShapeRenderer render)
	{
		Gdx.gl.glLineWidth(5);
		render.begin(ShapeRenderer.ShapeType.Line);
		render.setColor(color);
		render.circle(pos.x, pos.y, size);
		render.end();
		for(Bubble bubble: bubbleList) {
			bubble.update();
			bubble.render(render);
		}
	}
	
	@Override
	public void checkCollision()
	{
		for(Entity entity: world.entityList) {
			if(isTouching(entity)) {
				if(isFriendly != entity.isFriendly && !(entity instanceof ShieldedEnemy))
					entity.addEffect(effect);
			}
		}
		if(isTouching(world.player) && isFriendly != world.player.isFriendly) {
			world.player.addEffect(effect);
		}
		
	}
	
	public void addBubble() {
		Bubble bubble = new Bubble(QuickMaths.getRandomPointInCircle(pos, size), color);
		if(QuickMaths.isCloserThan(pos, bubble.pos, size - (bubble.maxSize/2))) {
			boolean touchesAny = false;
			for(Bubble listBubble : bubbleList) {
				if(QuickMaths.isTouching(bubble, listBubble)) {
					touchesAny = true;
				}
			}
			if(!touchesAny) {
				bubbleList.add(bubble);
			} else {
				addBubble();
			}
		}
	}
	
	private void cleanBubbles() {
		List<Bubble> cleanList = new ArrayList<Bubble>();
		for(Bubble bubble: bubbleList) {
			if(bubble.isDead) {
				cleanList.add(bubble);
			}
		}
		bubbleList.removeAll(cleanList);
	}
}
