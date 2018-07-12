package auto.Pages;

import auto.Config.Config;


/**
 * Created by Denis on 06.01.2018.
 */
public class Page {

    public static Config conf = Config.getInstance();

    String siteURL = conf.getString("siteURL");

}
