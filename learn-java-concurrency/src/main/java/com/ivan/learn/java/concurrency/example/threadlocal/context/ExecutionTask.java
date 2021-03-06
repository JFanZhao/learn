package com.ivan.learn.java.concurrency.example.threadlocal.context;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 22:33
 **/
public class ExecutionTask implements Runnable{
    private QueryFromDBAction queryAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {

        queryAction.execute();
        System.out.println("The name query successful");
        httpAction.execute();
        System.out.println("The card id query successful");

        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The Name is " + context.getName() + " and CardId " + context.getCardId());
    }
}
