package br.com.ufersa.arlan.gasp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.ufersa.arlan.gasp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.LOGIN_EMAIL)
    EditText LOGINEMAIL;
    @BindView(R.id.LOGIN_SENHA)
    EditText LOGINSENHA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.LOGIN_ENTRAR, R.id.LOGIN_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.LOGIN_ENTRAR:

                //TODO validar login

                break;
            case R.id.LOGIN_VOLTAR:
                finish();

                break;
        }
    }
}
