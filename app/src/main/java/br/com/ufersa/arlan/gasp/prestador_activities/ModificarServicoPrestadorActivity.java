package br.com.ufersa.arlan.gasp.prestador_activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;
import br.com.ufersa.arlan.gasp.database.AppDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModificarServicoPrestadorActivity extends AppCompatActivity {

    @BindView(R.id.MODIFICAR_SERVICO_DESCRICAO)
    EditText MODIFICARSERVICODESCRICAO;
    @BindView(R.id.MODIFICAR_SERVICO_NOME_CLIENTE)
    TextView MODIFICARSERVICONOMECLIENTE;
    @BindView(R.id.MODIFICAR_SERVICO_PRODUTO)
    TextView MODIFICARSERVICOPRODUTO;
    @BindView(R.id.MODIFICAR_SERVICO_DEFEITO)
    TextView MODIFICARSERVICODEFEITO;
    @BindView(R.id.MODIFICAR_SERVICO_VALOR)
    EditText MODIFICARSERVICOVALOR;
    @BindView(R.id.MODIFICAR_SERVICO_STATUS)
    TextView MODIFICARSERVICOSTATUS;
    @BindView(R.id.MODIFICAR_SERVICO_BOTAO_ALTERAR)
    Button MODIFICARSERVICOBOTAOALTERAR;
    @BindView(R.id.MODIFICAR_SERVICO_VALOR_HINT)
    TextView MODIFICARSERVICOVALORHINT;
    @BindView(R.id.MODIFICAR_SERVICO_BOTAO_FINALIZAR)
    Button MODIFICARSERVICOBOTAOFINALIZAR;

    private AppDatabase appDatabase;
    Servico servico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_servico_prestador);
        ButterKnife.bind(this);

        initiate();
        getIncomingIntent();
    }

    private void initiate() {
        appDatabase = AppDatabase.getInstance(ModificarServicoPrestadorActivity.this);

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("servico")) {

            servico = getIntent().getParcelableExtra("servico");
            updateUI(servico);
        }
    }

    private void updateUI(Servico servico) {

        MODIFICARSERVICONOMECLIENTE.setText(servico.getClienteNome());
        MODIFICARSERVICOPRODUTO.setText(servico.getProduto());
        MODIFICARSERVICODEFEITO.setText(servico.getDefeito());
        MODIFICARSERVICOVALOR.setText(String.valueOf(servico.getValor()));
        MODIFICARSERVICOSTATUS.setText(servico.getStatus());
        MODIFICARSERVICODESCRICAO.setText(servico.getDescricao());


        if (servico.getStatus().equals(getString(R.string.aguardando)))
            MODIFICARSERVICOBOTAOALTERAR.setEnabled(false);

        if (servico.getStatus().equals(getString(R.string.finalizado))) {
            MODIFICARSERVICOBOTAOALTERAR.setEnabled(false);
            MODIFICARSERVICOBOTAOFINALIZAR.setEnabled(false);
            MODIFICARSERVICODESCRICAO.setEnabled(false);
            MODIFICARSERVICOVALOR.setEnabled(false);
            MODIFICARSERVICOVALORHINT.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.MODIFICAR_SERVICO_BOTAO_ALTERAR, R.id.MODIFICAR_SERVICO_BOTAO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MODIFICAR_SERVICO_BOTAO_ALTERAR:

                new AsyncAlterar().execute();

                break;

            case R.id.MODIFICAR_SERVICO_BOTAO_VOLTAR:
                finish();
                break;
        }
    }

    @OnClick(R.id.MODIFICAR_SERVICO_BOTAO_FINALIZAR)
    public void onViewClicked() {
        if (!servico.getStatus().equals(getString(R.string.confirmado)))
            Toast.makeText(this, "O cliente ainda não confirmou o serviço.", Toast.LENGTH_SHORT).show();
        else
            new AsyncFinalizar().execute();
    }


    private class AsyncAlterar extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                servico.setValor(Double.valueOf(MODIFICARSERVICOVALOR.getText().toString()));
                servico.setDescricao(MODIFICARSERVICODESCRICAO.getText().toString());
                servico.setStatus(getString(R.string.aguardando));

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ModificarServicoPrestadorActivity.this, "Erro ao modificar serviço.", Toast.LENGTH_SHORT).show();
                this.cancel(true);
                startActivity(new Intent(ModificarServicoPrestadorActivity.this, PrestadorMainActivity.class));

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
            Toast.makeText(ModificarServicoPrestadorActivity.this, "Serviço atualizado", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ModificarServicoPrestadorActivity.this, PrestadorMainActivity.class));
        }
    }

    private class AsyncFinalizar extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                servico.setDescricao(MODIFICARSERVICODESCRICAO.getText().toString());
                servico.setStatus(getString(R.string.finalizado));

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ModificarServicoPrestadorActivity.this, "Erro ao finalizar serviço.", Toast.LENGTH_SHORT).show();
                this.cancel(true);
                startActivity(new Intent(ModificarServicoPrestadorActivity.this, PrestadorMainActivity.class));

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
            Toast.makeText(ModificarServicoPrestadorActivity.this, "Serviço Finalizado.", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(ModificarServicoPrestadorActivity.this, PrestadorMainActivity.class));
        }
    }
}
