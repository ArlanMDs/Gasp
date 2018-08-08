package br.com.ufersa.arlan.gasp.prestador_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModificarServicoActivity extends AppCompatActivity {

    @BindView(R.id.MODIFICAR_SERVICO_DESCRICAO)
    TextView MODIFICARSERVICODESCRICAO;
    @BindView(R.id.MODIFICAR_SERVICO_NOME_CLIENTE)
    TextView MODIFICARSERVICONOMECLIENTE;
    @BindView(R.id.MODIFICAR_SERVICO_PRODUTO)
    TextView MODIFICARSERVICOPRODUTO;
    @BindView(R.id.MODIFICAR_SERVICO_DEFEITO)
    TextView MODIFICARSERVICODEFEITO;
    @BindView(R.id.MODIFICAR_SERVICO_VALOR)
    TextView MODIFICARSERVICOVALOR;
    @BindView(R.id.MODIFICAR_SERVICO_STATUS)
    TextView MODIFICARSERVICOSTATUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_servico);
        ButterKnife.bind(this);

        initiate();
        getIncomingIntent();
    }

    private void initiate() {
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("servico")) {

            Servico servico = getIntent().getParcelableExtra("servico");
            updateUI(servico);
        }
    }

    private void updateUI(Servico servico) {

        MODIFICARSERVICONOMECLIENTE.setText(servico.getClienteNome());
        MODIFICARSERVICOPRODUTO.setText(servico.getProduto());
        //MODIFICARSERVICOSTATUS
    }

    @OnClick({R.id.MODIFICAR_SERVICO_BOTAO_ALTERAR, R.id.MODIFICAR_SERVICO_BOTAO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.MODIFICAR_SERVICO_BOTAO_ALTERAR:
                break;
            case R.id.MODIFICAR_SERVICO_BOTAO_VOLTAR:
                break;
        }
    }
}
