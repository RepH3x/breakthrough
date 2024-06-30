package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;

public class Indicator extends Element
{
	public Vector2 pos;
	public String text;
	public Color color;
	public Color textColor;
	public float textSize;
	
	public Indicator(Vector2 pos, String text, Color color, SpriteBatch batch, BitmapFont font) {
		super(pos, 0, 0, color);
		this.pos = pos;
		this.text = text;
		this.color = color;
		this.textColor = new Color(Color.BLACK);
		this.textSize = 3;
	}
	
	public void update() {
	}
	
	public void render(ShapeRenderer render, SpriteBatch batch, BitmapFont font) {
		font.setScale(textSize);
		width = font.getBounds(text).width;
		height = font.getBounds(text).height;
		float padding = 5;
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.setColor(color);
		render.rect(pos.x-padding, pos.y-height-padding,width+padding*2,height+padding*2);
		render.end();
		batch.begin();
		font.setColor(textColor);
		font.setScale(textSize);
		font.draw(batch, text, pos.x, pos.y);
		batch.end();
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
