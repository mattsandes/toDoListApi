package org.com.sandes.model.dtos;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TaskDTO {

    private UUID id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private Boolean concluida = false;

    public TaskDTO() {
    }

    public TaskDTO(UUID id, String titulo, String descricao, LocalDateTime dataCriacao, Boolean concluida) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.concluida = concluida;
    }

    public UUID getId() {
        return this.id;
    }

    public void setID(UUID id) {
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
        return this.dataCriacao;
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

        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) && Objects.equals(titulo, taskDTO.titulo) && Objects.equals(descricao, taskDTO.descricao) && Objects.equals(dataCriacao, taskDTO.dataCriacao) && Objects.equals(concluida, taskDTO.concluida);
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
}
