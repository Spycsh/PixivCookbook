package PixivCookbook.Controller;

import PixivCookbook.View.ForbiddenEditWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Pair;

public class EditForbiddenEvent
{
    public void addForbiddenEvent(ForbiddenEditWindow forW, Stage forbiddenStage)
    {
        forW.home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                forbiddenStage.close();
            }
        });
        for(int i=0;i<forW.data.size();i++)
        {
            if(forW.deleteForbidden.size()==0) break;
            if(i>=forW.editForbidden.size()) break;
            int temp=i;
            forW.editForbidden.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(forW.mark.get(temp).equals(1)&&temp<forW.forbidenText1.size())
                    {
                        //System.out.println(forW.forbidenText1.size());
                        String s1 ="";
                        String s2 ="";
                        int j=temp;
                        //for(int j=0;j<forW.forbidenText1.size();j++)
                        {
                            s1=forW.forbidenText1.get(j).getText();
                            s2=forW.forbidenText2.get(j).getText();
                            forW.data.set(j,new Pair<>(s1,s2));
                        }
                    }
                    forW.mark.set(temp,1-forW.mark.get(temp));
                    forW.refresh();
                    addForbiddenEvent(forW,forbiddenStage);
                }
            });
            forW.deleteForbidden.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    forW.data.remove(temp);
                    forW.mark.remove(temp);
                    forW.mark.add(1);
                    forW.refresh();
                    addForbiddenEvent(forW,forbiddenStage);
                }
            });
            forW.addForbidden.get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    forW.data.add(temp,new Pair<>("Deafault","Deafault"));
                    forW.mark.add(temp,1);
                    forW.refresh();
                    addForbiddenEvent(forW,forbiddenStage);
                }
            });
        }
    }
}
