package nog.com.br.appfidelidade.service;

import nog.com.br.appfidelidade.async.AsyncUsuario;
import nog.com.br.appfidelidade.util.Constantes;
import nog.com.br.appfidelidade.validation.LoginValidation;

/**
 * Created by andersonnogueira on 18/05/16.
 */
public class LoginService {

    public void validarCamposLogin(LoginValidation validation) {
        boolean resultado = true;
        if (validation.getLogin() == null || "".equals(validation.getLogin())) {
            validation.getEdtLogin().setError("Campo obrigatório!");
            resultado = false;
        } else if (validation.getLogin().length() < 2) {
            validation.getEdtLogin().setError("Campo deve ter no mínimo 3 caracteres!");
        }

        if (validation.getSenha() == null || "".equals(validation.getSenha())) {
            validation.getEdtSenha().setError("Campo obrigatório!");
            resultado = false;
        }

        if (resultado) {
            new AsyncUsuario(validation).execute(Constantes.URL_WS_LOGIN);
        }
    }

    public void deslogar() {

    }

}
