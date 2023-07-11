using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using System;
using System.Diagnostics;
using System.Globalization;
using Debug = UnityEngine.Debug;
using UnityEngine.SceneManagement;

public class CheckersBoard : MonoBehaviour {
    public Piece[,] Pieces = new Piece[8, 8];
    public GameObject WhitePiecePrefab;
    public GameObject BlackPiecePrefab;

    private Vector3 boardOffset = new Vector3(-4.0f, 0f, -4.0f);
    private Vector3 pieceOffset = new Vector3(0.5f, 0f, 0.5f);

    private bool isWhiteTurn;
    public bool isWhite;
    private bool hasKilled;

    private Piece selectedPiece;
    private List<Piece> forcedPieces;

    private Vector2 MouseOver;
    private Vector2 startDrag;
    private Vector2 endDrag;
	int staph = 0;
	int counter =0;
	bool sixnine = false;

	// Use this for initialization
	void Start () {
        isWhiteTurn = true;
        isWhite = true;
        forcedPieces = new List<Piece>();
        GenerateBoard();
		File.WriteAllText ("lastMove.txt",null);
		File.WriteAllText ("gameMovements.txt",null);

	}
	
	// Update is called once per frame
	void Update () {
        UpdateMouseOver();

        if((isWhite)?isWhiteTurn : !isWhiteTurn){


			if (isWhiteTurn) {

				int x = (int)MouseOver.x;
				int y = (int)MouseOver.y;


				if (selectedPiece != null)
					UpdatePieceDrag (selectedPiece);

				if (Input.GetMouseButtonDown (0))
					SelectPiece (x, y);

				if (Input.GetMouseButtonUp (0)) {
					TryMove ((int)startDrag.x, (int)startDrag.y, x, y);
				//	Debug.Log ("x cordinate: " + x + "y cordinate: " + y);
				}
				// here, we take startDrag.x and startDrag.y and we do caculation with x and y. but perhaps its better implemented inside TryMove

				CheckVictory ();
			} 
			else
			{
				CheckVictory ();
				
				if (staph == 0) {
					Debug.Log (++counter);
					Process p1 = new Process ();
					p1.StartInfo.FileName = "java";
					//p1.StartInfo.WorkingDirectory = "";
					//p1.StartInfo.UseShellExecute = true;
					p1.StartInfo.Arguments = "-jar javaGameAi.jar"; // path needs to change later on
					p1.Start ();

				//
				p1.WaitForExit ();
					staph++;
					int x1, y1, x2, y2;

				

					var lines = File.ReadAllText ("lastMove.txt");
					File.WriteAllText ("lastMove.txt", null);
					string[] words = lines.Split (' ');
					x1 = int.Parse (words [0]);
					if (x1 == 6976) {
						Debug.Log ("6976 found");
						sixnine = true;
						x1 = int.Parse (words [1]);
						Debug.Log ("x1: " + x1);
						y1 = int.Parse (words [2]);
						Debug.Log ("y1: " + y1);
						x2 = int.Parse (words [3]);
						Debug.Log ("x2: " + x2);
						y2 = int.Parse (words [4]);
						Debug.Log ("y2: " + y2);

					} 
					 
					else if(x1 == 6969) {
							SceneManager.LoadScene ("victory");
						y1 = 6969;

							x2 = 6969;
						y2 = 6969;

						}
					else{
						y1 = int.Parse (words [1]);

						x2 = int.Parse (words [2]);
						y2 = int.Parse (words [3]);

					}
					// i think that the cause is that, it tried to make an illegal move
					Debug.Log("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2 );
					TryMove (x1, y1, x2, y2);
					staph = 0;

				}
			}
        }
	}

    //Function to let value when click
    private void UpdateMouseOver()
    {

        RaycastHit hit;
        if (Physics.Raycast(Camera.main.ScreenPointToRay(Input.mousePosition), out hit, 25.0f, LayerMask.GetMask("Board")))
        {
            //unity.... go to board item, Layer--> add Layer--> Board, then go to board item, Layer--> Board
            MouseOver.x = (int)(hit.point.x - boardOffset.x);		//"- boardOffset.x"to let value on Debug message be more reallity
            MouseOver.y = (int)(hit.point.z - boardOffset.z);		//"- boardOffset.z"to let value on Debug message be more reallity
        }
        else
        {
            MouseOver.x = -1;
            MouseOver.y = -1;
        }


    }

    //Optional function that let you move the piece when move it <called by Update>
    private void UpdatePieceDrag(Piece p)
    {

        RaycastHit hit;
        if (Physics.Raycast(Camera.main.ScreenPointToRay(Input.mousePosition), out hit, 25.0f, LayerMask.GetMask("Board")))
        {
            p.transform.position = hit.point + Vector3.up;
        }
    }

    // check if the clecked position are piece.... if yes then the piece be selected <called by Update>
    private void SelectPiece(int x, int y){
		//if out of the board range
		if(x < 0 || x > 8 || y < 0 || y > 8)
			return;
		
		Piece p = Pieces[x, y];
        if (p != null && p.isWhite == isWhite)
        {
            if (forcedPieces.Count == 0)
            {
                selectedPiece = p;
			    startDrag = MouseOver;
            //    Debug.Log(selectedPiece.name);
            }
            else
            {
                //look at the piece under our forced pieces list
                if (forcedPieces.Find(fp => fp == p) == null)
                    return;

                selectedPiece = p;
                startDrag = MouseOver;
            }
			
			
		}
	}

    //Function move the piece if it is possible
    private void TryMove(int x1, int y1, int x2, int y2)
    {
        forcedPieces = ScanForPossibleMove();

        //Multiplayer Support
        startDrag = new Vector2(x1, y1);
        endDrag = new Vector2(x2, y2);
        selectedPiece = Pieces[x1, y1];

        MovePiece(selectedPiece, x2, y2);

        //Check if we are out of bound
        if (x2 < 0 || x2 > 8 || y2 < 0 || y2 > 8)
        {
            if (selectedPiece != null)
                MovePiece(selectedPiece, x1, y1);
			
			selectedPiece = null;
			startDrag = Vector2.zero;
			return;
		}
        //if there a selected Piece
        if (selectedPiece != null)
        {

            //if it hasn't moved
            if (endDrag == startDrag)
            {
                MovePiece(selectedPiece, x1, y1);

                selectedPiece = null;
                startDrag = Vector2.zero;
                return;
            }
        }

        //if it is valid move
        if (selectedPiece.ValidMove(Pieces, x1, y1, x2, y2))
        {

            //check if we jump above piece
            if (Mathf.Abs(x2 - x1) == 2)
            {	Piece p = Pieces[(x1 + x2) / 2, (y1 + y2) / 2];
                //kill piece(we need destroy the piece)
                if (p != null)
                {
                    Pieces[(x1 + x2) / 2, (y1 + y2) / 2] = null;
                    Destroy(p.gameObject); //edit
                    hasKilled = true;
                }
            }

            //where we supposed to kill anything?
            if (forcedPieces.Count != 0 && !hasKilled)
            {

                MovePiece(selectedPiece, x1, y1);
                selectedPiece = null;
                startDrag = Vector2.zero;
                return;
            }




            Pieces[x2, y2] = selectedPiece;
            Pieces[x1, y1] = null;
            //save the new position as deffault
            MovePiece(selectedPiece, x2, y2);
			if (!sixnine) 
			{  
				//var lines = File.ReadAllText ("lastMove.txt");
				//lines +
				if(isWhite)
				File.AppendAllText("lastMove.txt", x1 + " " + y1 + " " + x2 + " " + y2 + " ");
				
				Debug.Log ("we're here");
			}
			else {
				File.WriteAllText ("lastMove.txt", "6976");
				sixnine = false;
			}

            EndTurn();
        }
        //
        else
        {
            MovePiece(selectedPiece, x1, y1);
            selectedPiece = null;
            startDrag = Vector2.zero;
            return;
        }

    }

    //to end the turn and let the other team play <called by TryMove>
    private void EndTurn()
    {
        int x = (int)endDrag.x;
        int y = (int)endDrag.y;

        //Promotions
        if (selectedPiece != null)
        {
            //reach the end of board to be king(for white piece)
            if (selectedPiece.isWhite && !selectedPiece.isKing && y == 7)
            {
                selectedPiece.isKing = true;
                selectedPiece.transform.Rotate(Vector3.right * 180);
            }

            //reach the end of board to be king(for black piece)
            else if (!selectedPiece.isWhite && !selectedPiece.isKing && y == 0)
            {
                selectedPiece.isKing = true;
                selectedPiece.transform.Rotate(Vector3.right * 180);
            }
        }



        selectedPiece = null;
        startDrag = Vector2.zero;

        if (ScanForPossibleMove(selectedPiece, x, y).Count != 0 && hasKilled)
            return;

        isWhiteTurn = !isWhiteTurn;
        isWhite = !isWhite;
        hasKilled = false;
        CheckVictory();
    }

    //Check if the team win <called by EndTurn>
    //End the game
    private void CheckVictory()
    {
        var ps = FindObjectsOfType<Piece>();
        bool hasWhite = false;
        bool hasBlack = false;
        for (int i = 0; i < ps.Length; i++)
        {
            if (ps[i].isWhite)
                hasWhite = true;
            else
                hasBlack = true;
        }


        if (!hasWhite)
            Victory(false);
        if (!hasBlack)
            Victory(true);
    }

    private void Victory(bool isWhite){

		if (isWhite) {
			Debug.Log ("what happend?");
			SceneManager.LoadScene ("victory");

		} else {
			Debug.Log ("was this executed?");
			SceneManager.LoadScene ("defeat");
			Debug.Log ("what about now");
		}
        //    Debug.Log("White team has WON");
       // else
        //    Debug.Log("Black team has WON");
	}

    private List<Piece> ScanForPossibleMove(Piece p, int x, int y)
    {

        forcedPieces = new List<Piece>();

        if (Pieces[x, y].IsForceToMove(Pieces, x, y))
            forcedPieces.Add(Pieces[x, y]);

        return forcedPieces;
    }

    private List<Piece> ScanForPossibleMove()
    {

        forcedPieces = new List<Piece>();

        //check all the pieces
        for (int i = 0; i < 8; i++)
            for (int j = 0; j <8; j++)
                if (Pieces[i, j] != null && Pieces[i, j].isWhite == isWhiteTurn)
                    if (Pieces[i, j].IsForceToMove(Pieces, i, j))
                        forcedPieces.Add(Pieces[i, j]);

        return forcedPieces;
    }

    //create board to start a new game
    private void GenerateBoard()
    {

        //generate white team
        for (int y = 0; y < 3; y++)
        {
            bool oddrow = (y % 2 == 0);
            for (int x = 0; x < 8; x += 2)
            {
                //generate the white pieces
                //if it is oddrow then put x, otherwise put x+1
                GeneratePiece((oddrow) ? x : x + 1, y);
            }
        }
        //generate black team 
        for (int y = 7; y > 4; y--)
        {
            bool oddrow = (y % 2 == 0);
            for (int x = 0; x < 8; x += 2)
            {
                //generate the black pieces
                //if it is oddrow then put x, otherwise put x+1
                GeneratePiece((oddrow) ? x : x + 1, y);
            }
        }
    }

    //create peace< called by GenerateBoard >
    private void GeneratePiece(int x, int y){
		bool isPieceWhite = (y < 3)? true : false;
		
		GameObject go = Instantiate((isPieceWhite)? WhitePiecePrefab : BlackPiecePrefab) as GameObject;
		go.transform.SetParent(transform);
		Piece p = go.GetComponent<Piece>();
		Pieces[x,y] = p;
		//unity......need to move Piece script to WhitePiece & BlackPiece objects

        MovePiece(p, x, y);
	}

    //Move Piece to deffult position to start the game < called by GeneratePiece >
    private void MovePiece(Piece p, int x, int y)
    {
        p.transform.position = (Vector3.right * x) + (Vector3.forward * y) + boardOffset + pieceOffset;
    }

}
