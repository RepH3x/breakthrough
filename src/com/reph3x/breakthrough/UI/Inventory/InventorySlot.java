package com.reph3x.breakthrough.UI.Inventory;
import com.reph3x.breakthrough.UI.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.UI.Item.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.reph3x.breakthrough.UI.Button.*;

public class InventorySlot extends Button
{
	
	public Item item;
	public boolean isFull;
	
	public InventorySlot(Vector2 pos, Color color, Item item, OrthographicCamera camera) {
		super(pos, 96, 96, color, camera);
		this.item = item;
	}
	public InventorySlot(Vector2 pos, Color color, OrthographicCamera camera) {
		this(pos, color, null, camera);
	}
	
	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public void render(ShapeRenderer render, SpriteBatch batch, BitmapFont font)
	{
		super.render(render, batch, font);
		if(item != null)
			item.render(batch);
	}

	@Override
	public void onPressed()
	{ 
		
	}
}
