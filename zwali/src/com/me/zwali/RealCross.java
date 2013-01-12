package com.me.zwali;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.newdawn.slick.opengl.Texture;
import java.lang.Math;


public class RealCross extends Crosshair {

	Texture Side;
	Vector Sidesize;
	Vector Upsize;
	int radii;
	
	public RealCross( Vector pos, Vector size, Texture Side)
	{
		super(pos, size, Side);
		this.Side = Side;
		Upsize = size;
		Sidesize = new Vector( size.y, size.x);
	}
	
	public void setOpen( Player PL, Vector Disp)
	{
		Vector dist = new Vector( this.pos.x-(PL.pos.x - Disp.x), this.pos.y-(PL.pos.y-Disp.y));
		this.radii = (int) (Math.tan(PL.accuracy*Math.PI/180)*dist.Size());
	}
	
	public void draw()
	{
		
		glLoadIdentity();
		glPushMatrix();
		
		Side.bind();
		
		glTranslatef((float)pos.x, (float)pos.y, 0.0f); 
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i((int)((-1)*(Upsize.x/2)), (int)((-1)*(Upsize.y + radii))); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)( Upsize.x/2), (int)((-1)*(Upsize.y + radii))); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)( Upsize.x/2), (int)((-1)*(radii))); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i((int)((-1)*(Upsize.x/2)), (int)((-1)*(radii)));
        glEnd();	
        glPopMatrix();
        
        glLoadIdentity();
		glPushMatrix();
		
		Side.bind();
		
		glTranslatef((float)pos.x, (float)pos.y, 0.0f); 
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i((int)((-1)*(Upsize.x/2)), (int)((Upsize.y + radii))); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)( Upsize.x/2), (int)((Upsize.y + radii))); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)( Upsize.x/2), (int)((radii))); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i((int)((-1)*(Upsize.x/2)), (int)((radii)));
        glEnd();	
        glPopMatrix();

        
        glLoadIdentity();
		glPushMatrix();
		
		Side.bind();
		
		glTranslatef((float)pos.x, (float)pos.y, 0.0f); 
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i((int)((-1)*(Sidesize.x + radii)), (int)((-1)*(Sidesize.y/2))); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)( (-1)*(radii)), (int)((-1)*(Sidesize.y/2))); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)( (-1)*(radii)), (int)((Sidesize.y/2))); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i((int)((-1)*(Sidesize.x + radii)), (int)(Sidesize.y/2));
        glEnd();	
        glPopMatrix();
        
        
        glLoadIdentity();
		glPushMatrix();
		Side.bind();
		
		glTranslatef((float)pos.x, (float)pos.y, 0.0f); 
		
		glBegin(GL_QUADS);
			glTexCoord2f(0,0);
			glVertex2i((int)((Sidesize.x + radii)), (int)((-1)*(Sidesize.y/2))); // Upper-left
            glTexCoord2f(1, 0);
            glVertex2i((int)( (radii)), (int)((-1)*(Sidesize.y/2))); // Upper-right
            glTexCoord2f(1, 1);
            glVertex2i((int)( (radii)), (int)((Sidesize.y/2))); // Bottom-right
            glTexCoord2f(0, 1);
            glVertex2i((int)((Sidesize.x + radii)), (int)(Sidesize.y/2));
        glEnd();	
        glPopMatrix();

		
	}
}
