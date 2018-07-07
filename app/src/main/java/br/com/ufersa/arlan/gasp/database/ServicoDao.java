package br.com.ufersa.arlan.gasp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.ufersa.arlan.gasp.beans.Servico;

@Dao
public interface ServicoDao {

    @Query("SELECT * FROM Servico")
    List<Servico> getAll();

   // @Query("SELECT * FROM watersample WHERE name LIKE :name LIMIT 1")
  //  Product findByName(String name);

    @Insert
    void insertAll(List<Servico> servicos);

    @Insert
    void insert(Servico servico);

    @Update
    void update(Servico servico);

    @Delete
    void delete(Servico servico);

}
