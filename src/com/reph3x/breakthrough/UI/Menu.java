package com.reph3x.breakthrough.UI;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.UI.Button.*;

public class Menu extends Element
{
	public List<Element> elementList;
	public World world;
	
	public Menu(Vector2 pos, float width, float height, Color color, World world) {
		super(pos, width, height, color);
		elementList = new ArrayList<Element>();
		this.world = world;
	}
	
	//Must be called after creation of Menu
	public void create() {
		createButtons();
		createElements();
	}

	public void createButtons() {
		CloseButton close = new CloseButton(new Vector2(), world.camera, this);
		Vector2 closePos = new Vector2((pos.x+width)-50 - borderPadding,(pos.y+height)- 50 - borderPadding);
		close.pos = closePos;
		elementList.add(close);
		world.queueUpdate(close);
	}
	
	//Must be overridden to function
	public void createElements() {
		
	}
	
	public void onClose() {
		remove();
	}
}
