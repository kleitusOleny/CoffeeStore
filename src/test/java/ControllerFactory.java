import controller.LoginController;
import utils.LoginHandle;
import view.LoginPanel;

public class ControllerFactory {
    public static LoginController createLoginController(LoginHandle model, LoginPanel view) {
        return new LoginController(model, view);
    }
}
