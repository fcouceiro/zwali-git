package com.me.zwali;


public class Matriz 
{
	String [][] matrizHeroi;
	
	Matriz()
	{
			matrizHeroi = new String [4][4];
			matrizHeroi[0][0] = "Nome";
			matrizHeroi[0][1] = "Vida";
			matrizHeroi[0][2] = "Habilidade";
			matrizHeroi[0][3] = "Skin";
			
			//Boi
			matrizHeroi[1][0] = "Boi";
			matrizHeroi[1][1] = "80";
			matrizHeroi[1][2] = "1";
			matrizHeroi[1][3] = "wood";
			
			//Rapariga
			matrizHeroi[2][0] = "Rapariga";
			matrizHeroi[2][1] = "100";
			matrizHeroi[2][2] = "2";
			matrizHeroi[2][3] = "girl";
			
			//Robin Wood
			matrizHeroi[3][0] = "Robin Wood";
			matrizHeroi[3][1] = "110";
			matrizHeroi[3][2] = "3";
			matrizHeroi[3][3] = "boy";
	}
	
	public String getFromMatrizHeroi(int linha, int coluna)
	{
		return matrizHeroi[linha][coluna];
		
	}
}
