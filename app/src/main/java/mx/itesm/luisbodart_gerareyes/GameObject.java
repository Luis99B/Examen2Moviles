package mx.itesm.luisbodart_gerareyes;

public class GameObject {
    private String nombre,
            anio;
    private String[] plataformas;


    public GameObject(String nombre, String anio, String[] plataformas) {
        this.nombre = nombre;
        this.anio = anio;
        this.plataformas=plataformas;

    }


    public String getNombre() {
        return nombre;
    }

    public String getAnio() {
        return anio;
    }

    public String[] getPlataformas() {
        return plataformas;
    }
}