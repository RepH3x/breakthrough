package com.reph3x.breakthrough.UI.Item;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.reph3x.breakthrough.UI.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Item
{
	public String name;
	public ItemTile tile;
	
	public Item(String name, ItemTile tile) {
		this.name = name;
		this.tile = tile;
	}
	
	public void render(SpriteBatch batch) {
		tile.render(batch);
	}
}
