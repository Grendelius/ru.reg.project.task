package ru.reg.project;

public enum Xpath {

    SEARCH_PAGE_SETTINGS_LINK("//span[@text']"),
    MARKET_ALL_CTGS_BTN("/html/body/div[1]/div[2]/div[1]/div/div[1]/div/button");




    private String domString;

    Xpath(String s) {
        this.domString = s;
    }

    public String getDomString() {
        return domString;
    }
}
