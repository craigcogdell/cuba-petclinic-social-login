package com.haulmont.sample.petclinic.web;

import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.mainwindow.UserActionsButton;
import com.haulmont.cuba.gui.screen.Install;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.web.Connection;
import com.haulmont.cuba.web.app.main.MainScreen;

import javax.inject.Inject;

@UiController("main")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen {

    @Inject
    private Screens screens;
    @Inject
    private Connection connection;

    @Install(to = "userActionsButton", subject = "loginHandler")
    private void loginHandler(UserActionsButton.LoginHandlerContext ctx) {
        showLoginDialog();
    }

    @Subscribe
    private void onAfterShowExt(AfterShowEvent event) {
        if (!connection.isAuthenticated()) {
            showLoginDialog();
        }
    }

    private void showLoginDialog() {
        screens.create(LoginDialog.class, OpenMode.DIALOG)
                .show();
    }
}