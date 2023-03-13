import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.UniThemeSettings;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.$;

/*
- Настройки темы Юни2:
    * Верхняя липкая панель -- Включаем и Отключаем
- Позиция навигационной панели вкладок -- Перед вкладками товара
*/

public class TestCase1_BeforeProductTabs extends TestRunner
{
    @Test
    public void checkProductPage_TestCaseOne() {
        CsCartSettings csCartSettings = new CsCartSettings();
        //Включаем верхнюю липкую панель темы
        csCartSettings.navigateToAddonsPage();
        UniThemeSettings uniThemeSettings = csCartSettings.navigateToThemeSettings();
        if(!uniThemeSettings.setting_TopStickyPanel.isSelected()){
            uniThemeSettings.setting_TopStickyPanel.click();
            csCartSettings.button_Save.click(); }

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
        Selenide.screenshot("100 Product tabs panel - Panel before product tabs, Top sticky panel-On");
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
        Selenide.screenshot("110 Floating panel - Panel before product tabs, Top sticky panel-On, Short name");

        //Отключаем верхнюю липкую панель темы
        csCartSettings.shiftBrowserTab(0);
        csCartSettings.navigateToAddonsPage();
        csCartSettings.navigateToThemeSettings();
        if(uniThemeSettings.setting_TopStickyPanel.isSelected()){
            uniThemeSettings.setting_TopStickyPanel.click();
            csCartSettings.button_Save.click(); }
        csCartSettings.shiftBrowserTab(1);
        Selenide.refresh();
        productPage.tabPanel.scrollTo();
        Selenide.sleep(1000);
        Selenide.screenshot("120 Product tabs panel - Panel before product tabs, Top sticky panel-Off");
        productPage.tab_Tags.scrollIntoView(true);
        Selenide.sleep(1000);
        Selenide.screenshot("130 Floating panel - Panel before product tabs, Top sticky panel-Off, Short name");
    }
}