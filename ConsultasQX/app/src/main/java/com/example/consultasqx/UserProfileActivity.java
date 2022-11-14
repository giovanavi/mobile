package com.example.consultasqx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultasqx.Util.ConfiguraBD;
import com.example.consultasqx.model.Usuario;
import com.example.consultasqx.view.Home;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    private static final String TAG = "ViewDatabase";

    private Usuario usuario;
    private DAOUsuario dao;
    private DatabaseReference dados;
    private FirebaseDatabase refUsuario;
    private static final String USUARIO = "Usuario";
    private String nome, cpf, email, telefone, senha;

    private FirebaseAuth autenticacao;

    SharedPreferences sp;
    TextView campoNomeProfile;
    TextInputEditText campoNome, campoCpf, campoEmail, campoTelefone, campoSenha;
    //TextInputLayout campoNome, campoCpf, campoEmail, campoTelefone, campoSenha;
    //Home home;

    static boolean valido;

    //private String email, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));

        //Intent intent = new Intent();
        //email = intent.getStringExtra("email");

        campoNomeProfile = findViewById(R.id.textView_NomeProfile);
        campoNome = findViewById(R.id.editTextNome);
        campoCpf = findViewById(R.id.editTextCpf);
        campoEmail = findViewById(R.id.editTextEmail);
        campoTelefone = findViewById(R.id.editTextTelefone);
        campoSenha = findViewById(R.id.editTextSenha);


        /*campoNomeProfile = findViewById(R.id.textView_NomeProfile);
        campoNome = findViewById(R.id.editText_nome);
        campoCpf = findViewById(R.id.editText_cpf);
        campoEmail = findViewById(R.id.editText_email);
        campoTelefone = findViewById(R.id.editText_phone);
        campoSenha = findViewById(R.id.editText_senha);*/

        dao = new DAOUsuario();

        sp = getApplicationContext().getSharedPreferences(USUARIO, Context.MODE_PRIVATE);

        autenticacao = ConfiguraBD.FirebaseAutenticacao();

        nome = sp.getString("nome", "");
        cpf = sp.getString("cpf", "");
        email = sp.getString("email", "");
        telefone = sp.getString("telefone", "");
        senha = sp.getString("senha", "");

        campoNomeProfile.setText(nome);
        campoNome.setText(nome);
        campoCpf.setText(cpf);
        campoEmail.setText(email);
        campoTelefone.setText(telefone);
        campoSenha.setText(senha);

        /*campoNomeProfile.setText(nome);
        campoNome.getEditText().setText(nome);
        campoCpf.getEditText().setText(cpf);
        campoEmail.getEditText().setText(email);
        campoTelefone.getEditText().setText(telefone);
        campoSenha.getEditText().setText(senha);*/

        //refUsuario = FirebaseDatabase.getInstance();
        //dados = refUsuario.getReference(USUARIO);

        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference userRef = rootRef.child(USUARIO);

        //ArrayList<String> list = new ArrayList<>();
        //dados = FirebaseDatabase.getInstance().getReference().child("Usuario");

        //showAllUserData();

        /*userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                String nomeProfile = null, nome = null, cpf = null, mail = null, telefone = null;
                //list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //usuario = snapshot.getValue(Usuario.class);
                    //list.add(snapshot.getValue().toString());
                    if (snapshot.child("email").getValue().equals(email)) {
                        nomeProfile = snapshot.child("nomeProfile").getValue().toString();
                        nome = snapshot.child("nome").getValue().toString();
                        cpf = snapshot.child("cpf").getValue().toString();
                        mail = snapshot.child("email").getValue().toString();
                        telefone = snapshot.child("telefone").getValue().toString();
                        senha = snapshot.child("senha").getValue().toString();
                    }
                }
                campoNomeProfile.setText(nomeProfile);
                campoNome.setText(nome);
                campoCpf.setText(cpf);
                campoEmail.setText(mail);
                campoTelefone.setText(telefone);
                campoSenha.setText(senha);
            }

            @Override
            public void onCancelled( DatabaseError error) {
                Log.d(TAG, "Falha em ler os valores", error.toException());
            }
        });*/
    }

    /*private void showAllUserData() {
        Intent intent = getIntent();

        String nome = intent.getStringExtra("nome");
        String cpf = intent.getStringExtra("cpf");
        String email = intent.getStringExtra("email");
        String telefone = intent.getStringExtra("telefone");
        String senha = intent.getStringExtra("senha");
        //String nomeProfile = intent.getStringExtra("nomeProfile");

        //campoNomeProfile.setText(nomeProfile);
        campoNome.getEditText().setText(nome);
        campoCpf.getEditText().setText(cpf);
        campoEmail.getEditText().setText(email);
        campoTelefone.getEditText().setText(telefone);
        campoSenha.getEditText().setText(senha);

    }*/

    /*public void persUsuario(Usuario usuario){
        this.usuario = usuario;
    }*/

    public void adicionar2() {
        /*campoNomeProfile.setText(usuario.getNome().toString());
        campoNome.setText(usuario.getNome().toString());
        campoCpf.setText(usuario.getCpf().toString());
        campoEmail.setText(usuario.getEmail().toString());
        campoTelefone.setText(usuario.getTelefone().toString());
        campoSenha.setText(usuario.getSenha().toString());*/

        /*for(DataSnapshot ds : dataSnapshot.getChildren()){
            usuario.setNome(ds.child(userID).getValue(Usuario.class).getName());
            usuario.setCpf(ds.child(userID).getValue(Usuario.class).getCpf());
            usuario.setEmail(ds.child(userID).getValue(Usuario.class).getEmail());
            usuario.setTelefone(ds.child(userID).getValue(Usuario.class).getPhone_num());
            usuario.setSenha(ds.child(userID).getValue(Usuario.class).getSenha());

            ArrayList<String> array  = new ArrayList<>();
            array.add(usuario.getNome());
            array.add(usuario.getEmail());
            array.add(usuario.getTelefone());
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            mListView.setAdapter(adapter);
        }*/
    }

    public void update(View v) {
        nome = campoNome.getText().toString();
        cpf = campoCpf.getText().toString();
        email = campoEmail.getText().toString();
        telefone = campoTelefone.getText().toString();
        senha = campoSenha.getText().toString();

        Log.d(TAG, "1- Nome: " + nome);
        Log.d(TAG, "1- Nome: " + nome);
        Log.d(TAG, "1- Nome: " + nome);
        Log.d(TAG, "1- Nome: " + nome);
        Log.d(TAG, "1- Nome: " + nome);

        Log.d(TAG, "Resultado 1: " + verNome());
        Log.d(TAG, "Resultado 2: " + verNums());
        Log.d(TAG, "Resultado 3: " + verTele());
        Log.d(TAG, "Resultado 4: " + senha.length());
        Log.d(TAG, "Resultado 5: " + verEmail());

        if (verNome() && verNums() && verTele() && (senha.length() >= 8) && /*!email.isEmpty()*/ verEmail()) {

            Log.d(TAG, "Entrou no if de update");

            HashMap<String, Object> hashMap = new HashMap<>();
            //hashMap.put("nomeProfile", campoNomeProfile.getText().toString());
            hashMap.put("nome", nome);
            hashMap.put("cpf", cpf);
            hashMap.put("email", email);
            hashMap.put("telefone", telefone);
            hashMap.put("senha", senha);

            Log.d(TAG, "2- Nome: " + nome);
            Log.d(TAG, "2- Nome: " + nome);
            Log.d(TAG, "2- Nome: " + nome);
            Log.d(TAG, "2- Nome: " + nome);

            /*hashMap.put("nome", campoNome.getEditText().toString());
            hashMap.put("cpf", campoCpf.getEditText().toString());
            hashMap.put("email", campoEmail.getEditText().toString());
            hashMap.put("telefone", campoTelefone.getEditText().toString());
            hashMap.put("senha", campoSenha.getEditText().toString());*/

            Log.d(TAG, "3- UID do usuário: " + autenticacao.getUid());

            dao.update(autenticacao.getUid(), hashMap).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Atualizado", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();

                editor.putString("nome", nome);
                editor.putString("cpf", cpf);
                editor.putString("email", email);
                editor.putString("telefone", telefone);
                editor.putString("senha", senha);
                editor.commit();

                campoNomeProfile.setText(nome);

            }).addOnFailureListener(er -> {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });

        }
    }

    public void delete(View v) {
        //home = new Home();

        Log.d(TAG, "Dentro do delete");

        dao.remove(autenticacao.getUid()).addOnSuccessListener(suc -> {
            Toast.makeText(this, "Conta removida", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "Dentro do dao.remove(autenticacao.getUid()).addOnSuccessListener(suc -> ");

            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();

            campoNomeProfile.setText("");
            campoNome.setText("");
            campoCpf.setText("");
            campoEmail.setText("");
            campoTelefone.setText("");
            campoSenha.setText("");

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

            finish();

        }).addOnFailureListener(er -> {
            Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "" + er.getMessage());
        });

        //home.verApg();
    }

    private boolean verEmail() {
        //int valido;

        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();

        if (autenticacao.getCurrentUser().getEmail().equals(email)) {
            Log.d(TAG, "Entrou em getCurrentUser().getEmail().equals(email)");
            return true;
        } else {
            Log.d(TAG, "Vai criar outro usuário");
            autenticacao.createUserWithEmailAndPassword(
                    email, senha
            ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(UserProfileActivity.this, "Sucesso em atualizar o usuário", Toast.LENGTH_SHORT).show();

                        Log.d(TAG, "Entrou no task.isSuccessful()");
                        valido = true;
                    } else {
                        Log.d(TAG, "Entrou no else de onComplete");

                        String excecao;

                        try {
                            throw Objects.requireNonNull(task.getException());
                        } catch (FirebaseAuthWeakPasswordException e) {
                            excecao = "Digite uma senha mais forte";
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            excecao = "Digite um email válido";
                        } catch (FirebaseAuthUserCollisionException e) {
                            excecao = "Esta conta já existe";
                        } catch (Exception e) {
                            excecao = "Erro ao cadastrar usuário " + e.getMessage();
                            e.printStackTrace();
                        }
                        Toast.makeText(UserProfileActivity.this, excecao, Toast.LENGTH_SHORT).show();
                        valido = false;
                    }
                }
            });
        }

        Log.d(TAG, "Antes de retornar valido[0], valor de valido[0] = " + valido);
        return valido;
    }

    private boolean verTele() {
        if (telefone.length() != 11) {
            return false;
        } else {
            for (int i = 0; i < telefone.length(); ++i) {
                char ch = telefone.charAt(i);

                if ((i == 0 && ch != '8') || !(ch >= '0' && ch <= '9')) {
                    Toast.makeText(this, "O campo Telefone deve estar de acordo com o modelo DDXXXXXXXXX", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    private boolean verNums() {
        if (cpf.length() != 14) {
            return false;
        } else {
            for (int i = 0; i < cpf.length(); ++i) {
                char ch = cpf.charAt(i);

                if (((i == 3 || i == 7) && ch != '.') || (i == 11 && ch != '-')) {
                    Toast.makeText(this, "Preencha o campo CPF corretamente no formato XXX.XXX.XXX-XX", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (!((ch >= '0' && ch <= '9') || ch == '.' || ch == '-')) {
                    Toast.makeText(this, "Preencha o campo CPF corretamente com números, \".\" e \"-\"", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    private boolean verNome() {
        if (nome.length() == 0) {
            return false;
        } else {
            for (int i = 0; i < nome.length(); ++i) {
                char ch = nome.charAt(i);

                if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == ' ') || ((i == 0 || i == nome.length()-1) && ch == ' ')) {
                    Toast.makeText(this, "Preencha o campo Nome apropriadamente (Verifique espaços indesejados)", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }

        return true;
    }

    public void voltarUserProfile(View v) {
        finish();
    }
}