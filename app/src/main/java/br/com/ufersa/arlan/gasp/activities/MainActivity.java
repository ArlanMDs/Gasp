package br.com.ufersa.arlan.gasp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.ufersa.arlan.gasp.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.BUTTON_INICIAR, R.id.BUTTON_CADASTRAR})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.BUTTON_INICIAR:
                startActivity( new Intent(MainActivity.this, LoginActivity.class));

                break;
            case R.id.BUTTON_CADASTRAR:
                startActivity( new Intent(MainActivity.this, CadastrarUsuarioActivity.class));

                break;
        }
    }
}
