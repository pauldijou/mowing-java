package org.mowing.model;

import java.util.List;

import org.mowing.app.Util;

public class Mower {
    int id;
    int x;
    int y;
    Direction direction;
    List<Order> orders;
    Map map;
    
    public Mower(int id, int x, int y, Direction direction, List<Order> orders) {
        super();
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.orders = orders;
    }
    
    /**
     * Run all orders of the mower and display the result of each one
     */
    public void run() {
        Util.msg("###############################################");
        Util.msg("");
        Util.msg("Run "+this.toString()+", orders: "+this.orders.toString());
        Util.msg("");
        for(Order order : orders) {
            move(order);
        }
        Util.msg("");
        Util.msg("Done for "+this.toString());
        Util.msg("");
    }
    
    /**
     * Test if a mower is at the given coordinates
     * @param x
     * @param y
     * @return
     */
    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    /**
     * Execute an order
     * @param order
     */
    private void move(Order order) {
        if(Order.A.equals(order)) {
            advance();
        } else {
            rotate(order);
        }
    }
    
    /**
     * Advance of one square based on the current direction.
     * Will fail if the destination is outside the map or contains 
     * another mower. In these cases, the mower will stay at its position.
     */
    private void advance() {
        int newX = this.x;
        int newY = this.y;
        
        switch (this.direction) {
            case N:
                ++newY;
                break;
            case E:
                ++newX;
                break;
            case S:
                --newY;
                break;
            case W:
                --newX;
                break;
        }
        
        if(this.map.isOutside(newX, newY)) {
            // You can't go outside of the map
            Util.msg("  I don't know who gives orders but "+this.toString()+" just tried to go outside at ("+newX+","+newY+")");
        } else if(this.map.hasMowerAt(newX, newY)) {
            // Please, don't destroy mowers by crashing them
            Mower destinationMower = this.map.getMowerAt(newX, newY);
            Util.msg("  Saperlipopette!! We just avoid a crash between "+this.toString()+" and "+ destinationMower.toString());
        } else {
            Util.msg("  Advancing from ("+this.x+","+this.y+", "+this.direction.toString()+") to ("+newX+","+newY+", "+this.direction.toString()+")");
            this.x = newX;
            this.y = newY;
            this.map.getSquare(this.x, this.y).mow();
        }
    }
    
    /**
     * Will do a rotation based on the order (which must be 'G' or 'D')
     * @param order
     */
    private void rotate(Order order) {
        if(Order.G.equals(order)) {
            rotateLeft();
        } else {
            rotateRight();
        }
    }
    
    private void rotateLeft() {
        Util.msg("  Rotating left from "+this.direction.toString()+" to "+this.direction.toLeft().toString());
        this.direction = this.direction.toLeft();
    }
    
    private void rotateRight() {
        Util.msg("  Rotating right from "+this.direction.toString()+" to "+this.direction.toRight().toString());
        this.direction = this.direction.toRight();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mower #").append(this.id).append(" at ");
        sb.append("(").append(this.x).append(",").append(this.y);
        sb.append(",").append(this.direction.toString()).append(")");
        return sb.toString();
    }

}
