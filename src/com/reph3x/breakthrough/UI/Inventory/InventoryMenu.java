package com.reph3x.breakthrough.UI.Inventory;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.UI.*;
import java.util.*;
import com.reph3x.breakthrough.UI.Item.*;

public class InventoryMenu extends Menu
{
	
	public List<InventorySlot> inventory;
	public float padding;
	public int rows;
	public int columns;
	protected OrthographicCamera camera;
	
	public InventoryMenu(Vector2 pos, Color color, World world, int rows, int columns, OrthographicCamera camera) {
		super(pos, 0, 0, color, world);
		this.inventory = new ArrayList<InventorySlot>();
		this.padding = 10;
		this.rows = rows;
		this.columns = columns;
		this.camera = camera;
	}

	@Override
	public void createElements()
	{
		InventorySlot dummy = new InventorySlot(new Vector2(), new Color(Color.GRAY), camera);
		width = columns*dummy.width + (columns-1)*padding + borderPadding*3 + elementList.get(0).width;
		height = rows*dummy.height + (rows-1)*padding + borderPadding*3 + elementList.get(0).height;
		for (int i=0;i<columns;i++) {
			for(int j=0;j<rows;j++) {
				Vector2 iPos = new Vector2();
				iPos.x = (pos.x+borderPadding) + padding*i + dummy.width*i;
				iPos.y = (pos.y-borderPadding+height-dummy.height) - padding*j - dummy.height*j;
				Item item = new Item("Gun", new ItemTile(iPos, "Gun.png"));
				InventorySlot iSlot = new InventorySlot(iPos, new Color(Color.GRAY), item, camera);
				inventory.add(iSlot);
				world.queueUpdate(iSlot);
			}
		}
		Vector2 closePos = new Vector2(pos.x + width - elementList.get(0).width - borderPadding, pos.y + height - elementList.get(0).height - borderPadding);
		elementList.get(0).pos = closePos;
	}

	@Override
	public void onClose()
	{
		super.onClose();
		for(Element el : inventory) {
			el.remove();
		}
	}
	
	
}
