package com.epam.murodil.controller.navigation;

import com.epam.murodil.constants.ControllerConstants;

public class Router {
    private String page = ControllerConstants.DEFAULT_PAGE;
    private PageChangeType type = PageChangeType.FORWARD;

    public enum PageChangeType {
        FORWARD, REDIRECT
    }

    public Router() {
    }

    public Router(String page) {
        this.page = (page != null ? page : ControllerConstants.DEFAULT_PAGE);
    }

    public Router(String page, PageChangeType type) {
        this.page = (page != null ? page : ControllerConstants.DEFAULT_PAGE);
        this.type = (type != null ? type : PageChangeType.FORWARD);
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = (page != null ? page : ControllerConstants.DEFAULT_PAGE);
    }

    public void setRedirect() {
        this.type = PageChangeType.REDIRECT;
    }

    public void setForward() {
        this.type = PageChangeType.FORWARD;
    }

    public PageChangeType getType() {
        return type;
    }
}
