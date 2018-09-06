package br.com.ufersa.arlan.gasp.prestador_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.ufersa.arlan.gasp.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrestadorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestador_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.BUTTON_PRESTADOR_CADASTRAR_SERVICO, R.id.BUTTON_PRESTADOR_LISTAR_SERVICOS, R.id.BUTTON_PRESTADOR_VOLTAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BUTTON_PRESTADOR_CADASTRAR_SERVICO:

                startActivity(new Intent(PrestadorMainActivity.this, CadastrarServicoActivity.class));

                break;

            case R.id.BUTTON_PRESTADOR_LISTAR_SERVICOS:

                startActivity(new Intent(PrestadorMainActivity.this, ListarServicosPrestadorActivity.class));

                break;
            case R.id.BUTTON_PRESTADOR_VOLTAR:

                finish();
                //todo fez logout

                break;
        }
    }
}
