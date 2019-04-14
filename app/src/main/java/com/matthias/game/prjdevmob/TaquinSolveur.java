package com.matthias.game.prjdevmob;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TaquinSolveur {

    ArrayList<TaquinState> states;
    ArrayList<TaquinState> open;
    ArrayList<TaquinState> close;
    ArrayList<Integer> solution;
    ArrayList<Integer> depth;

    public TaquinSolveur(Integer[] tab) {
        states = new ArrayList<>();
        close = new ArrayList<>();
        open = new ArrayList<>();
        solution = new ArrayList<>();
        depth = new ArrayList<>();
        open.add(new TaquinState(tab,0));
    }

    public ArrayList<Integer> solve() { // resolution du jeux en utilisant l'algorithme a* ?
        ArrayList<Integer> solution = new ArrayList<>();
        while (!open.isEmpty()) {
            for (int i=0; i< open.size();i++) {

                int minIndice =0;
                int minVal= open.get(minIndice).getCout();
                for (int k=0;k<open.size();k++) {
                    if (open.get(k).getCout()< minIndice) {
                        minIndice = k;
                    }
                }
                if(!close.contains(open.get(minIndice))) {
                    close.add(open.get(minIndice));
                }


                states = findMove(open.get(i));


                for (TaquinState state : states) {
                    if(state.getH() == 0) {

                        for (TaquinState sol : close) {
                            solution.add(sol.getMovecode());
                        }
                        solution.add(state.getMovecode());
                        return solution;
                    } else {

                        if (!open.contains(state) && !close.contains(state)) {
                            open.add(state);

                        } else if (open.contains(state) && (open.get(open.indexOf(state)).getCout() < state.getCout())) {
                            open.remove(open.indexOf(state));
                            open.add(state);
                        } else if ((close.contains(state)) && (close.get(close.indexOf(state)).getCout() < state.getCout())) {
                            close.remove(close.indexOf(state));
                            open.add(state);
                        }


                    }


                }


            }
        }
        return solution;
    }
    /*
    public ArrayList<Integer> solve1() {
        ArrayList<Integer> solution = new ArrayList<>();
        ArrayList<TaquinState> openP = new ArrayList<>();
        ArrayList<TaquinState> closeP = new ArrayList<>();
        openP.add(open.get(0));
        boolean fin = false;
        while (!(fin || openP.isEmpty())) {
            TaquinState current = best(openP);
            openP.remove(openP.indexOf(current));
            closeP.add(current);
            states = findMove(current);
            if(current.getH() ==0) {
                fin =true;
            } else {
                for (TaquinState state: states) {
                    if (!closeP.contains(state)) {
                        openP.add(state);
                    } else {
                        if (state.getCout() < closeP.get(closeP.size()-1).getCout()) {
                            closeP.remove(closeP.size()-1);
                            openP.add(state);
                        }
                    }
                }
            }
        }

        solution.add(0);
        return solution;

    } */

    public TaquinState best(ArrayList<TaquinState> q) {

        int minIndice =0;

        int minVal= q.get(minIndice).getCout();
        for (int k=0;k<q.size();k++) {
            if (q.get(k).getCout()< minIndice) {
                minIndice = k;
            }
        }
        return q.get(minIndice);
    }


    public ArrayList<TaquinState> findMove(TaquinState s) {
        int nullInd = 0;
        for (int i=0;i<s.getGame().length;i++) {
            if (s.getGame()[i]==null) {
                nullInd = i;
                break;
            }
        }

        int i = nullInd%4;
        int j = nullInd/4;

        Integer tmp[] = new Integer[16];

        ArrayList<TaquinState> nextMoves = new ArrayList<>();



        if(j!=0){
            tmp = s.getGame().clone();
            TaquinState taquinMove = new TaquinState(tmp,s.getDepth()+1);
            taquinMove.moveUp();
            nextMoves.add(taquinMove);

        }

        if(j!=3) {
            tmp = s.getGame().clone();
            TaquinState taquinMove = new TaquinState(tmp,s.getDepth()+1);
            taquinMove.moveDown();
            nextMoves.add(taquinMove);
        }

        if(i!=0){
            tmp = s.getGame().clone();
            TaquinState taquinMove = new TaquinState(tmp,s.getDepth()+1);
            taquinMove.moveLeft();
            nextMoves.add(taquinMove);
        }

        if(i!=3) {
            tmp = s.getGame().clone();
            TaquinState taquinMove = new TaquinState(tmp,s.getDepth()+1);
            taquinMove.moveRight();
            nextMoves.add(taquinMove);
        }



        return nextMoves;

    }

    private void permTab(Integer[] tab, int n,int m){
        Integer a = tab[n];
        tab[n] = tab[m];
        tab[m] = a;
    }

    private int getN(int i, int j) {
        return j*4+i;
    }


}
