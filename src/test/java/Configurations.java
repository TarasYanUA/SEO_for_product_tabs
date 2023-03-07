import adminPanel.CsCartSettings;
import org.testng.annotations.Test;

public class Configurations extends TestRunner{
    @Test
    public void setConfigurations() {
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettings();
        if(csCartSettings.setting_DisplayProductDetailsInTabs.isSelected()) {
            csCartSettings.setting_DisplayProductDetailsInTabs.click();
        }
        if(!csCartSettings.setting_QuickView.isSelected()){
            csCartSettings.setting_QuickView.click();
        }
        csCartSettings.button_Save.click();
        csCartSettings.navigateToProductTabs();
    }
}
