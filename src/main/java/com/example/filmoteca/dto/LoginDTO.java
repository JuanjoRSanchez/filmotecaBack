package com.example.filmoteca.dto;

import java.util.Objects;

public class LoginDTO {

    private String name;

    private int valor;

    public LoginDTO() {
    }

    public LoginDTO(String name, int valor) {
        this.name = name;
        this.valor = valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "LoginDTO [name=" + name + ", valor=" + valor + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LoginDTO other = (LoginDTO) obj;
        return Objects.equals(name, other.name) && valor == other.valor;
    }


}
