package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;

public class UpdateBar extends Element
{
	public List<Update> updateList = new ArrayList<Update>();
	public float ticks;
	
	public UpdateBar() {
		super(new Vector2(-Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2)-50), Gdx.graphics.getWidth(), 50, new Color(Color.WHITE));
		this.ticks = 0;
		hasBorder = false;
	}

	@Override
	public void update()
	{
		if(!updateList.isEmpty()) {
			ticks++;
			this.color = updateList.get(0).color;
			if(ticks > updateList.get(0).delay) {
				updateList.remove(0);
				ticks = 0;
			}
		} else {
			this.color = new Color(Color.WHITE);
		}
	}

	@Override
	public void render(ShapeRenderer render, SpriteBatch batch, BitmapFont font)
	{
		super.render(render, batch, font);
		
		if(!updateList.isEmpty()) {
			render.begin(ShapeRenderer.ShapeType.Filled);
			render.setColor(updateList.get(0).barColor);
			render.rect(pos.x, pos.y, width*(ticks/updateList.get(0).delay), height);
			render.end();
			
			font.setScale(3);
			font.setColor(Color.BLACK);
			String update = updateList.get(0).text;
			float updateW = font.getBounds(update).width;
			float updateH = font.getBounds(update).height;
			batch.begin();
			font.draw(batch, update, pos.x + (width/2)-(updateW/2), pos.y+(height/2)+(updateH/2));
			batch.end();
		}
	}

	public void addUpdate(Update update) {
		updateList.add(update);
	}
	public void addUpdates(Update[] updates) {
		for(Update update : updates) {
			addUpdate(update);
		}
	}
}
