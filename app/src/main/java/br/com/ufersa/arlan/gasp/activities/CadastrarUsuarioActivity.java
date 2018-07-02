package br.com.ufersa.arlan.gasp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.ufersa.arlan.gasp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.CADASTRAR_USUARIO_NAME)
    EditText CADASTRARUSUARIONAME;
    @BindView(R.id.CADASTRAR_USUARIO_ESPECIALIDADE)
    EditText CADASTRARUSUARIOESPECIALIDADE;
    @BindView(R.id.CADASTAR_USUARIO_EMAIL)
    EditText CADASTARUSUARIOEMAIL;
    @BindView(R.id.CADASTRO_USUARIO_SENHA)
    EditText CADASTROUSUARIOSENHA;
    @BindView(R.id.CADASTRO_USUARIO_CONFIRMAR_SENHA)
    EditText CADASTROUSUARIOCONFIRMARSENHA;
    @BindView(R.id.CADASTRO_USUARIO_CPF)
    EditText CADASTROUSUARIOCPF;
    @BindView(R.id.spinner)
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.CADASTRAR_USUARIO_CONFIRMAR, R.id.CADASTRAR_USUARIO_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CADASTRAR_USUARIO_CONFIRMAR:

                //TODO insere no banco

                break;
            case R.id.CADASTRAR_USUARIO_VOLTAR:

                finish();

                break;
        }
    }
}
