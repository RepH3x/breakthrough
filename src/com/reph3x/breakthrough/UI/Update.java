package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.graphics.*;

public class Update
{
	String text;
	Color color;
	Color barColor;
	float delay;
	
	public Update(String text, Color color, float delay) {
		this.text = text;
		this.color = color;
		this.barColor = new Color(color).mul(0.75f, 0.75f, 0.75f, 1);
		this.delay = delay;
	}
	public Update(String text) {
		this(text, Color.YELLOW, 250);
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
