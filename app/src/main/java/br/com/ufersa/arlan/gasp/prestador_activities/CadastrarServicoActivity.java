package br.com.ufersa.arlan.gasp.prestador_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.com.ufersa.arlan.gasp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarServicoActivity extends AppCompatActivity {

    @BindView(R.id.CADASTRAR_SERVICO_CPF)
    EditText CADASTRARSERVICOCPF;
    @BindView(R.id.CADASTRAR_SERVICO_NOME_CLIENTE)
    TextView CADASTRARSERVICONOMECLIENTE;
    @BindView(R.id.CADASTRAR_SERVICO_TELEFONE_CLIENTE)
    TextView CADASTRARSERVICOTELEFONECLIENTE;
    @BindView(R.id.CADASTRAR_SERVICO_PRODUTO)
    EditText CADASTRARSERVICOPRODUTO;
    @BindView(R.id.CADASTRAR_SERVICO_DEFEITO)
    EditText CADASTRARSERVICODEFEITO;
    @BindView(R.id.CADASTRAR_SERVICO_DATA_ENTRADA)
    EditText CADASTRARSERVICODATAENTRADA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.CADASTRAR_SERVICO_BUSCAR, R.id.CADASTRAR_SERVICO_CONFIRMAR, R.id.CADASTRAR_SERVICO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CADASTRAR_SERVICO_BUSCAR:

                //todo buscar cliente por cpf

                break;
            case R.id.CADASTRAR_SERVICO_CONFIRMAR:

                //TODO inserir no banco

                break;
            case R.id.CADASTRAR_SERVICO_VOLTAR:

                finish();

                break;
        }
    }
}
