import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;


public class Page {
    WebDriver driver;

    //1 Задание
    @FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
    private WebElement linkManageJenkins;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a/dl/dt")
    private WebElement manageUsers;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a/dl/dd[1]")
    private WebElement manageUsersText;

    //2 Задание
    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a")
    private WebElement linkManageUser;

    @FindBy(xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
    private WebElement createUsers;

    //3 Задание
    @FindBy(xpath = "//*[@id=\"main-panel\"]/form")
    private WebElement form;

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement username;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[2]/td[2]/input")
    private WebElement password1;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[3]/td[2]/input")
    private WebElement password2;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[4]/td[2]/input")
    private WebElement fullname;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[5]/td[2]/input")
    private WebElement email;

    @FindBy(xpath = "//*[@id=\"yui-gen3-button\"]")
    private WebElement createUserFromForm;

    @FindBy(xpath = "//*[@id=\"people\"]")
    private WebElement tableOfPeople;

    @FindBy(linkText = "someuser")
    private WebElement newTableElement;

    //@FindBy(linkText = "user/someuser/delete")
    //@FindBy(xpath = "//*[@id=\"people\"]/tbody/tr[3]/td[4]/a[2]") - работает
    @FindBy(xpath = "//a[starts-with(@href, \"user/someuser/delete\")]")
    private WebElement linkDeleteUser;

    @FindBy(xpath = "//a[starts-with(@href, \"user/admin/delete\")]")
    private WebElement linkDeleteAdmin;

    //*[@id="main-panel"]/form/text()
    @FindBy(xpath = "//*[@id=\"main-panel\"]/form")
    private WebElement QuestionAboutDeleting;

    @FindBy(xpath = "//*[@id=\"yui-gen3-button\"]")
    private WebElement deleteButton;

    private WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Page manageJenkins() {
        linkManageJenkins.click();
        return PageFactory.initElements(driver, Page.class);
    }

    public boolean containsManageUsers() //containsManageUsers(String type, String text)
    {
        if (manageUsers == null) {
            return false;
        }
        return manageUsers.getText().contains("Create/delete/modify users that can log in to this Jenkins"); //Как лучше сравнить 2 строки здесб?
    }

    public Page manageUsers() {
        linkManageUser.click();
        return PageFactory.initElements(driver, Page.class);
    }

    public boolean isFormPresentForReal() {

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        /*try { Thread.sleep(500); }
        catch (InterruptedException e)
        { e.printStackTrace(); }*/


        //ДУБЛИРОВАНИЕ ПОИСКА ЭЛЕМЕНТОВ - ПЛОХО!

        if (form == null) {
            return false;
        }

            /*if      ((form.findElement(By.id("username")).getAttribute("type").equalsIgnoreCase("text")) &&
                    (form.findElement(By.id("username")).getText().equals("")) &&
                    (form.findElement(By.name("password1")).getAttribute("type").equalsIgnoreCase("password")) &&
                    (form.findElement(By.name("password1")).getText().equals("")) &&
                    (form.findElement(By.name("password2")).getAttribute("type").equalsIgnoreCase("text")) &&
                    (form.findElement(By.name("password2")).getText().equals("")) &&
                    (form.findElement(By.name("fullname")).getAttribute("type").equalsIgnoreCase("text")) &&
                    (form.findElement(By.name("fullname")).getText().equals("")) &&
                    (form.findElement(By.name("email")).getAttribute("type").equalsIgnoreCase("text")) &&
                    (form.findElement(By.name("email")).getText().equals("")))

                     { return true;}  */

        if ((username.getAttribute("type").equalsIgnoreCase("text")) &&
                (username.getText().equals("")) &&
                (password1.getAttribute("type").equalsIgnoreCase("password")) &&
                (password1.getText().equals("")) &&
                (password2.getAttribute("type").equalsIgnoreCase("password")) &&
                (password2.getText().equals("")) &&
                (fullname.getAttribute("type").equalsIgnoreCase("text")) &&
                (fullname.getText().equals("")) &&
                (email.getAttribute("type").equalsIgnoreCase("text")) &&
                (email.getText().equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public Page createUser(String someuser, String somepassword, String some_full_name, String some_email) {
        username.sendKeys(someuser);
        password1.sendKeys(somepassword);
        password2.sendKeys(somepassword);
        fullname.sendKeys(some_full_name);
        email.sendKeys(some_email);
        createUserFromForm.click();
        return PageFactory.initElements(driver, Page.class);
    }

    public boolean findTextAboutUser() {
        if (driver.findElements(By.linkText("someuser")).isEmpty()) {
            return false;
        } else return true;
        /*System.out.println("findTextAboutUser работает неправильно, нужно искать через текст ссылки");
        if (newTableElement.getText().contains("someuser") && newTableElement!=null) {return true;}
        //driver.findElement(By.linkText(userLink.toString())).getText();
        return false;*/

    }

    public Page clickCreateUsers() {
        createUsers.click();
        return PageFactory.initElements(driver, Page.class);
    }

    public Page clickLinkToDelete() {

        System.out.println("ClickLinkToDelete нужно исправить! ищу топорно через хпас, а не через имя ссылки");
        //Дальше не работает: Пока ищу через хпас - работает, но это неправильно. По имени ссылки не находит
        // if (driver.findElements(By.tagName("href"))
        linkDeleteUser.click();
        return PageFactory.initElements(driver, Page.class);
    }

    public Page deleteUser() {

        deleteButton.click();
        return PageFactory.initElements(driver, Page.class);
    }

    public WebElement getLinkDeleteUser() {
        return linkDeleteUser;
    }

    public WebElement getManageUsersText() {
        return manageUsersText;
    }

    public WebElement getLinkManageJenkins() {
        return linkManageJenkins;
    }

    public WebElement getManageUsers() {
        return manageUsers;
    }

    public WebElement getCreateUsers() {
        return createUsers;
    }

    public String getQuestionAboutDeleting() {
        return QuestionAboutDeleting.getText();
    }

    public boolean findLinkToDeleteAdmin() {
       /* if (linkDeleteAdmin.isEnabled()) {return true;}
        else return false;*/

        try {
            if (linkDeleteAdmin.isEnabled()
            ) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}

