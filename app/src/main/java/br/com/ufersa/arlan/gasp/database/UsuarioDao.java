package br.com.ufersa.arlan.gasp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.ufersa.arlan.gasp.beans.Usuario;


@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE email = :email AND senha = :senha LIMIT 1")
    public Usuario getUser(String email, String senha);

    @Query("SELECT * FROM usuario WHERE cpf = :cpf LIMIT 1")
    public Usuario getUserByCpf(String cpf);

    @Insert
    void insertAll(List<Usuario> usuarios);

    @Insert
    void insert(Usuario usuario);

    @Update
    void update(Usuario usuario);

    @Delete
    void delete(Usuario usuario);

}
