package br.com.ufersa.arlan.gasp.cliente_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import br.com.ufersa.arlan.gasp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicoStatusActivity extends AppCompatActivity {

    @BindView(R.id.STATUS_SERVICO)
    TextView STATUSSERVICO;
    @BindView(R.id.STATUS_NOME_FUNCIONARIO)
    TextView STATUSNOMEFUNCIONARIO;
    @BindView(R.id.STATUS_PRODUTO)
    TextView STATUSPRODUTO;
    @BindView(R.id.STATUS_DEFEITO)
    TextView STATUSDEFEITO;
    @BindView(R.id.STATUS_VALOR)
    TextView STATUSVALOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_status);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.CONFIRMA_STATUS_SERVICO, R.id.CANCELAR_STATUS_SERVICO, R.id.STATUS_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CONFIRMA_STATUS_SERVICO:

                //TODO aprova o serviço

                break;
            case R.id.CANCELAR_STATUS_SERVICO:

                //TODO nega o serviço

                break;
            case R.id.STATUS_VOLTAR:

                finish();

                break;
        }
    }
}
