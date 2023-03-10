import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.$;

public class TestCaseOne extends TestRunner
{
    @Test
    public void productPage() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToProductListPage();
        productSettings.goToEditingProductPage("Wii U DELUXE");
        ProductPage productPage = productSettings.navigateToProductPage(1);
        selectLanguage_RU();
        productPage.tabPanel.scrollIntoView(true);
        //Проверка, что панель товарных вкладок от модуля присутствуют
        Assert.assertTrue($(".ab-spt-floating-panel").exists(), "There is no product tabs panel!");
        //Проверка, что панель товарных вкладок расположена перед вкладками товара
        Assert.assertTrue($(".ab-spt-floating-position-before_tabs").exists(),
                "Position of the product tabs panel is not before tabs!");
        Selenide.screenshot("100 Product tabs panel - Panel before product tabs, Top sticky panel-On");
        productPage.tab_Tags.scrollIntoView(true);
        Selenide.sleep(1000);
        //Проверяем, что краткое название товара присутствует
        String expectedText = "ShortName";
        String actualText = $(".tab-list-title").getText();
        Assert.assertTrue(actualText.contains(expectedText),
                "There is no product short name!");
        Selenide.screenshot("110 Floating panel - Panel before product tabs, Top sticky panel-On, Short name");
    }
}