package com.reph3x.breakthrough.Entity.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.*;
import com.reph3x.breakthrough.Bullet.*;
import com.reph3x.breakthrough.World.*;
import com.reph3x.breakthrough.Gun.*;
import com.reph3x.breakthrough.Entity.*;

public class Player extends Entity
{

	public float shootCooldown;
	public float shootTime;
	public boolean touchedLastFrame;
	public int strength;
	public int dexterity;
	public int intelligence;

	public Player(World world, Vector2 pos, float size, float speed, float hp, float shootCooldown, OrthographicCamera camera, Color color)
	{
		super(world, pos, size, speed, hp, true, color);
		this.shootCooldown = shootCooldown;
		shootTime = 0;
		touchedLastFrame = false;
	}

	public void update()
	{
		super.update();
		checkTouch(world.camera);
	}

	//returns true if there was a special case gun
	@Override
	public boolean specialCaseGuns()
	{
		if(gun instanceof ChargeGun) {
			ChargeGun cGun = (ChargeGun) gun;
			if(cGun.stageTimer > cGun.stageDur) {
				cGun.stageTimer = 0;
				cGun.currentStage++;
			}
			if(cGun.currentStage > cGun.stages)
				cGun.currentStage = cGun.stages;
			cGun.stageTimer++;
			if(touchedLastFrame) {
				touchedLastFrame = false;
				cGun.currentStage = 0;
				cGun.stageTimer = 0;
				Vector3 target = new Vector3(Gdx.input.getX(1), Gdx.input.getY(1), 0);
				world.camera.unproject(target);
				Vector2 target2 = new Vector2(target.x, target.y);
				gun = cGun;
				shoot(target2);
			}
			return true;
		}
		return false;
	}



	private void checkTouch(Camera camera)
	{
		if (Gdx.input.isTouched(1))
		{
			if(!specialCaseGuns() && Gdx.input.justTouched()) {
				Vector3 target = new Vector3(Gdx.input.getX(1), Gdx.input.getY(1), 0);
				camera.unproject(target);
				Vector2 target2 = new Vector2(target.x, target.y);
				shoot(target2);
			}
		}
	}






}
