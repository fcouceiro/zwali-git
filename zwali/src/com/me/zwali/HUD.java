package com.me.zwali;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;
import java.util.ArrayList;
import java.util.List;

public class HUD extends Entity
{
	List <Entity> enemSpotted = new ArrayList<Entity>(10);
	Vector posE;
	Texture x;
	HUD(Vector pos, Vector size, Texture T) 
	{
		super(pos, size,false, T);
		this.x = T;
	}
	
	public void AddEnem(Entity ent)
	{
		enemSpotted.add(ent);
	}
	
	public boolean Update(Vector PPOS)
	{
		
		for(Entity enem:enemSpotted)
		{
			Vector pos = new Vector((PPOS.x - enem.pos.x),(PPOS.y - enem.pos.y));
			this.posE = new Vector((((-1)*pos.x*200)/800)+700,(((-1)*pos.y*200)/600)+100);
			if(Math.sqrt(pos.SizeSQ()) < 400)
			{
			this.drawEnem();
			
			}
		}
		
		return true;
		
	}
	
	private void drawEnem()
	{
		glLoadIdentity();
		glPushMatrix();
		
		x.bind();
		
		glTranslatef((float)posE.x, (float)posE.y, 0.0f); 
				
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i(0, 0); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i(10, 0); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i(10, 10); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i(0, 10);
        glEnd();	
        glPopMatrix();
	}
}
