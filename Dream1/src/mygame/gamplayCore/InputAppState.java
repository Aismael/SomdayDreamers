/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.gamplayCore;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;

/**
 *
 * @author Aismael
 */
public class InputAppState extends AbstractAppState implements ActionListener, AnalogListener{
    InputManager inputManager;
    GameCharacterControl character;
    public InputAppState(InputManager inputManager,GameCharacterControl gameCharacterControl){
        super();
        this.inputManager=inputManager;
        this.character=gameCharacterControl;
        addInputMappings();
    }
    public enum InputMapping{
        RotateLeft, RotateRight, LookUp, LookDown, StrafeLeft, StrafeRight, MoveForward, MoveBackward, Jump, Duck;
    }
    
    private void addInputMappings(){
        inputManager.addMapping(InputMapping.RotateLeft.name(), new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping(InputMapping.RotateRight.name(), new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping(InputMapping.LookUp.name(), new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping(InputMapping.LookDown.name(), new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping(InputMapping.StrafeLeft.name(), new KeyTrigger(KeyInput.KEY_A),new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping(InputMapping.StrafeRight.name(), new KeyTrigger(KeyInput.KEY_D),new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping(InputMapping.MoveForward.name(), new KeyTrigger(KeyInput.KEY_W),new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping(InputMapping.MoveBackward.name(), new KeyTrigger(KeyInput.KEY_S),new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping(InputMapping.Jump.name(), new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping(InputMapping.Duck.name(), new KeyTrigger(KeyInput.KEY_C));
        for(InputMapping i: InputMapping.values()){
            inputManager.addListener(this, i.name());
        }
    }
    
    @Override
    public void initialize(AppStateManager stateManager, Application app){
        super.initialize(stateManager, app);
        this.inputManager=app.getInputManager();
        addInputMappings();
    }
    
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        if(character!= null){
            character.onAction(name, isPressed, tpf);
        }
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if(character!= null){
            character.onAnalog(name, value, tpf);
        }
    }
    @Override
    public void cleanup(){
        super.cleanup();
        for(InputMapping i: InputMapping.values()){
            if(inputManager.hasMapping(i.name())){
            inputManager.deleteMapping(i.name());
            }
            inputManager.removeListener(this);
        }
    }
}
