package com.reph3x.breakthrough;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.reph3x.breakthrough.World.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.reph3x.breakthrough.Bullet.*;
import com.badlogic.gdx.math.*;
import com.reph3x.breakthrough.Particle.*;
import com.reph3x.breakthrough.UI.*;

public class Breakthrough implements ApplicationListener
{
	World world;
	float enemyTimer = 0;
	float w;
	float h;
	

	@Override
	public void create()
	{
		
		world = new World();
		world.addRandomEnemy(25);
		//world.addRandomEntitySpawner(50);
	}

	@Override
	public void resize(int width, int height)
	{
		world.camera.viewportWidth = width;
		world.camera.viewportHeight = height;
		world.camera.update();
	}

	@Override
	public void render()
	{
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.camera.update();
		
		world.update();
		
		enemyTimer++;
		if(enemyTimer > 250) {
			world.addRandomEnemy(25);
			enemyTimer = 0;
		}
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}
	

}
