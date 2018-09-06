package br.com.ufersa.arlan.gasp.cliente_activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;
import br.com.ufersa.arlan.gasp.database.AppDatabase;
import br.com.ufersa.arlan.gasp.prestador_activities.PrestadorMainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModificarServicoClienteActivity extends AppCompatActivity {

    @BindView(R.id.MODIFICAR_SERVICO_DESCRICAO)
    TextView MODIFICARSERVICODESCRICAO;
    @BindView(R.id.MODIFICAR_SERVICO_NOME_PRESTADOR)
    TextView MODIFICARSERVICONOMEPRESTADOR;
    @BindView(R.id.MODIFICAR_SERVICO_PRODUTO)
    TextView MODIFICARSERVICOPRODUTO;
    @BindView(R.id.MODIFICAR_SERVICO_DEFEITO)
    TextView MODIFICARSERVICODEFEITO;
    @BindView(R.id.MODIFICAR_SERVICO_VALOR)
    TextView MODIFICARSERVICOVALOR;
    @BindView(R.id.MODIFICAR_SERVICO_STATUS)
    TextView MODIFICARSERVICOSTATUS;
    @BindView(R.id.MODIFICAR_SERVICO_BOTAO_CONFIRMAR)
    Button MODIFICARSERVICOBOTAOCONFIRMAR;

    private AppDatabase appDatabase;
    Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_servico_cliente);
        ButterKnife.bind(this);

        initiate();
        getIncomingIntent();
    }

    private void initiate() {
        appDatabase = AppDatabase.getInstance(ModificarServicoClienteActivity.this);

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("servico")) {

            servico = getIntent().getParcelableExtra("servico");
            updateUI(servico);
        }
    }

    private void updateUI(Servico servico) {

        MODIFICARSERVICONOMEPRESTADOR.setText(servico.getPrestadorNome());
        MODIFICARSERVICOPRODUTO.setText(servico.getProduto());
        MODIFICARSERVICODEFEITO.setText(servico.getDefeito());
        MODIFICARSERVICOVALOR.setText(String.valueOf(servico.getValor()));
        MODIFICARSERVICOSTATUS.setText(servico.getStatus());
        MODIFICARSERVICODESCRICAO.setText(servico.getDescricao());


        //        <item>Em avaliação</item>
        //        <item>Em conserto</item>
        //        <item>Aguardando confirmação do cliente</item>
        //        <item>Confirmado pelo cliente</item>
        //        <item>Finalizado</item>

        //para confirmar somente quando tiver em aguardando confirmação

        if (!servico.getStatus().equals(getString(R.string.aguardando)))
            MODIFICARSERVICOBOTAOCONFIRMAR.setEnabled(false);


    }

    @OnClick({R.id.MODIFICAR_SERVICO_BOTAO_CONFIRMAR, R.id.MODIFICAR_SERVICO_BOTAO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MODIFICAR_SERVICO_BOTAO_CONFIRMAR:

                new AsyncUpdate().execute();

                break;
            case R.id.MODIFICAR_SERVICO_BOTAO_VOLTAR:
                finish();
                break;
        }
    }

    private class AsyncUpdate extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                servico.setStatus(getString(R.string.confirmado));

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ModificarServicoClienteActivity.this, "Erro ao modificar serviço.", Toast.LENGTH_SHORT).show();
                this.cancel(true);
                startActivity(new Intent(ModificarServicoClienteActivity.this, ClienteMainActivity.class));

            }
        }

        @Override
        protected Void doInBackground(Void... voids) {

            appDatabase.servicoDao()
                    .update(servico);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // passar o relatorio para a activity de detalhes junto com uma flag apontando que vem da atualização
            // preciso limpar o histórico para o usuário não apertar back e ver os dados desatualizados
            Toast.makeText(ModificarServicoClienteActivity.this, "Serviço atualizado", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ModificarServicoClienteActivity.this, ClienteMainActivity.class));
        }
    }
}
