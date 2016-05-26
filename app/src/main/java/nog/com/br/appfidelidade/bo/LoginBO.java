package nog.com.br.appfidelidade.bo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import nog.com.br.appfidelidade.repository.LoginRepository;
import nog.com.br.appfidelidade.util.Util;
import nog.com.br.appfidelidade.validation.LoginValidation;

/**
 * Created by andersonnogueira on 03/05/16.
 */
public class LoginBO {
    private LoginRepository loginRepository;

    public LoginBO(Activity activity){
        loginRepository = new LoginRepository(activity);
        loginRepository.listarLogins(activity);
    }

    public boolean validarCamposLogin(LoginValidation validation){

        boolean resultado = true;
        if (validation.getLogin() == null || "".equals(validation.getLogin())){
            validation.getEdtLogin().setError("Campo Obrigatório!");
            //exemplo para exibir os toasts
            //Util.showMsgToast(LoginActivity.this, "Por favor preencher todos os campos!");
            resultado = false;
        }else if (validation.getLogin().length() < 2){
            validation.getEdtLogin().setError("O campo deve possuir mais de 2 caracteres!");
        }

        if (validation.getSenha() == null || "".equals(validation.getSenha())){
            validation.getEdtSenha().setError("Campo Obrigatório!");
            resultado = false;
        }
        if (resultado){

            loginRepository.deleteLogin(validation.getLogin(), validation.getSenha());

            if ((validation.getLogin().equals("111.111.111-11") && validation.getLogin().equals("11.111.111/1111-11")) ||!validation.getSenha().equals("admin")){
                Util.showMsgToast(validation.getActivity(), "Login/Senha inválidos!");
                resultado = false;
            }else {
                SharedPreferences.Editor editor = validation.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.putString("login", validation.getLogin());
                editor.putString("senha", validation.getSenha());
                editor.putInt("modo",validation.getModo());



                editor.commit();
            }
        }

        return resultado;
    }

}
