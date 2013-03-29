package com.me.zwali;

public class Collision {

	public Collision ()
	{		
	}
	
	public boolean BCool( Entity A, Entity B)
	{
		boolean col = false;
		
		if( (A.pos.x - A.size.x/2 >= B.pos.x - B.size.x/2) && ( A.pos.x - A.size.x/2 <= B.pos.x + B.size.x/2  ))
		{
			if( (A.pos.y - A.size.y/2 >= B.pos.y - B.size.y/2) && (A.pos.y - A.size.y/2 <= B.pos.y + B.size.y/2))
				col = true;
						
			else if( (A.pos.y + A.size.y/2 >= B.pos.y - B.size.y/2) && (A.pos.y + A.size.y/2 <= B.pos.y + B.size.y/2) )
				col = true;
		}
				
		if( (A.pos.x + A.size.x/2 >= B.pos.x - B.size.x/2) && (A.pos.x + A.size.x/2 <= B.pos.x + B.size.x/2  ))
		{
			if(( A.pos.y - A.size.y/2 >= B.pos.y - B.size.y/2) && (A.pos.y - A.size.y/2 <= B.pos.y + B.size.y/2))
				col = true;
						
			else if( (A.pos.y + A.size.y/2 >= B.pos.y - B.size.y/2) && (A.pos.y + A.size.y/2 <= B.pos.y + B.size.y/2) )
				col = true;
		}
		
		if( (B.pos.x - B.size.x/2 >= A.pos.x - A.size.x/2) &&( B.pos.x - B.size.x/2 <= A.pos.x + A.size.x/2  ))
		{
			if( (B.pos.y - B.size.y/2 >=A.pos.y - A.size.y/2) && (B.pos.y - B.size.y/2 <= A.pos.y + A.size.y/2))
				col = true;
						
			else if( (B.pos.y + B.size.y/2 >= A.pos.y - A.size.y/2) && (B.pos.y + B.size.y/2 <= A.pos.y + A.size.y/2) )
				col = true;
		}
				
		if( (B.pos.x + B.size.x/2 >= A.pos.x - A.size.x/2) && (B.pos.x + B.size.x/2 <= A.pos.x + A.size.x/2  ))
		{
			if(( B.pos.y - B.size.y/2 >= A.pos.y - A.size.y/2) && (B.pos.y - B.size.y/2 <= A.pos.y + A.size.y/2))
				col = true;
						
			else if( (B.pos.y + B.size.y/2 >= A.pos.y - A.size.y/2) && (B.pos.y + B.size.y/2 <= A.pos.y + A.size.y/2) )
				col = true;
		}
			

				
		return col;
	
		
	}
	
	public Vector4 Coll(Entity A, Entity B)
	{
		if( A.circle)
		{
			if( B.circle)
			{
				return CircleCollide(A,B);
			}
			
			else
			{
				return CircleBoxColl(A,B);
			}
		}
		else
		{
			if( B.circle)
			{
				return CircleBoxColl(A, B);
			}
			else
			{
				return BoxCollide(A,B);
			}
		}
	}
	
	private Vector4 CircleCollide(Entity A, Entity B)
	{
		Vector Dist = new Vector( B.pos.x - A.pos.x, B.pos.y - A.pos.y);
		float min_dist = (A.radii + B.radii);
		float real_dist = (float) Math.sqrt( Dist.SizeSQ() );
		float dist = real_dist - min_dist;
		Dist.normalize();
		return new Vector4( Dist, dist);
	}
	
	private Vector4 CircleBoxColl(Entity A, Entity B)
	{
		Entity Circ;
		Entity Box;
		boolean AIsCircle = false;
		if(A.circle)
		{
			AIsCircle = true;
			Circ = A;
			Box = B;
		}
		else
		{
			Circ = B;
			Box = A;
		}
		
		Vector ClosestP = new Vector(0,0);
		Vector CircPos = new Vector(Circ.pos.x - Box.pos.x, Circ.pos.y - Box.pos.y);
		
		CircPos.rotate((float) -Box.angle);
		
		if( CircPos.x > Box.size.x/2)
		{
			if( CircPos.y >Box.size.y/2)
			{
				ClosestP = new Vector(Box.size.x/2, Box.size.y/2);
			}
			
			else if( CircPos.y < -Box.size.y/2 )
			{
				ClosestP = new Vector(Box.size.x/2,  -Box.size.y/2);
			}
			
			else
			{
				ClosestP = new Vector ( Box.size.x/2 , CircPos.y );
			}
		}
		
		else if( CircPos.x < - Box.size.x/2)
		{
			if( CircPos.y > Box.size.y/2)
			{
				ClosestP = new Vector(-Box.size.x/2, Box.size.y/2);
			}
			
			else if( CircPos.y < -Box.size.y/2 )
			{
				ClosestP = new Vector(-Box.size.x/2,  -Box.size.y/2);
			}
			
			else
			{
				ClosestP = new Vector ( -Box.size.x/2 , CircPos.y );
			}
		}
		
		else
		{
			if( CircPos.y > Box.size.y/2)
			{
				ClosestP = new Vector(CircPos.x,  Box.size.y/2);
			}
			
			else if( CircPos.y < - Box.size.y/2 )
			{
				ClosestP = new Vector(CircPos.x,  - Box.size.y/2);
			}
		}
		
		Vector Sep = new Vector( CircPos.x - ClosestP.x, CircPos.y - ClosestP.y);
		float dist = (float) (Math.sqrt(Sep.SizeSQ()));
		Sep.normalize();
		Vector K = new Vector ( Sep.x*(dist-Circ.radii), Sep.y*(dist-Circ.radii));
		dist-=Circ.radii;
		K.normalize();
		if( Sep.dot(K)<0)
		{
			dist*=-1;
			K = K.mult(-1);
		}
		if( !AIsCircle)
		{
			dist = -dist;
			Sep = Sep.mult(-1);
		}
		
		Sep.rotate((float) Box.angle);
		
		return new Vector4( Sep, dist);
	}
	
	public Vector4 BoxCollide( Entity A, Entity B)
	{
		Vector ClosestP = new Vector(0,0);
		Vector Box1P = new Vector(0,0);
		
		if( A.pos.x > B.pos.x + B.size.x/2)
		{
			if( A.pos.y > B.pos.y + B.size.y/2)
			{
				ClosestP = new Vector(B.pos.x + B.size.x/2,  B.pos.y + B.size.y/2);
				Box1P = new Vector ( A.pos.x - A.size.x/2, A.pos.y - A.size.y/2);
				
			}
			
			else if( A.pos.y < B.pos.y - B.size.y/2 )
			{
				ClosestP = new Vector(B.pos.x + B.size.x/2,  B.pos.y - B.size.y/2);
				Box1P = new Vector ( A.pos.x - A.size.x/2, A.pos.y + A.size.y/2);
			}
			
			else
			{
				ClosestP = new Vector ( B.pos.x + B.size.x/2 , A.pos.y );
				Box1P = new Vector ( A.pos.x -  A.size.x/2, A.pos.y);
			}
		}
		
		else if ( A.pos.x < B.pos.x - B.size.x/2)
		{
			if( A.pos.y > B.pos.y + B.size.y/2)
			{
				ClosestP = new Vector(B.pos.x - B.size.x/2,  B.pos.y + B.size.y/2);
				Box1P = new Vector ( A.pos.x + A.size.x/2, A.pos.y - A.size.y/2);
			}
			
			else if( A.pos.y < B.pos.y - B.size.y/2 )
			{
				ClosestP = new Vector(B.pos.x - B.size.x/2,  B.pos.y - B.size.y/2);
				Box1P = new Vector ( A.pos.x + A.size.x/2, A.pos.y + A.size.y/2);
			}
			
			else
			{
				ClosestP = new Vector ( B.pos.x - B.size.x/2 , A.pos.y );
				Box1P = new Vector ( A.pos.x + A.size.x/2, A.pos.y);
			}
			
		}
		
		else
		{
			if( A.pos.y > B.pos.y + B.size.y/2)
			{
				ClosestP = new Vector(A.pos.x,  B.pos.y + B.size.y/2);
				Box1P = new Vector ( A.pos.x, A.pos.y - A.size.y/2);
			}
			
			else if( A.pos.y < B.pos.y - B.size.y/2 )
			{
				ClosestP = new Vector(A.pos.x,  B.pos.y - B.size.y/2);
				Box1P = new Vector ( A.pos.x, A.pos.y + A.size.y/2);
			}
		}
		
		Vector Dist = new Vector ( Box1P.x - ClosestP.x, Box1P.y - ClosestP.y);
		
		float dist = (float) Math.sqrt(Dist.SizeSQ());
		Dist.normalize();
		
		return new Vector4 (Dist, dist);
		
	}
	
}
