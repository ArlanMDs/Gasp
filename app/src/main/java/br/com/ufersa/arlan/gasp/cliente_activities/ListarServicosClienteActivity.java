package br.com.ufersa.arlan.gasp.cliente_activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;
import br.com.ufersa.arlan.gasp.database.AppDatabase;
import br.com.ufersa.arlan.gasp.util.ServicoClienteAdapter;
import br.com.ufersa.arlan.gasp.util.ServicoPrestadorAdapter;

public class ListarServicosClienteActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_servicos_cliente);

        recyclerView = findViewById(R.id.SERVICO_CLIENTE_RECYCLER_VIEW);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListarServicosClienteActivity.this));

        //prepara o bd
        appDatabase = AppDatabase.getInstance(ListarServicosClienteActivity.this);


        // Popula a lista
        new AsyncRead().execute();
    }

    private class AsyncRead extends AsyncTask<Void, Void, List<Servico>> {
        // referência https://stackoverflow.com/questions/11833978/asynctask-pass-two-or-more-values-from-doinbackground-to-onpostexecute
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected List<Servico> doInBackground(Void... voids) {

            return appDatabase.servicoDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Servico> servicos) {
            RecyclerView.Adapter adapter = new ServicoClienteAdapter(ListarServicosClienteActivity.this, servicos);
            recyclerView.setAdapter(adapter);

        }
    }
}
