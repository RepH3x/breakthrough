package com.reph3x.breakthrough.UI.Button;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.UI.Button.*;
import com.reph3x.breakthrough.UI.*;

public class CloseButton extends Button
{
	public Menu parent;

	public CloseButton(Vector2 pos, OrthographicCamera camera, Menu parent) {
		super(pos, 75, 75, new Color(Color.RED), camera, "X");
		this.parent = parent;
	}

	@Override
	public void onPressed()
	{
		parent.onClose();
		remove();
	}


}
