package org.mowing.model;

public class Square {
    int x;
    int y;
    boolean mowed = false;
    
    public Square(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
    
    /**
     * Mow the square! One less to cut.
     */
    public void mow() {
        this.mowed = true;
    }
}
