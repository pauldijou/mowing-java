package org.mowing.model;

import java.util.ArrayList;
import java.util.List;

import org.mowing.app.Util;

public class Map {
    int topRightX;
    int topRightY;
    List<Mower> mowers;
    List<List<Square>> squares;
    
    /**
     * Init the map with a matrix of squares based on the coordinates 
     * of the top-right square
     * @param topRightX
     * @param topRightY
     */
    public Map(int topRightX, int topRightY) {
        super();
        Util.msg("Init map ("+topRightX+", "+topRightY+")");
        this.topRightX = topRightX;
        this.topRightY = topRightY;
        
        mowers = new ArrayList<Mower>();
        squares = new ArrayList<List<Square>>();
        for(int x = 0; x <= this.topRightX; ++x) {
            squares.add(new ArrayList<Square>());
            for(int y = 0; y <= this.topRightY; ++y) {
                squares.get(x).add(new Square(x,y));
            }
        }
    }
    
    /**
     * Add a mow to the map. Will mow the landed square.
     * @param mower
     */
    public void addMower(Mower mower) {
        if(isOutside(mower.x, mower.y)) {
            Util.msg("Dude, you can't put a mower outside of the field... "+mower.toString()+" will be ignored.");
        } else if(hasMowerAt(mower.x, mower.y)) {
            Util.msg("Hey man, are you trying to fusion two mowers? "+mower.toString()+" will be ignored.");
        } else {
            mower.map = this;
            getSquare(mower.x, mower.y).mow();
            mowers.add(mower);
        }
    }
    
    /**
     * Run all map mowers one after another
     */
    public void runMowers() {
        for(Mower mower : this.mowers) {
            mower.run();
        }
    }
    
    /**
     * Test if there is a mower at the coordinates
     * @param x
     * @param y
     * @return
     */
    public boolean hasMowerAt(int x, int y) {
        return getMowerAt(x, y) != null;
    }
    
    /**
     * Retrieve the mower at the coordinates. Null if there is none.
     * @param x
     * @param y
     * @return
     */
    public Mower getMowerAt(int x, int y) {
        for(Mower mower : this.mowers) {
            if(mower.isAt(x, y)) {
                return mower;
            }
        }
        return null;
    }
    
    /**
     * Test if coordinates are outside the map
     * @param x
     * @param y
     * @return
     */
    public boolean isOutside(int x, int y) {
        return (x < 0) || (y < 0) || (x > this.topRightX) || (y > this.topRightY);
    }
    
    /**
     * Retrieve the square at the given coordinates or null if it's outside
     * @param x
     * @param y
     * @return
     */
    Square getSquare(int x, int y) {
        if(!isOutside(x, y)) {
            return this.squares.get(x).get(y);
        } else {
            return null;
        }
    }
}
