package org.com.sandes.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_tb")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(
            nullable = false, length = 80)
    private String titulo;

    @Column(
            nullable = false, length = 100)
    private String descricao;

    @Column
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column
    private Boolean concluida = false;

    public Task() {
    }

    public Task(UUID id, String titulo, String descricao, LocalDateTime dataCracao, Boolean concluida) {
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
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
