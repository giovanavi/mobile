package com.example.consultasqx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.consultasqx.Util.ConfiguraBD;
import com.example.consultasqx.model.Usuario;
import com.example.consultasqx.view.Home;
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

    DAOUsuario dao;
    SharedPreferences sp;

    String nome, email, phone, cpf, senha, confSenha;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dao = new DAOUsuario();
        sp = getSharedPreferences("Usuario", Context.MODE_PRIVATE);

        campoNome = findViewById(R.id.editTextNome);
        campoPhone = findViewById(R.id.editTextPhonePaciente);
        campoCpf = findViewById(R.id.editTextCpf);
        campoEmail = findViewById(R.id.editTextE_mail);
        campoSenha = findViewById(R.id.editTextSenha);
        campoConfSenha = findViewById(R.id.editTextConfirmarSenha);
    }

    public void validarCampos(View v){
        nome = campoNome.getText().toString();
        email = campoEmail.getText().toString();
        phone = campoPhone.getText().toString();
        cpf = campoCpf.getText().toString();
        senha = campoSenha.getText().toString();
        confSenha = campoConfSenha.getText().toString();

        if(verNome()){
            if(phone.length() == 11 && verTele(phone)){
                if(cpf.length() == 14 && verNums(cpf)){
                    if(!email.isEmpty()){
                        if(senha.length() >= 8){
                            if(!confSenha.equals(senha)){
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
                            Toast.makeText(this, "Preencha o campo Senha com no mínimo 8 dígitos e no máximo 16", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "Preencha o campo E-mail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Preencha o campo CPF corretamente no formato XXX.XXX.XXX-XX", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Preencha o campo Telefone segundo o modelo DDXXXXXXXXX", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Preencha o campo Nome", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean verNome() {
        for(int i = 0; i < nome.length(); ++i) {
            char ch = nome.charAt(i);

            if(i < nome.length()-1){
                char ch1 = nome.charAt(i + 1);

                if (ch == ' ' && ch1 == ' ') {
                    return false;
                }
            }
        }

        if (nome.length() == 0) {
            return false;
        } else {
            for (int i = 0; i < nome.length(); ++i) {
                char ch = nome.charAt(i);

                if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= 'á' && ch <= 'ú') || (ch >= 'Á' && ch <= 'Ú') || ch == 'ã' || ch == 'õ' || ch == ' ') || (i == 0 && ch == ' ')) {
                    Toast.makeText(this, "Preencha o campo Nome apropriadamente (Verifique espaços indesejados)", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    private boolean verTele(String phone){
        for (int i = 0; i < phone.length(); ++i) {
            char ch = phone.charAt (i);

            if(i == 0 && ch != '8'){
                return false;
            }else if (!((ch >= '0' && ch <= '9'))) {
                return false;
            }
        }

        return true;
    }

    private boolean verNums(String cpf){
        for (int i = 0; i < cpf.length(); ++i) {
            char ch = cpf.charAt (i);

            if (((i == 3 || i == 7) && ch != '.') || (i == 11 && ch != '-')) {
                return false;
            } else if (!((ch >= '0' && ch <= '9') || ch == '.' || ch == '-')) {
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

                    dao.add(usuario).addOnSuccessListener(suc -> {
                        Toast.makeText(CadastroActivity.this, "Inserindo dados...", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = sp.edit();

                        editor.putString("nome", nome);
                        editor.putString("cpf", cpf);
                        editor.putString("email", email);
                        editor.putString("telefone", phone);
                        editor.putString("senha", senha);
                        editor.commit();

                        abrirHome();

                    }).addOnFailureListener(er -> {
                        Toast.makeText(CadastroActivity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                    });


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

    private void abrirHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

        finish();
    }

    public void voltarCadastro(View v){
        finish();
    }

}