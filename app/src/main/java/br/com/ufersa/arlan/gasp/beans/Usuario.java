package br.com.ufersa.arlan.gasp.beans;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cpf;
    private String cnpj;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
   // Se o cadastro for feito usando o CPF, o usuário é cliente. Se usar o CNPJ, é  prestador


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    // o room precisa desse construtor
    public Usuario(String cpf, String cnpj, String nome, String email, String senha, String telefone) {
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    private Usuario(final UsuarioBuilder builder) {
        cpf = builder.cpf;
        cnpj = builder.cnpj;
        nome = builder.nome;
        email = builder.email;
        senha = builder.senha;
        telefone = builder.telefone;
    }

    public static class UsuarioBuilder {
        private String cpf;
        private String cnpj;
        private String nome;
        private String email;
        private String senha;
        private String telefone;

        public UsuarioBuilder setCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public UsuarioBuilder setCnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public UsuarioBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public UsuarioBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UsuarioBuilder setSenha(String senha) {
            this.senha = senha;
            return this;
        }

        public UsuarioBuilder setTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public Usuario createUsuario() {
            return new Usuario(this);
        }
    }

}
