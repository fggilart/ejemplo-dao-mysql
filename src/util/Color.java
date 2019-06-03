package util;

public enum Color {
    
    EXITO("\u001B[32m"),
    ERROR("\u001B[31m"),
    INFO("\u001B[34m"),
    ALERTA("\u001B[33m"),
    DEFAULT("\u001B[0m");

    String color;

    private Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
    
    

}