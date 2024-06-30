package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Element
{
	public Vector2 pos;
	public float width;
	public float height;
	public Color color;
	public Color borderColor;
	public boolean hasBorder;
	public float borderPadding;
	public boolean isDead;
	
	public Element(Vector2 pos, float width, float height, Color color) {
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.color = color;
		this.borderColor = new Color(0.2f, 0.2f, 0.2f, 1);
		this.hasBorder = true;
		this.borderPadding = 5;
		this.isDead = false;
	}
	
	//needs to be overridden to function
	public void update() {
		
	}
	
	public void render(ShapeRenderer render, SpriteBatch batch, BitmapFont font) {
		if (!hasBorder) {
			render.begin(ShapeRenderer.ShapeType.Filled);
			render.setColor(color);
			render.rect(pos.x, pos.y, width, height);
			render.end();
		} else {
			render.begin(ShapeRenderer.ShapeType.Filled);
			render.setColor(borderColor);
			render.rect(pos.x, pos.y, width, height);
			render.setColor(color);
			render.rect(pos.x + borderPadding/2, pos.y + borderPadding/2, width - borderPadding, height - borderPadding);
			render.end();
		}
	}
	
	public void scale(float width, float height) {
		this.width*=width;
		this.height*=height;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void remove() {
		isDead = true;
	}
}
