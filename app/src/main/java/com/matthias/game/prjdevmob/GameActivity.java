package com.matthias.game.prjdevmob;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private GameAdapter adapter; // Gestion de laffichage de la grille
    private GridView grid;
    private int moveCount;
    private TextView compt; // Texte our laffiche des coups effectués en cours
    private int solving; // si la resolution est lancé solving != -1 sinon elle prend pour valeur
                        //  0 pour un délacement vers le haut
                        //  1 pour un déplacement vers le bas
                        //  2 vers la droite et 3 vers la gauche
    private int solveIndex;

    // Bouton deplacement des cellules Haut, Bas, Gauche, Droite
    private Button up;
    private Button down;
    private Button left;
    private Button right;


    private  Integer inCell[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,null}; // Donnée initiale de la grille
    private ArrayList<Integer> moveList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        shuffle(inCell,2);
        solving = -1;
        moveCount = 0;
        solveIndex = 0;
        moveList = new ArrayList<>();
        compt = findViewById(R.id.compt);
        compt.setText(Integer.toString(moveCount,10));

        up = findViewById(R.id.btnup);
        down = findViewById(R.id.btndown);
        left = findViewById(R.id.btnleft);
        right = findViewById(R.id.btnright);


        grid = findViewById(R.id.gridview);
        adapter = new GameAdapter(this,inCell);
        grid.setAdapter(adapter);



    }





    public static void permTab(Integer[] tab, int n,int m){ // Permet de faire une permutation des
                                                            // des valeurs à deux indices dans un
                                                            // tableau
        Integer a = tab[n];
        tab[n] = tab[m];
        tab[m] = a;
    }

    public static int getN(int i, int j) { // transforme une coordonne i,j dans une grille 4x4
                                            // à une coordonnée à une dimention pour un tableau 16
                                            // elements
        return j*4+i;
    }

    //Gesttion des deplacents depuis un tableau

    public static void moveUp(Integer[] tab) {
        for (int i=0;i< tab.length;i++) {
            if(tab[i] == null) {
                int x = i%4;
                int y = (int) Math.floor(i/4);
                if(y!=0){
                    permTab(tab,getN(x,y),getN(x,y-1));
                }
                break;
            }
        }
    }

    public static void moveDown(Integer[] tab) {
        for (int i=0;i< tab.length;i++) {
            if(tab[i] == null) {
                int x = i%4;
                int y = (int) Math.floor(i/4);
                if(y!=3){
                    permTab(tab,getN(x,y),getN(x,y+1));
                }
                break;
            }
        }
    }

    public static void moveRight(Integer[] tab) {
        for (int i=0;i< tab.length;i++) {
            if(tab[i] == null) { //12
                int x = i%4; //0
                int y = (int) Math.floor(i/4); //3
                if(x!=3){ //1
                    permTab(tab,getN(x,y),getN(x+1,y)); //12 13
                }
                break;
            }
        }
    }

    public static void moveLeft(Integer[] tab) {
        for (int i=0;i< tab.length;i++) {
            if(tab[i] == null) {
                int x = i%4;
                int y = (int) Math.floor(i/4);
                if(x!=0){
                    permTab(tab,getN(x,y),getN(x-1,y));
                }
                break;
            }
        }
    }

    private void checkGame(Integer[] tab) { // verifie si le jeux est fini ou sinon incrémente le
                                            // Nombre de coups, lance lactivité EndGame quand le jeu
                                            // est terminé
        boolean a = true;
        for (int i=0;i<tab.length-1;i++) {
            if (tab[i] != null) {
                if (tab[i] == i) {
                    a= true && a;
                } else {
                    a= false && a;
                    break;
                }
            } else { a = false && a; break; }


        }




        moveCount ++;
        compt.setText(Integer.toString(moveCount,10));
        if (a) {
            Intent intent = new Intent(GameActivity.this, EndGame.class);
            intent.putExtra("count", moveCount);
            this.startActivity(intent);
        }
    }

    private void shuffle(Integer[] tab,int n) { // mélange du tableau ( des cellules ), n etant
                                                // le nombre de deplacement aleatoire appliqué au
                                                // tableau

        for (int i=0;i<(n+1);i++) {
            int a = (int) Math.floor(Math.random()*4);
            if( a == 0) {
                moveUp(tab);
            } else if (a==1) {
                moveDown(tab);
            } else if (a==2) {
                moveLeft(tab);
            } else {
                moveRight(tab);
            }
        }


    }

    // implementation des buttons lier au délacement

    public void btnUp(View v) {
        if (solving==0) {
            moveUp(inCell);
            if (solveIndex < moveList.size()) {
                solving = moveList.get(solveIndex++);
            }
            updateBtnColor(solving);
        } else if (solving ==-1) {
            moveUp(inCell);

        }

        adapter = new GameAdapter(this,inCell);
        grid.setAdapter(adapter);
        grid.invalidate();
        checkGame(inCell);
    }

    public void btnDown(View v) {
        if (solving==1) {
            moveDown(inCell);
            if (solveIndex < moveList.size()) {
                solving = moveList.get(solveIndex++);
            }
            updateBtnColor(solving);
        } else if (solving ==-1) {
            moveDown(inCell);
        }

        adapter = new GameAdapter(this,inCell);
        grid.setAdapter(adapter);
        grid.invalidate();
        checkGame(inCell);
    }

    public void btnRight(View v) {
        if (solving==3) {
            moveRight(inCell);
            if (solveIndex < moveList.size()) {
                solving = moveList.get(solveIndex++);
            }

            updateBtnColor(solving);
        } else if (solving ==-1) {
            moveRight(inCell);
        }

        adapter = new GameAdapter(this,inCell);
        grid.setAdapter(adapter);
        grid.invalidate();
        checkGame(inCell);
    }

    public void btnLeft(View v) {
        if (solving==2) {
            moveLeft(inCell);
            if (solveIndex < moveList.size()) {
                solving = moveList.get(solveIndex++);
            }
            updateBtnColor(solving);
        } else if (solving ==-1) {
            moveLeft(inCell);
        }

        adapter = new GameAdapter(this,inCell);
        grid.setAdapter(adapter);
        grid.invalidate();
        checkGame(inCell);
    }

    public void solve(View v) {
        TaquinSolveur solution = new TaquinSolveur(inCell);
        moveList = solution.solve();
        solving = moveList.get(solveIndex++);
        updateBtnColor(solving);
    }


    public void updateBtnColor(int moveid) {
        up.setBackgroundColor(getResources().getColor(R.color.btn));
        down.setBackgroundColor(getResources().getColor(R.color.btn));
        left.setBackgroundColor(getResources().getColor(R.color.btn));
        right.setBackgroundColor(getResources().getColor(R.color.btn));

        if(moveid == 1) {
            up.setBackgroundColor(getResources().getColor(R.color.btnsol));
        } else if (moveid == 0) {
            down.setBackgroundColor(getResources().getColor(R.color.btnsol));
        } else if (moveid == 3) {
            left.setBackgroundColor(getResources().getColor(R.color.btnsol));
        } else if (moveid == 2) {
            right.setBackgroundColor(getResources().getColor(R.color.btnsol));
        }
        up.invalidate();
        down.invalidate();
        left.invalidate();
        right.invalidate();
    }

}