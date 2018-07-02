package br.com.ufersa.arlan.gasp.prestador_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.ufersa.arlan.gasp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarFuncionarioActivity extends AppCompatActivity {

    @BindView(R.id.CADASTRAR_FUNCIONARIO_NAME)
    EditText CADASTRARFUNCIONARIONAME;
    @BindView(R.id.CADASTRAR_FUNCIONARIO_ESPECIALIDADE)
    EditText CADASTRARFUNCIONARIOESPECIALIDADE;
    @BindView(R.id.CADASTAR_FUNCIONARIO_EMAIL)
    EditText CADASTARFUNCIONARIOEMAIL;
    @BindView(R.id.CADASTRO_FUNCIONARIO_CPF)
    EditText CADASTROFUNCIONARIOCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_funcionario);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.CADASTRAR_FUNCIONARIO_CONFIRMAR, R.id.CADASTRAR_FUNCIONARIO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CADASTRAR_FUNCIONARIO_CONFIRMAR:

                //TODO inserir no banco

                break;
            case R.id.CADASTRAR_FUNCIONARIO_VOLTAR:

                finish();

                break;
        }
    }
}
