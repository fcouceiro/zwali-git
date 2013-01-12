package com.me.zwali;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;


import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;


public class InvMenu
{
	Vector Pos;
	Vector Size;
	Vector stringPos = new Vector(50, 570);
	public Text string;
	List <Texture> topImages = new ArrayList<Texture>(10);
	
	
	public InvMenu(Textures T) 
	{
		string = new Text(T);
		this.Pos = new Vector(0,540);
		this.Size = new Vector(800,800);
		topImages.add(T.barIM);
		topImages.add(T.bar_ammo);
		topImages.add(T.bar_medkit);
		topImages.add(T.bar_outofammo);
		topImages.add(T.bar_stroke);
		topImages.add(T.bar_buildmodeon);
		topImages.add(T.bar_buildmodeoff);
	}
	
	public void update(boolean buildMode)
	{
		this.drawBAR();
		//this.drawMK();
		this.drawWp();
		this.drawStroke();
		
		string.draw();
	}

	
	private void drawWp()
	{
		glLoadIdentity();
		glPushMatrix();
		
		topImages.get(1).bind();
		
		glTranslatef((float)117, (float)570, 0.0f); 
				
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0, 0); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)64, 0); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)64, (int)64); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(0, (int)64);
        glEnd();	
        glPopMatrix();
	}
	
	private void drawStroke()
	{
		glLoadIdentity();
		glPushMatrix();
		
		topImages.get(4).bind();
		
		glTranslatef((float)Pos.x, (float)Pos.y, 0.0f); 
				
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0, 0); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)Size.x, 0); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)Size.x, (int)Size.y); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(0, (int)Size.y);
        glEnd();	
        glPopMatrix();
	}
	
	private void drawMK()
	{
		glLoadIdentity();
		glPushMatrix();
		
		topImages.get(2).bind();
		
		glTranslatef((float)400, (float)578, 0.0f); 
				
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0, 0); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)75, 0); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)75, (int)36); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(0, (int)36);
        glEnd();	
        glPopMatrix();
	}
	
	private void drawBAR()
	{
		glLoadIdentity();
		glPushMatrix();
		
		topImages.get(0).bind();
		
		glTranslatef((float)Pos.x, (float)Pos.y, 0.0f); 
				
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0, 0); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)Size.x, 0); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)Size.x, (int)Size.y); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(0, (int)Size.y);
        glEnd();	
        glPopMatrix();
	}

}
