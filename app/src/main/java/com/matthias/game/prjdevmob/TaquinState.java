package com.matthias.game.prjdevmob;

public class TaquinState {
    Integer[] game;
    int depth;
    int h;
    int cout;
    int movecode;
    public TaquinState(Integer[] t, int d) {
        depth = d;
        game = t;
        movecode = -1;
        h = calcHeuristique();
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

    public int getCout() {
        return h+depth;
    }

    public int getH() {
        h = calcHeuristique();
        return h;
    }

    public int calcHeuristique() {
        int sdistance =0;
        for (int i=0; i < game.length;i++) {
            int x = i%4;
            int y = i/4;

            if (game[i]!= null) {
                int x1 = game[i]%4;
                int y1 = game[i]/4;
                sdistance = Math.abs(x1-x) + Math.abs(y1-y) + sdistance;
            }else {
                sdistance = Math.abs(3-x) + Math.abs(3-y) + sdistance;
            }
        }
        return sdistance;
    }

    public Integer[] getGame() {
        return game;
    }

    public int getMovecode(){
        return movecode;
    }

    public void moveUp() {
        GameActivity.moveUp(game);
        movecode = 0;
    }

    public void moveDown() {
        GameActivity.moveDown(game);
        movecode = 1;
    }

    public void moveLeft() {
        GameActivity.moveLeft(game);
        movecode = 2;
    }

    public void moveRight() {
        GameActivity.moveRight(game);
        movecode = 3;
    }

    @Override
    public Object clone() {
        Integer[] gameClone = new Integer[16];
        for (int i = 0; i< getGame().length;i++) {
            gameClone[i] = getGame()[i];
        }
        TaquinState cloneState = new TaquinState(gameClone,getDepth());
        return cloneState;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof TaquinState) {
            TaquinState stateTaquin = (TaquinState) obj;
            for (int i=0; i<this.getGame().length; i++) {
                for (int j=0;i<stateTaquin.getGame().length;i++) {
                    if (this.getGame()[i] != stateTaquin.getGame()[i]) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }


}
