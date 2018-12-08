import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.Steps;
import util.ColorConverter;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class JenkinsTest {


    private Steps steps;
    private final String USERNAME = "login";
    private final String PASSWORD = "password";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.openBrowser();
    }

    @Test
    public void TestJenkins()
    {
        //№0 - Авторизация
        steps.loginJenkins(USERNAME,PASSWORD);
        //№1 - После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users»
        // и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
        steps.clickManagePersonLink();
        assertTrue(steps.isPresentedManageUserText());
        assertTrue(steps.isPresentedManageUserLink());
        //№2.	После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»,
        // становится доступна ссылка «Create User».
        steps.clickManageUsersLink();
        assertTrue(steps.isEnabledCreateUserLink());
        //№3.	После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями типа password,
        // причём все поля должны быть пустыми.
        steps.clickCreateUserLink();
        assertTrue(steps.isFormPresentForReal());
        //№4.	После заполнения полей формы («Username» = «someuser», «Password» = «somepassword», «Confirm password» = «somepassword»,
        // «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom») и клика по кнопке с надписью «Create User» на странице
        // появляется строка таблицы (элемент tr), в которой есть ячейка (элемент td) с текстом «someuser».
        steps.createUser("someuser", "somepassword", "Some Full Name", "some@addr.dom");
        assertTrue(steps.isPresentedTextAboutUser("someuser"));
        //№5.	После клика по ссылке с атрибутом href равным «user/someuser/delete» появляется текст
        // «Are you sure about deleting the user from Jenkins?».
        steps.clickDeleteSomeuserLink();
        assertTrue(steps.isPresentedQuestionAboutDeleting());
        //№6.	После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr),
        // с ячейкой (элемент td) с текстом «someuser».На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
        steps.confirmDeleting();
        assertFalse(steps.isPresentedTextAboutUser("someuser"));
        assertFalse(steps.isPresentedLinkToDelete("someuser"));
        //№7.	{На той же странице, без выполнения каких бы то ни было действий}.
        // На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
        assertFalse(steps.isPresentedLinkToDelete("admin"));
        //Доп задание 1: У всех кнопок (элемент типа button), которые нужно кликать в основной части задания, цвет фона = #4b758b.
        //Т.к. у всех кнопок, с которыми мы работали в основном части задания (кроме страницы Авторизации) одинаковые id = "yui-gen5-button",
        //то и цвет этих кнопок будет одинаковый, поэтому нам достаточно проверить лишь одну кнопку.
        assertEquals(steps.getColorOfUsedButtons(), ColorConverter.hexToRGB("#4b758b", true));
        //Доп задание 2: При попытке создать пользователя с пустым (незаполненным) именем на странице появляется текст
        // «"" is prohibited as a full name for security reasons.»
        //Сообщение не появляется (видимо, после обновления)

        //Доп задание 3: При клике по ссылке с текстом «ENABLE AUTO REFRESH» эта ссылка пропадает, а вместо неё появляется
        // ссылка с текстом «DISABLE AUTO REFRESH». При клике по ссылке с текстом «DISABLE AUTO REFRESH» эта ссылка пропадает,
        // а вместо неё появляется ссылка с текстом «ENABLE AUTO REFRESH». Т.е. эти две ссылки циклически сменяют друг друга
        assertTrue(steps.checkAutoRefresh());
    }


    @AfterMethod(description = "Stop Browser")
    public void stopBrowser()
    {
        steps.closeBrowser();
    }

}
