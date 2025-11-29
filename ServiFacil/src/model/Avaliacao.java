package model;

public class Avaliacao {
    private int id;
    private int nota; // de 1 a 5
    private String comentario;
    private static int proximoId = 1;

    public Avaliacao(int nota, String comentario) {
        this.id = proximoId++;
        this.nota = nota;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
