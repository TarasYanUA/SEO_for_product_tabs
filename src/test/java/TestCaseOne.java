import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import org.testng.annotations.Test;

public class TestCaseOne {
    @Test
    public void productPage() {
        CsCartSettings csCartSettings = new CsCartSettings();
        ProductSettings productSettings = csCartSettings.navigateToProductListPage();
        productSettings.goToEditingProductPage("Wii U DELUXE");
    }
}
