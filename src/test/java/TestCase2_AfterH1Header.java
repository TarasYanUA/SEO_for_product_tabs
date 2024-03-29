import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.SeoTabsSettings;
import adminPanel.UniThemeSettings;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.$;

/*
- Настройки темы Юни2:
    * Верхняя липкая панель -- Включаем и Отключаем
- Позиция навигационной панели вкладок -- После заголовка Н1
*/

public class TestCase2_AfterH1Header extends TestRunner{
    @Test
    public void checkProductTabs_TestCaseTwo(){
        //Включаем верхнюю липкую панель темы
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAddonsPage();
        UniThemeSettings uniThemeSettings = csCartSettings.navigateToThemeSettings();
        if(!uniThemeSettings.setting_TopStickyPanel.isSelected()){
            uniThemeSettings.setting_TopStickyPanel.click();
            csCartSettings.button_Save.click(); }

        //Настраиваем настройки модуля
        csCartSettings.navigateToAddonsPage();
        SeoTabsSettings seoTabsSettings = csCartSettings.navigateToSeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("after_h1");
        seoTabsSettings.button_SaveSettings.click();

        //Переходим на витрину
        ProductSettings productSettings = csCartSettings.navigateToProductListPage();
        productSettings.goToEditingProductPage("Wii U DELUXE");
        ProductPage productPage = productSettings.navigateToProductPage(1);
        closeCookieNotice();
        selectLanguage_RU();
        productPage.tabPanel.hover();
        Selenide.sleep(1500);
        //Проверка, что панель товарных вкладок от модуля присутствуют
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue($(".ab-spt-floating-panel").exists(), "There is no product tabs panel!");
        //Проверка, что панель товарных вкладок расположена после заголовка Н1
        softAssert.assertTrue($(".ab-spt-floating-position-after_h1").exists(),
                "Position of the product tabs panel is not after H1 header!");
        Selenide.screenshot("200 Product tabs panel - Panel after H1, UniTheme2");
        productPage.tab_Tags.scrollIntoView(true);
        Selenide.sleep(1500);
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
        softAssert.assertEquals(result, expectedWord, "There is no product short name!");
        Selenide.screenshot("210 Floating panel - Panel after H1, Top sticky panel-On");

        //Отключаем верхнюю липкую панель темы
        csCartSettings.shiftBrowserTab(0);
        csCartSettings.navigateToAddonsPage();
        csCartSettings.navigateToThemeSettings();
        if(uniThemeSettings.setting_TopStickyPanel.isSelected()){
            uniThemeSettings.setting_TopStickyPanel.click();
            csCartSettings.button_Save.click(); }
        csCartSettings.shiftBrowserTab(1);
        Selenide.refresh();
        Selenide.sleep(1500);
        softAssert.assertAll();
        Selenide.screenshot("230 Floating panel - Panel after H1, Top sticky panel-Off");
    }
}