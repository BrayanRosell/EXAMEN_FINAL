package com.example.t4_final.Moduls;

public class Libro {
    private String titulo;
    private String resumen;
    private String autor;
    private String fecha;
    private String tienda1;
    private String tienda2;
    private String tienda3;

    public  Libro(){}

    public Libro(String titulo, String resumen, String autor, String fecha, String tienda1, String tienda2, String tienda3) {
        this.titulo = titulo;
        this.resumen = resumen;
        this.autor = autor;
        this.fecha = fecha;
        this.tienda1 = tienda1;
        this.tienda2 = tienda2;
        this.tienda3 = tienda3;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTienda1() {
        return tienda1;
    }

    public void setTienda1(String tienda1) {
        this.tienda1 = tienda1;
    }

    public String getTienda2() {
        return tienda2;
    }

    public void setTienda2(String tienda2) {
        this.tienda2 = tienda2;
    }

    public String getTienda3() {
        return tienda3;
    }

    public void setTienda3(String tienda3) {
        this.tienda3 = tienda3;
    }
}
