package br.com.ufersa.arlan.gasp.prestador_activities;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.ufersa.arlan.gasp.R;
import br.com.ufersa.arlan.gasp.beans.Servico;
import br.com.ufersa.arlan.gasp.beans.Usuario;
import br.com.ufersa.arlan.gasp.database.AppDatabase;
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
    @BindView(R.id.CADASTRAR_SERVICO_EMAIL_CLIENTE)
    TextView CADASTRARSERVICOEMAILCLIENTE;
    @BindView(R.id.DADOSCLIENTELAYOUT)
    ConstraintLayout DADOSCLIENTELAYOUT;

    private AppDatabase appDatabase;
    private Usuario usuario;
    private DatePickerDialog datePickerDialog;
    private long dataEntrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_servico);
        ButterKnife.bind(this);

        //prepara o bd
        appDatabase = AppDatabase.getInstance(CadastrarServicoActivity.this);
    }

    @OnClick({R.id.CADASTRAR_SERVICO_BUSCAR, R.id.CADASTRAR_SERVICO_CONFIRMAR, R.id.CADASTRAR_SERVICO_VOLTAR, R.id.CADASTRAR_SERVICO_DATA_ENTRADA})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.CADASTRAR_SERVICO_BUSCAR:

                new AsyncRead().execute();

                break;
            case R.id.CADASTRAR_SERVICO_CONFIRMAR:

                new AsyncInsert().execute();

                break;
            case R.id.CADASTRAR_SERVICO_VOLTAR:

                finish();

                break;
            case R.id.CADASTRAR_SERVICO_DATA_ENTRADA:

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(CadastrarServicoActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        CADASTRARSERVICODATAENTRADA.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //
                        Calendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                        //passData(calendar.getTimeInMillis());
                        //getActivity().getSupportFragmentManager().beginTransaction().remove(DateFragment.this).commit();

                        try {
                            dataEntrada = calendar.getTimeInMillis();
                        }catch (Exception e){
                            e.printStackTrace();
                            CADASTRARSERVICODATAENTRADA.setText(null);
                        }
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();
                // Tentei usar o método setButton no dialog, mas deu algum conflito com o onDateSet

                break;
        }
    }

    // atualiza a interface quando é obtido um usuário válido na busca por cpf
    private void updateUI(Usuario user) {
        CADASTRARSERVICONOMECLIENTE.setText(user.getNome());
        CADASTRARSERVICOTELEFONECLIENTE.setText(user.getTelefone());
        CADASTRARSERVICOEMAILCLIENTE.setText(user.getEmail());

        DADOSCLIENTELAYOUT.setVisibility(View.VISIBLE);

    }

    private class AsyncRead extends AsyncTask<Void, Void, Usuario> {
        // referência https://stackoverflow.com/questions/11833978/asynctask-pass-two-or-more-values-from-doinbackground-to-onpostexecute
        @Override
        protected void onPreExecute() {

            // Antes de executar, verifica se o textview do cpf contém uma string de número válido
            if (!cpfIsValid(CADASTRARSERVICOCPF.getText().toString())) {
                this.cancel(true);
                Toast.makeText(CadastrarServicoActivity.this, "CPF inválido.", Toast.LENGTH_SHORT).show();
            }
            super.onPreExecute();

        }

        private boolean cpfIsValid(String s) {
            return s.length() == 11; // 11 é o tamanho de um cpf válido
        }

        @Override
        protected Usuario doInBackground(Void... voids) {

            return appDatabase.usuarioDao().getUserByCpf(CADASTRARSERVICOCPF.getText().toString());
        }

        @Override
        protected void onPostExecute(Usuario user) {
            if (user != null)
                updateUI(user);
            else
                Toast.makeText(CadastrarServicoActivity.this, "Nenhum cliente cadastrado com o CPF informado.", Toast.LENGTH_SHORT).show();

        }
    }


    private class AsyncInsert extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //TODO pedir número mínimo de caracteres para o nome?
            // se não houver campo nulo, cria o objeto e adiciona-o no bd
            if (!dataIsValid()) {
                this.cancel(true);
                Toast.makeText(CadastrarServicoActivity.this, "Algum campo está vazio.", Toast.LENGTH_SHORT).show();
            }
        }

        private boolean dataIsValid() {
            return CADASTRARSERVICOCPF.length() == 11 && CADASTRARSERVICOPRODUTO.length() > 0 && CADASTRARSERVICODEFEITO.length() > 0 && CADASTRARSERVICODATAENTRADA.length() > 0;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            // busca as credenciais do prestador logado
            SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String prestadorCpf = (mSharedPreference.getString("usuarioAtivoID", "error"));
            String prestadorNome = (mSharedPreference.getString("usuarioAtivoNome", "error"));

            Servico servico = new Servico.ServicoBuilder()
                    .setDataEntrada(dataEntrada)
                    .setDefeito(CADASTRARSERVICODEFEITO.getText().toString())
                    .setProduto(CADASTRARSERVICOPRODUTO.getText().toString())
                    .setPrestadorCNPJ(prestadorCpf)
                    .setPrestadorNome(prestadorNome)
                    .setClienteCPF(CADASTRARSERVICOCPF.getText().toString()) // TODO fonte de bugs... se o usuário alterar depois da busca, dá ruim...
                    .setClienteNome(CADASTRARSERVICONOMECLIENTE.getText().toString())
                    .setStatus(getString(R.string.avaliacao))
                    .createServico();

            appDatabase.servicoDao()
                    .insert(servico);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(CadastrarServicoActivity.this, "Serviço cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
            super.onPostExecute(aVoid);

        }
    }
}
