using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Piece : MonoBehaviour {
    public bool isWhite;
	public bool isKing;


    //kill role
    public bool IsForceToMove(Piece[,] board, int x, int y)
    {
        //if the selected piece is white
        if (isWhite || isKing)
        {
            //Top left
            if (x >= 2 && y <= 5)
            {
                Piece p = board[x - 1, y + 1];
                //check if there is piece in different color to kill
                if (p != null && p.isWhite != isWhite)
                {
                    //check if can land after kill
                    if (board[x - 2, y + 2] == null)
                        return true;
                }
            }

            //Top Right
            if (x <= 5 && y <= 5)
            {
                Piece p = board[x + 1, y + 1];
                //check if there is piece in different color to kill
                if (p != null && p.isWhite != isWhite)
                {
                    //check if can land after kill
                    if (board[x + 2, y + 2] == null)
                        return true;
                }
            }
        }
        //if the selected piece is black
        if (!isWhite || isKing)
        {
            //Bottom left
            if (x >= 2 && y >= 2)
            {
                Piece p = board[x - 1, y - 1];
                //check if there is piece in different color to kill
                if (p != null && p.isWhite != isWhite)
                {
                    //check if can land after kill
                    if (board[x - 2, y - 2] == null)
                        return true;
                }
            }

            //Bottom Right
            if (x <= 5 && y >= 2)
            {
                Piece p = board[x + 1, y - 1];
                //check if there is piece in different color to kill
                if (p != null && p.isWhite != isWhite)
                {
                    //check if can land after kill
                    if (board[x + 2, y - 2] == null)
                        return true;
                }
            }

        }
        return false;

    }

	//check if the the piece can move to the target
	//in this function you can add X mark when the move is available (when return true)
    public bool ValidMove(Piece[,] board, int x1, int y1, int x2, int y2)
    {

        //if you are moving above another piece
        if (board[x2, y2] != null)
            return false;

        int deltaMove = Mathf.Abs(x1 - x2);
        int deltaMoveY = y2 - y1;






        //for white team
        if (isWhite || isKing)
        {

            //Normal Jump
            if (deltaMove == 1 && deltaMoveY == 1)
            {
                return true;
            }

            //Kill Jump
            else if (deltaMove == 2 && deltaMoveY == 2)
            {

                Piece p = board[(x1 + x2) / 2, (y1 + y2) / 2];
                if (p != null && p.isWhite != isWhite)
                    return true;
            }
        }

        //for black team
        if (!isWhite || isKing)
        {

            //Normal Jump
            if (deltaMove == 1 && deltaMoveY == -1)
            {
                return true;
            }

            //Kill Jump
            else if (deltaMove == 2 && deltaMoveY == -2)
            {

                Piece p = board[(x1 + x2) / 2, (y1 + y2) / 2];
                if (p != null && p.isWhite != isWhite)
                    return true;
            }
        }

        return false;
    }
}
