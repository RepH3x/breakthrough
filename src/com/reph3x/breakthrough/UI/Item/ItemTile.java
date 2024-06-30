package com.reph3x.breakthrough.UI.Item;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.UI.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;

public class ItemTile extends Element
{
	public Texture texture;
	
	public ItemTile(Vector2 pos, String texturePath) {
		super(pos, 96, 96, new Color(Color.WHITE));
		texture = new Texture(Gdx.files.internal(texturePath));
	}

	public void render(SpriteBatch batch)
	{
		batch.begin();
		batch.draw(texture, pos.x, pos.y);
		batch.end();
	}
	
	
}
