package com.example.map;

/**
 * CURRENT STATE (BROKEN ON PURPOSE):
 * A style object exists, but is mutable and is created per marker,
 * even when thousands of markers share the same config.
 *
 * TODO (student):
 * - Make this an immutable Flyweight (final fields, no setters).
 */
public class MarkerStyle {

    private final String shape;   // e.g., PIN, CIRCLE, SQUARE
    private final String color;   // e.g., RED, BLUE, GREEN
    private final int size;       // e.g., 10..20
    private final boolean filled; // filled vs outline

    private MarkerStyle(Builder builder) {
        this.shape = builder.shape;
        this.color = builder.color;
        this.size = builder.size;
        this.filled = builder.filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize() { return size; }
    public boolean isFilled() { return filled; }

    // BROKEN: setters should go away after immutability refactor
    // public void setShape(String shape) { this.shape = shape; }
    // public void setColor(String color) { this.color = color; }
    // public void setSize(int size) { this.size = size; }
    // public void setFilled(boolean filled) { this.filled = filled; }

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
    }

    public static class Builder {
        private String shape;   
        private String color;   
        private int size;       
        private boolean filled;

        public Builder shape(String shape) {
            this.shape = shape;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder filled(boolean filled) {
            this.filled = filled;
            return this;
        }

        public MarkerStyle build() {
            return new MarkerStyle(this);
        }   
    } 
}
