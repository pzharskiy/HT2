package steps;

import Driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import pages.*;

public class Steps {
    private WebDriver driver;


    public void openBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }

    public void loginJenkins(String name, String password)
    {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open();
        registrationPage.signIn(name, password);
    }

    public void clickManagePersonLink()
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.manageJenkins();
    }

    public boolean isPresentedManageUserText()
    {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        if (manageJenkinsPage.getManageUsersText()!=null)
        {
            return true;
        }
        else return false;
    }

    public boolean isPresentedManageUserLink()
    {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        if (manageJenkinsPage.getDtManageUsers()!=null)
        {
            return true;
        }
        else return false;
    }

    public void clickManageUsersLink()
    {
        ManageJenkinsPage manageJenkinsPage = new ManageJenkinsPage(driver);
        manageJenkinsPage.manageUsers();
    }

    public boolean isEnabledCreateUserLink()
    {
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.getLinkCreateUsers().isEnabled();
    }

    public void clickCreateUserLink()
    {
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickCreateUsers();
    }

    public boolean isFormPresentForReal()
    {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        return createUserPage.isFormPresentForReal();
    }

    public void createUser(String username, String password, String fullname, String email)
    {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.createUser(username, password, fullname, email);
    }

    public boolean isPresentedTextAboutUser(String username)
    {
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.findTextAboutUser(username);
    }

    public void clickDeleteSomeuserLink()
    {
        UsersPage usersPage = new UsersPage(driver);
        usersPage.clickLinkToDelete();
    }

    public boolean isPresentedQuestionAboutDeleting()
    {
        DeleteUserPage deleteUsersPage = new DeleteUserPage(driver);
        return deleteUsersPage.getQuestionAboutDeleting().contains("Вы уверены, что хотите удалить пользователя из Jenkins?");
    }

    public void confirmDeleting()
    {
        DeleteUserPage deleteUsersPage = new DeleteUserPage(driver);
        deleteUsersPage.deleteUser();
    }

    public boolean isPresentedLinkToDelete(String username)
    {
        UsersPage usersPage = new UsersPage(driver);
        return usersPage.findLinkToDelete(username);
    }

    public String getColorOfUsedButtons()
    {
        CreateUserPage createUserPage = new CreateUserPage(driver);
        createUserPage.open();
        return createUserPage.getButtonColor();
    }

    public boolean checkAutoRefresh()
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickAutoRefreshTrue();
            if (mainPage.getAutoRefreshTrue() && !mainPage.getAutoRefreshFalse())
            {
                return false;
            }
        mainPage.clickAutoRefreshFalse();
            if (!mainPage.getAutoRefreshTrue() && mainPage.getAutoRefreshFalse())
            {
                return false;
            }
        return true;
    }

}