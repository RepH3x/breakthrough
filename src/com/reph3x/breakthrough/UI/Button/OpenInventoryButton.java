package com.reph3x.breakthrough.UI.Button;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.UI.Inventory.*;
import com.reph3x.breakthrough.World.*;

public class OpenInventoryButton extends Button
{
	public World world;
	
	public OpenInventoryButton(Vector2 pos, Color color, World world) {
		super(pos, 100, 100, color, world.camera, "I");
		this.world = world;
	}

	@Override
	public void onPressed()
	{
		Vector2 iPos = new Vector2(-world.w/2+15, -world.h/2+750);
		InventoryMenu iMenu = new InventoryMenu(iPos, new Color(Color.LIGHT_GRAY), world, 3, 12, camera);
		world.queueUpdate(iMenu);
		iMenu.create();
	}
	
	
}
