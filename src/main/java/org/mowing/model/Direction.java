package org.mowing.model;

public enum Direction {
    N,
    E,
    S,
    W;
    
    public Direction toLeft() {
        switch (this) {
            case N:
                return W;
            case W:
                return S;
            case S:
                return E;
            default:
                return N;
        }
    }
    
    public Direction toRight() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            default:
                return N;
        }
    }
}
