import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

public class JenkinsTest {

    private ChromeDriver driver;
    @BeforeMethod
    public void SetUp()
    {
        System.setProperty("webdriver.chrome.driver", "D://работа/EPAM/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
    }
    @AfterTest
    public void tearDown()
    {
        driver.close();
    }

    @Test
    public void TestJenkins()
    {
        //№0 - Авторизация
        RegistrationPage registrationPage = PageFactory.initElements(driver,RegistrationPage.class);
        Page page=registrationPage.logIn("pzharskiy", "Riseagainst");
        assertNotNull(page);

        //№1 - После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users»
        // и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
        page = page.manageJenkins();
        assertNotNull(page.getManageUsersText());
        assertTrue(page.getManageUsersText().getText().contains("Создание, удаление и модификция пользователей, имеющих право доступа к Jenkins" ));
        //assertTrue(page.containsManageUsers());

        //№2.	После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users», становится доступна ссылка «Create User».
        page=page.manageUsers();
        assertTrue(page.getCreateUsers().isEnabled()); //?????

        //№3.	После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями типа password, причём все поля должны быть пустыми.
        page=page.clickCreateUsers();
        assertTrue(page.isFormPresentForReal());

        //№4.	После заполнения полей формы («Username» = «someuser», «Password» = «somepassword», «Confirm password» = «somepassword»,
        // «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom») и клика по кнопке с надписью «Create User» на странице
        // появляется строка таблицы (элемент tr), в которой есть ячейка (элемент td) с текстом «someuser».
        page=page.createUser("someuser", "somepassword", "Some Full Name", "some@addr.dom»");
        assertTrue(page.findTextAboutUser());

        //№5.	После клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст «Are you sure about deleting the user from Jenkins?».
        page=page.clickLinkToDelete();
        assertTrue(page.getQuestionAboutDeleting().contains("Вы уверены, что хотите удалить пользователя из Jenkins?"));

        //№6.	После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr), с ячейкой (элемент td) с текстом «someuser».
        // На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
        page=page.deleteUser();
        assertFalse(page.findTextAboutUser());

        //№7.	{На той же странице, без выполнения каких бы то ни было действий}. На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
        assertFalse(page.findLinkToDeleteAdmin());
    }
}
