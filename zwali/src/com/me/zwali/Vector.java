package com.me.zwali;

import java.lang.Math;

public class Vector {

	double x, y;

	
	Vector(double a, double b)
	{
		x= a;
		y= b;
	}
	
	Vector( Vector a)
	{
		x = a.x;
		y = a.y;
	}
	
	Vector (float angle)
	{
		float angrad = (float) (Math.PI*angle/180);
		x=Math.cos(angrad);
		y=Math.sin(angrad);
	}
	
	void normalize()
	{
		if(x == 0 && y == 0);
		else
		{
			double d = Math.sqrt(x*x + y*y);
			x = x/d;
			y = y/d;
		}
	}
	
	Vector mult(double a)
	{
		Vector b = new Vector(x*a, y*a);
		return b;
	}
	
	double SizeSQ()
	{
		return x*x + y*y;
	}
	
	double Size()
	{
		return Math.sqrt(x*x+y*y);
	}
	
	double cross( Vector A)
	{
		return this.x*A.y - this.y*A.x;
	}
	
	double dot( Vector A)
	{
		return A.x*x + A.y*y;
	}
	
	void rotate( float f)
	{
		float an = (float) (f* Math.PI/180);
		double xt = (x*Math.cos(an) - y*Math.sin(an));
		double yt = x*Math.sin(an) + y*Math.cos(an);
		x = xt;
		y = yt;
	}
	
	Vector proj ( Vector A)
	{
		Vector K = A;
		K.normalize();
		double dot = this.dot(K);
		return new Vector( K.x * dot, K.y*dot );
	}
		
}
