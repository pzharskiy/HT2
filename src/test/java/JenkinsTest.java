import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class JenkinsTest {

    private ChromeDriver driver;

    @BeforeClass
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver", "D://работа/EPAM/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test
    public void TestJenkins() {
        //№0 - Авторизация
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Page page = registrationPage.SignIn("your_login", "your_password");
        assertNotNull(page);

        //№1 - После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users»
        // и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
        page = ((MainPage) page).manageJenkins();
        assertNotNull(((ManageJenkinsPage) page).getManageUsersText());
        assertNotNull(((ManageJenkinsPage) page).getDtManageUsers());
        //assertTrue(((ManageJenkinsPage)page).getManageUsersText().getText().contains("Создание, удаление и модификция пользователей, имеющих право доступа к Jenkins" ));


        //№2.	После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users», становится доступна ссылка «Create User».
        page = ((ManageJenkinsPage) page).manageUsers();
        assertTrue(((UsersPage) page).getLinkCreateUsers().isEnabled()); //?????

        //№3.	После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями типа password, причём все поля должны быть пустыми.
        page = ((UsersPage) page).clickCreateUsers();
        assertTrue(((CreateUserPage) page).isFormPresentForReal());

        //№4.	После заполнения полей формы («Username» = «someuser», «Password» = «somepassword», «Confirm password» = «somepassword»,
        // «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom») и клика по кнопке с надписью «Create User» на странице
        // появляется строка таблицы (элемент tr), в которой есть ячейка (элемент td) с текстом «someuser».
        page = ((CreateUserPage) page).createUser("someuser", "somepassword", "Some Full Name", "some@addr.dom»");
        assertTrue(((UsersPage) page).findTextAboutUser("someuser"));

        //№5.	После клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст «Are you sure about deleting the user from Jenkins?».
        page = ((UsersPage) page).clickLinkToDelete();
        assertTrue(((DeleteUserPage) page).getQuestionAboutDeleting().contains("Вы уверены, что хотите удалить пользователя из Jenkins?"));

        //№6.	После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr), с ячейкой (элемент td) с текстом «someuser».
        // На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
        page = ((DeleteUserPage) page).deleteUser();
        assertFalse(((UsersPage) page).findTextAboutUser("someuser"));
        assertFalse(((UsersPage) page).findLinkToDelete("someuser"));

        //№7.	{На той же странице, без выполнения каких бы то ни было действий}. На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
        assertFalse(((UsersPage) page).findLinkToDelete("admin"));
    }
}
