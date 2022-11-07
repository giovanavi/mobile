package com.example.consultasqx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.consultasqx.Util.ConfiguraBD;
import com.example.consultasqx.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Objects;

public class CadastroActivity extends AppCompatActivity {

    Usuario usuario;
    FirebaseAuth autenticacao;
    EditText campoNome, campoPhone, campoCpf, campoEmail, campoSenha, campoConfSenha;
    Button botaoCadastrar, botaoVoltarCadastro;

    String[] items = {"Paciente", "Médico"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    String item;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome =(EditText) findViewById(R.id.editTextNome);
        campoPhone =(EditText) findViewById(R.id.editTextPhone);
        campoCpf =(EditText) findViewById(R.id.editTextCpf);
        campoEmail =(EditText) findViewById(R.id.editTextE_mail);
        campoSenha =(EditText) findViewById(R.id.editTextSenha);
        campoConfSenha =(EditText) findViewById(R.id.editTextConfirmarSenha);

        autoCompleteTxt = (AutoCompleteTextView) findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);
        autoCompleteTxt.setAdapter(adapterItems);

        botaoCadastrar =(Button) findViewById(R.id.buttonCadastrar);
        botaoVoltarCadastro =(Button) findViewById(R.id.buttonVoltarCadastro);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Escolha: "+item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void validarCampos(View v){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String phone = campoPhone.getText().toString();
        String cpf = campoCpf.getText().toString();
        String senha = campoSenha.getText().toString();
        String confSenha = campoConfSenha.getText().toString();

        if(!nome.isEmpty()){
            if(!item.isEmpty()){
                if(!phone.isEmpty()){
                    if(!cpf.isEmpty() && cpf.length() == 14 && verNums(cpf)){
                        if(!email.isEmpty()){
                            if(!senha.isEmpty()){
                                if(!confSenha.equals(senha) || confSenha.isEmpty()){
                                    Toast.makeText(this, "Preencha o campo Confirme sua senha corretamente", Toast.LENGTH_SHORT).show();
                                }else{
                                    usuario = new Usuario();
                                    usuario.setNome(nome);
                                    usuario.setTelefone(phone);
                                    usuario.setCpf(cpf);
                                    usuario.setEmail(email);
                                    usuario.setSenha(senha);

                                    cadastrarUsuario();
                                }
                            }else{
                                Toast.makeText(this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "Preencha o campo E-mail", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "Preencha o campo CPF corretamente", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Preencha o campo Telefone", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Selecione um tipo de usuário", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o campo Nome", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verNums(String cpf){
        for (int i = 0; i < cpf.length(); ++i) {
            char ch = cpf.charAt (i);

            if (!((ch >= '0' && ch <= '9') || ch == '.' || ch == '/')) {
                return false;
            }
        }

        return true;
    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguraBD.FirebaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Sucesso ao cadastrar o usuário", Toast.LENGTH_SHORT).show();
                }else{
                    String excecao;

                    try{
                        throw Objects.requireNonNull(task.getException());
                    }catch(FirebaseAuthWeakPasswordException e){
                        excecao = "Digite uma senha mais forte";
                    }catch(FirebaseAuthInvalidCredentialsException e){
                        excecao = "Digite um email válido";
                    }catch(FirebaseAuthUserCollisionException e){
                        excecao = "Esta conta já existe";
                    }catch(Exception e){
                        excecao = "Erro ao cadastrar usuário " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*public void Sign_in(View view){
        Intent intent = new Intent(this, FormSignin.class);
        startActivity(intent);
    }

    public void Sign_up(View view){
        Intent intent = new Intent(this, FormSignupPaciente.class);
        startActivity(intent);
    }*/

}