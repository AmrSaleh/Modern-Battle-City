package com.amr.movingsprite;

import com.amr.movingsprite.GFX.GFXsurface;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class tankSprite {

	int x, y, direction, srcX, srcY, dimsOfTile;
	int xSpeed, ySpeed;
	int[][] dimentions = new int[4][2];

	Bitmap[] spriteSheets = new Bitmap[4];
	GFXsurface ourSurface;

	public tankSprite(GFXsurface gfXsurface, Bitmap right, Bitmap down, Bitmap left, Bitmap up) {
		// TODO Auto-generated constructor stub
		ourSurface = gfXsurface;

		spriteSheets[0] = right;
		spriteSheets[1] = down;
		spriteSheets[2] = left;
		spriteSheets[3] = up;

		x = y = srcX = srcY = 0;
		Log.d("debug", "y=ourSurface.getHeight(); " + gfXsurface.getHeight());
		Log.d("debug", "width / 8: " + right.getWidth() / 8);
		Log.d("debug", "height: " + right.getHeight());
		// height=24;
		// width=24;
		dimsOfTile = right.getHeight(); // =24

//		dimentions[0][0] = 24;// right.getWidth();
//		dimentions[0][1] = 24;// right.getHeight();
//		dimentions[1][0] = 24;// down.getWidth();
//		dimentions[1][1] = 24;// down.getHeight();
//		dimentions[2][0] = 24;// left.getWidth();
//		dimentions[2][1] = 24;// left.getHeight();
//		dimentions[3][0] = 24;// up.getWidth();
//		dimentions[3][1] = 24;// up.getHeight();

		xSpeed = 5;
		ySpeed = 0;

		direction = 0;

	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		update();

		Rect src = new Rect(srcX *dimsOfTile, srcY * dimsOfTile, (srcX *dimsOfTile) + dimsOfTile, (srcY *dimsOfTile) + dimsOfTile);
		Rect dst = new Rect(x, y, x + dimsOfTile * 2, y + dimsOfTile * 2);
		canvas.drawBitmap(spriteSheets[direction], src, dst, null);
	}

	public void update() {

		if (x + dimsOfTile * 2 + xSpeed > ourSurface.getWidth()) {
			// x=ourSurface.getWidth()-32*2;
			xSpeed = 0;
			ySpeed = 5;
			direction = 1;
		}
		if (y + dimsOfTile * 2 + ySpeed > ourSurface.getHeight()) {
			// y=ourSurface.getHeight()-24*2;
			xSpeed = -5;
			ySpeed = 0;
			direction = 2;
		}
		if (x + xSpeed < 0) {

			xSpeed = 0;
			ySpeed = -5;
			direction = 3;
		}
		if (y + ySpeed < 0) {

			xSpeed = 5;
			ySpeed = 0;
			direction = 0;
		}

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (direction) {
		case 0:
			srcX = (srcX + 1) % 8;
			srcY = 0;
			break;
		case 1:
			srcX = 0;
			srcY = (srcY + 1) % 8;
			break;
		case 2:
			srcX = (srcX + 1) % 8;
			srcY = 0;
			break;
		case 3:
			srcX = 0;
			srcY = (srcY + 1) % 8;
			break;
		default:
			break;
		}

		x += xSpeed;
		y += ySpeed;
	}
}
