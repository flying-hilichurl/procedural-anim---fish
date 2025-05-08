package hilichurl.proceduralanim.controller;

import javafx.fxml.FXML;

public class StartController {
    private Runnable clickHandler;

    public void setClickHandler(Runnable handler){
        this.clickHandler=handler;
    }

    @FXML
    protected void onStartButtonClick() {
        if(clickHandler==null){
            System.out.println("wrong:clickHandler is null!");
            return ;
        }

        clickHandler.run();
    }
}