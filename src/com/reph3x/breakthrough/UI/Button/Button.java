package com.reph3x.breakthrough.UI.Button;
import com.reph3x.breakthrough.UI.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.*;
public class Button extends Element 
{
	protected OrthographicCamera camera;
	public String text;
	public boolean isPressed;
	
	public Button(Vector2 pos, float width, float height, Color color, OrthographicCamera camera, String text) {
		super(pos, width, height, color);
		this.camera = camera;
		this.text = text;
		this.isPressed = false;
	}
	public Button(Vector2 pos, float width, float height, Color color, OrthographicCamera camera) {
		this(pos, width, height, color, camera, null);
	}

	@Override
	public void update()
	{
		if(isTouched()) {
			onPressed();
		}
	}

	@Override
	public void render(ShapeRenderer render, SpriteBatch batch, BitmapFont font)
	{
		super.render(render, batch, font);
		if(text != null) {
			font.setScale(3);
			float textWidth = font.getBounds(text).width;
			float textHeight = font.getBounds(text).height;
			batch.begin();
			font.setColor(new Color(Color.BLACK));
			font.draw(batch, text, pos.x + width/2 - textWidth/2, pos.y + height/2 + textHeight/2);
			batch.end();
		}
	}
	
	public boolean isTouched() {
		if(Gdx.input.justTouched()) {
			Vector3 touchRaw = new Vector3(Gdx.input.getX(0), Gdx.input.getY(0), 0);
			camera.unproject(touchRaw);
			Vector2 touchPos = new Vector2(touchRaw.x, touchRaw.y);
			if(pos.x < touchPos.x && pos.x + width > touchPos.x) {
				if(pos.y < touchPos.y && pos.y + height > touchPos.y) {
					return true;
				}
			}
		}
		return false;
	}
	
	//Must be overridden to function
	public void onPressed() {
		
	}
	
	public void remove() {
		isDead = true;
	}
}
