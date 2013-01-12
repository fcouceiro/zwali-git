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

import java.util.*;

import org.newdawn.slick.opengl.Texture;


public class Text
{
	Textures letter;
	List<Texture> letters = new ArrayList<Texture>(100);
	String Text;
	Vector Pos;
	int fontsize;
	char ch[];
	boolean fromlog;
	//log area
	List<String> log = new ArrayList<String>(10);
	//End
	
	int time;
	int timetimer;
	boolean timerstring;
	
	public Text(Textures t)
	{
		letter = t;
		fontsize = 10;
		this.timerstring = false;
		this.fromlog = false;
	}
	
	public Text(Textures t, boolean log)
	{
		letter = t;
		this.fontsize = 10;
	}
	
	public Text(Textures t, int time)
	{
		letter = t;
		fontsize = 10;
		this.timerstring = true;
		time = 0;
		timetimer = 10;
		letters.add(letter.H);
		letters.add(letter.I);
		letters.add(letter.T);
		this.Pos = new Vector(0,0);
	}
	
	public void add(String str)
	{
		this.log.add(str);
		
	}
	
	public void drawString(String t, Vector pos, int fontsize)
	{
				this.fontsize = fontsize;
				this.Pos = pos;
				ch = t.toCharArray();
				letters.clear();
		
			for (int i = 0; i < t.length(); i++) {
				if (ch[i] == 'A' || ch[i] == 'a')
					letters.add(letter.A);
				else if (t.charAt(i) == 'B' || t.charAt(i) == 'b')
					letters.add(letter.B);
				else if (t.charAt(i) == 'C' || t.charAt(i) == 'c')
					letters.add(letter.C);
				else if (t.charAt(i) == 'D' || t.charAt(i) == 'd')
					letters.add(letter.D);
				else if (t.charAt(i) == 'E' || t.charAt(i) == 'e')
					letters.add(letter.E);
				else if (t.charAt(i) == 'F' || t.charAt(i) == 'f')
					letters.add(letter.F);
				else if (t.charAt(i) == 'G' || t.charAt(i) == 'g')
					letters.add(letter.G);
				else if (t.charAt(i) == 'H' || t.charAt(i) == 'h')
					letters.add(letter.H);
				else if (t.charAt(i) == 'I' || t.charAt(i) == 'i')
					letters.add(letter.I);
				else if (t.charAt(i) == 'J' || t.charAt(i) == 'j')
					letters.add(letter.J);
				else if (t.charAt(i) == 'K' || t.charAt(i) == 'k')
					letters.add(letter.K);
				else if (t.charAt(i) == 'L' || t.charAt(i) == 'l')
					letters.add(letter.L);
				else if (t.charAt(i) == 'M' || t.charAt(i) == 'm')
					letters.add(letter.M);
				else if (t.charAt(i) == 'N' || t.charAt(i) == 'n')
					letters.add(letter.N);
				else if (t.charAt(i) == 'O' || t.charAt(i) == 'o')
					letters.add(letter.O);
				else if (t.charAt(i) == 'P' || t.charAt(i) == 'p')
					letters.add(letter.P);
				else if (t.charAt(i) == 'Q' || t.charAt(i) == 'q')
					letters.add(letter.Q);
				else if (t.charAt(i) == 'R' || t.charAt(i) == 'r')
					letters.add(letter.R);
				else if (t.charAt(i) == 'S' || t.charAt(i) == 's')
					letters.add(letter.S);
				else if (t.charAt(i) == 'T' || t.charAt(i) == 't')
					letters.add(letter.T);
				else if (t.charAt(i) == 'U' || t.charAt(i) == 'u')
					letters.add(letter.U);
				else if (t.charAt(i) == 'V' || t.charAt(i) == 'v')
					letters.add(letter.V);
				else if (t.charAt(i) == 'W' || t.charAt(i) == 'w')
					letters.add(letter.W);
				else if (t.charAt(i) == 'X' || t.charAt(i) == 'x')
					letters.add(letter.X);
				else if (t.charAt(i) == 'Y' || t.charAt(i) == 'y')
					letters.add(letter.Y);
				else if (t.charAt(i) == 'Z' || t.charAt(i) == 'z')
					letters.add(letter.Z);
				else if (t.charAt(i) == ' ')
					letters.add(letter.SPACE);
				else if (t.charAt(i) == '/')
					letters.add(letter.slash);
				else if (t.charAt(i) == '-')
					letters.add(letter.minus);
				else if (t.charAt(i) == '+')
					letters.add(letter.plus);
				else if (t.charAt(i) == '*')
					letters.add(letter.times);
				else if (t.charAt(i) == '"')
					letters.add(letter.aspas);
				else if (t.charAt(i) == '&')
					letters.add(letter.ecomer);
				else if (t.charAt(i) == '%')
					letters.add(letter.percentagem);
				else if (t.charAt(i) == '$')
					letters.add(letter.cifrao);
				else if (t.charAt(i) == '@')
					letters.add(letter.arroba);
				else if (t.charAt(i) == '.')
					letters.add(letter.pfinal);
				else if (t.charAt(i) == '?')
					letters.add(letter.interrogacao);
				else if (t.charAt(i) == '!')
					letters.add(letter.exclamacao);
				else if (t.charAt(i) == '=')
					letters.add(letter.equal);
				else if (t.charAt(i) == '(')
					letters.add(letter.pa);
				else if (t.charAt(i) == ')')
					letters.add(letter.pf);
				else if (t.charAt(i) == '[')
					letters.add(letter.pra);
				else if (t.charAt(i) == ']')
					letters.add(letter.prf);
				else if (t.charAt(i) == '{')
					letters.add(letter.ca);
				else if (t.charAt(i) == '}')
					letters.add(letter.cf);
				else if (t.charAt(i) == ';')
					letters.add(letter.pvirgula);
				else if (t.charAt(i) == ':')
					letters.add(letter.doisp);
				else if (t.charAt(i) == '\'')
					letters.add(letter.aspp);
				else if (t.charAt(i) == '<')
					letters.add(letter.menor);
				else if (t.charAt(i) == '>')
					letters.add(letter.maior);
				else if (t.charAt(i) == '1')
					letters.add(letter._1);
				else if (t.charAt(i) == '2')
					letters.add(letter._2);
				else if (t.charAt(i) == '3')
					letters.add(letter._3);
				else if (t.charAt(i) == '4')
					letters.add(letter._4);
				else if (t.charAt(i) == '5')
					letters.add(letter._5);
				else if (t.charAt(i) == '6')
					letters.add(letter._6);
				else if (t.charAt(i) == '7')
					letters.add(letter._7);
				else if (t.charAt(i) == '8')
					letters.add(letter._8);
				else if (t.charAt(i) == '9')
					letters.add(letter._9);
				else if (t.charAt(i) == '0')
					letters.add(letter._0);
				else
					letters.add(letter.Red);
		}
	}

	public void updateLog()
	{
		
		if (log.size() == 1) {
			String a = log.get(0);
			this.drawString(a, new Vector(583, 553), this.fontsize);
			this.draw();
		}
		else if (log.size() == 2) {
			int b = 0;
			for (int i = (log.size() - 2); i < log.size(); i++) {
				String a = log.get(i);
				this.drawString(a, new Vector(583, 553 + 15 * b), this.fontsize);
				this.draw();
				b++;
			}
		}
		if (log.size() >= 3) {
			int b = 0;
			for (int i = (log.size() - 3); i < log.size(); i++) {
				String a = log.get(i);
				this.drawString(a, new Vector(583, 553 + 15 * b), this.fontsize);
				this.draw();
				b++;
			}
		}
	}
	
	public void clearString()
	{
		letters.clear();
	}
	
	public void setPos(Vector pos)
	{
		this.Pos = pos;
	}
	
	public void draw()
	{
		for(int i = 0; i < log.size(); i++)
			this.fromlog = true;
		
		if(this.fromlog){
			glColor4f(1.0f,0.5f,0.0f,1);
			this.fromlog = false;
		}
		else
			glColor4f(1.0f,1.0f,1.0f,1);
				if (!timerstring) {
					for (int i = 0; i < letters.size(); i++) {
						glLoadIdentity();
						glPushMatrix();
						letters.get(i).bind();
						glTranslatef((float) Pos.x + (fontsize * i),
								(float) Pos.y, 0.0f);
						glBegin(GL_QUADS);
						glTexCoord2f(0, 0);
						glVertex2i(0, 0); // Upper-left
						glTexCoord2f(1, 0);
						glVertex2i((int) (fontsize), 0); // Upper-right
						glTexCoord2f(1, 1);
						glVertex2i((int) (fontsize), (int) (fontsize)); // Bottom-right
						glTexCoord2f(0, 1);
						glVertex2i(0, (int) (fontsize));
						glEnd();
						glPopMatrix();
					}
				}
				else
				{
					if (time <= timetimer) {
						for (int i = 0; i < letters.size(); i++) {
							glLoadIdentity();
							glPushMatrix();
							letters.get(i).bind();
							glTranslatef((float) Pos.x + (fontsize * i),
									(float) Pos.y, 0.0f);
							glBegin(GL_QUADS);
							glTexCoord2f(0, 0);
							glVertex2i(0, 0); // Upper-left
							glTexCoord2f(1, 0);
							glVertex2i((int) (fontsize), 0); // Upper-right
							glTexCoord2f(1, 1);
							glVertex2i((int) (fontsize), (int) (fontsize)); // Bottom-right
							glTexCoord2f(0, 1);
							glVertex2i(0, (int) (fontsize));
							glEnd();
							glPopMatrix();
						}
						time++;
					}
				}
	}
	
}
