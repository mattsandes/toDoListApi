package org.com.sandes.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Task {

    private UUID id;
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Boolean concluida = false;

    public Task() {
    }

    public Task(UUID id, String titulo, String descricao, Date dataCracao, Boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCracao;
        this.concluida = concluida;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(titulo, task.titulo) && Objects.equals(descricao, task.descricao) && Objects.equals(dataCriacao, task.dataCriacao) && Objects.equals(concluida, task.concluida);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(titulo);
        result = 31 * result + Objects.hashCode(descricao);
        result = 31 * result + Objects.hashCode(dataCriacao);
        result = 31 * result + Objects.hashCode(concluida);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", concluida=" + concluida +
                '}';
    }
}
