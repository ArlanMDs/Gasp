package br.com.ufersa.arlan.gasp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.ufersa.arlan.gasp.beans.Servico;
import br.com.ufersa.arlan.gasp.beans.Usuario;


@Database(entities = {Usuario.class, Servico.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "appDatabase.db";
    private static volatile AppDatabase appDatabase;

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = create(context);
        }
        return appDatabase;
    }
    //The build just creates the configuration for the database so you can call this from the main thread.
    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME)
                .fallbackToDestructiveMigration()// esse método destroi a bd antigo se a versão mudar.
                .build();
    }

    public abstract ServicoDao servicoDao();
    public abstract UsuarioDao usuarioDao();
}
