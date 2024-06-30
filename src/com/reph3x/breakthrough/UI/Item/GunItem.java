package com.reph3x.breakthrough.UI.Item;
import com.reph3x.breakthrough.Gun.*;

public class GunItem extends Item
{
	
	public Gun gun;
	
	public GunItem(String name, ItemTile tile, Gun gun) {
		super(name, tile);
		this.gun = gun;
	}
}
