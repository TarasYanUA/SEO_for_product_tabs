import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.SeoTabsSettings;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.$;

public class TestCase3_Responsive extends TestRunner{
    @Test
    public void checkProductTabsOnResponsive_TestCaseThree() {
        //Активируем тему "Responsive"
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToDesignThemes();
        $("#image_img_bright_theme_Bright_theme").hover();
        csCartSettings.button_ActivateTheme.click();

        //Включаем настройку модуля - Перед вкладками товара
        csCartSettings.navigateToAddonsPage();
        SeoTabsSettings seoTabsSettings = csCartSettings.navigateToSeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("before_tabs");
        seoTabsSettings.button_SaveSettings.click();

        //Переходим на витрину
        ProductSettings productSettings = csCartSettings.navigateToProductListPage();
        productSettings.goToEditingProductPage("Wii U DELUXE");
        ProductPage productPage = productSettings.navigateToProductPage(1);
        selectLanguage_RU();
        productPage.tabPanel.scrollTo();
        Selenide.sleep(1000);
        //Проверка, что панель товарных вкладок от модуля присутствуют
        Assert.assertTrue($(".ab-spt-floating-panel").exists(), "There is no product tabs panel!");
        //Проверка, что панель товарных вкладок расположена перед вкладками товара
        Assert.assertTrue($(".ab-spt-floating-position-before_tabs").exists(),
                "Position of the product tabs panel is not before tabs!");
        Selenide.screenshot("300 Product tabs panel - Panel before product tabs, Responsive");
        productPage.tab_Tags.scrollIntoView(true);
        Selenide.sleep(1000);
        //Проверяем, что краткое название товара присутствует
        String result = null;
        String expectedWord = "ShortName";
        String myString = $(".tab-list-title").getText();
        String[] couple = myString.split(" ");
        for(int i=0; i < couple.length ; i++) {
            if(couple[i].equals(expectedWord)){
                result = couple[i];
            }
        }
        Assert.assertEquals(result, expectedWord, "There is no product short name!");
        Selenide.screenshot("310 Floating panel - Panel before product tabs, Responsive");

        //Включаем настройку модуля - После заголовка Н1
        csCartSettings.shiftBrowserTab(0);
        csCartSettings.navigateToAddonsPage();
        csCartSettings.navigateToSeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("after_h1");
        seoTabsSettings.button_SaveSettings.click();

        csCartSettings.shiftBrowserTab(1);
        Selenide.refresh();
        Selenide.sleep(1000);
        //Проверка, что панель товарных вкладок от модуля присутствуют
        Assert.assertTrue($(".ab-spt-floating-panel").exists(), "There is no product tabs panel!");
        //Проверка, что панель товарных вкладок расположена после заголовка Н1
        Assert.assertTrue($(".ab-spt-floating-position-after_h1").exists(),
                "Position of the product tabs panel is not after H1 header!");
        Selenide.screenshot("320 Product tabs panel - Panel before product tabs, Responsive");
        productPage.tab_Tags.scrollIntoView(true);
        Selenide.sleep(1000);
        //Проверяем, что краткое название товара присутствует
        String resultTwo = null;
        String expectedWordTwo = "ShortName";
        String myStringTwo = $(".tab-list-title").getText();
        String[] coupleTwo = myStringTwo.split(" ");
        for(int i=0; i < coupleTwo.length ; i++) {
            if(coupleTwo[i].equals(expectedWordTwo)){
                resultTwo = coupleTwo[i];
            }
        }
        Assert.assertEquals(resultTwo, expectedWordTwo, "There is no product short name!");
        Selenide.screenshot("330 Floating panel - Panel before product tabs, Responsive");




    }
}