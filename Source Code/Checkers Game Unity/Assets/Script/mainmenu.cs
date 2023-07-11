using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System.IO;

public class mainmenu : MonoBehaviour {
	bool minimax = true;


	public void	toggleAiminimax()
	{
		minimax = true;
		
	}
	public void toggleAiSA()
	{
		minimax = false;
	}

	public void play()
	{
		string x;
		if (minimax)
			x = "minimax";
		else
			x = "annealing";
		File.WriteAllText ("aitype.txt", x);
		SceneManager.LoadScene ("MyGame");
	}

	public void exit()
	{
		Application.Quit ();
	}

	// Use this for initialization
//	void Start () {
		
//	}
	
	// Update is called once per frame
//	void Update () {
		
//	}
}
