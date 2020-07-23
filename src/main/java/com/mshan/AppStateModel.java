package com.mshan;
import org.apache.helix.NotificationContext;
import org.apache.helix.model.Message;
import org.apache.helix.participant.statemachine.StateModel;
import org.apache.helix.participant.statemachine.StateModelInfo;
import org.apache.helix.participant.statemachine.Transition;


/**
 *
 */
@StateModelInfo(initialState = "OFF", states = {
        "OFF", "ON"
})
public class AppStateModel extends StateModel{

    private final String name;

    /**
     * App State Model construction
     * @param name
     */
    public AppStateModel(String name){
        this.name=name;
    }

    @Transition(from = "OFF", to = "ON")
    public void on(Message m, NotificationContext context) {
        System.out.println(context.getManager().getInstanceName() + " acquired :" + name);
    }

    @Transition(from = "ON", to = "OFF")
    public void off(Message m, NotificationContext context) {
        System.out.println(context.getManager().getInstanceName() + " releasing :" + name);
    }


}
