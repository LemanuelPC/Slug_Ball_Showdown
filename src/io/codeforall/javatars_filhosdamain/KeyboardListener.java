package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardListener implements KeyboardHandler {

    private Interactable entity;

    public KeyboardListener(){
        startListening();
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        entity.setKey(keyboardEvent.getKey(), true);
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        entity.setKey(keyboardEvent.getKey(), false);
    }


    public void setEntity(Interactable entity) {
        this.entity = entity;
    }

    private void startListening() {
        Keyboard keyboard = new Keyboard(this);

        //PRESSED IMPLEMENTATION
        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_UP);

        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_DOWN);

        keyboard.addEventListener(down);

        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);

        keyboard.addEventListener(left);

        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(right);

        KeyboardEvent space = new KeyboardEvent();
        space.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        space.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(space);

        KeyboardEvent w = new KeyboardEvent();
        w.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        w.setKey(KeyboardEvent.KEY_W);

        keyboard.addEventListener(w);

        KeyboardEvent s = new KeyboardEvent();
        s.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        s.setKey(KeyboardEvent.KEY_S);

        keyboard.addEventListener(s);

        KeyboardEvent a = new KeyboardEvent();
        a.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        a.setKey(KeyboardEvent.KEY_A);

        keyboard.addEventListener(a);

        KeyboardEvent d = new KeyboardEvent();
        d.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        d.setKey(KeyboardEvent.KEY_D);

        keyboard.addEventListener(d);

        KeyboardEvent p = new KeyboardEvent();
        p.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        p.setKey(KeyboardEvent.KEY_P);

        keyboard.addEventListener(p);

        KeyboardEvent esc = new KeyboardEvent();
        esc.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        esc.setKey(KeyboardEvent.KEY_ESC);

        keyboard.addEventListener(esc);

        KeyboardEvent t = new KeyboardEvent();
        t.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        t.setKey(KeyboardEvent.KEY_T);

        keyboard.addEventListener(t);


        //RELEASE IMPLEMENTATION

        /*KeyboardEvent upRelease = new KeyboardEvent();
        upRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        upRelease.setKey(KeyboardEvent.KEY_UP);

        keyboard.addEventListener(upRelease);

        KeyboardEvent downRelease = new KeyboardEvent();
        downRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        downRelease.setKey(KeyboardEvent.KEY_DOWN);

        keyboard.addEventListener(downRelease);

        KeyboardEvent leftRelease = new KeyboardEvent();
        leftRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        leftRelease.setKey(KeyboardEvent.KEY_LEFT);

        keyboard.addEventListener(leftRelease);

        KeyboardEvent rightRelease = new KeyboardEvent();
        rightRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        rightRelease.setKey(KeyboardEvent.KEY_RIGHT);

        keyboard.addEventListener(rightRelease);

        KeyboardEvent spaceRelease = new KeyboardEvent();
        spaceRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        spaceRelease.setKey(KeyboardEvent.KEY_SPACE);

        keyboard.addEventListener(spaceRelease);

        KeyboardEvent wRelease = new KeyboardEvent();
        wRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        wRelease.setKey(KeyboardEvent.KEY_W);

        keyboard.addEventListener(wRelease);

        KeyboardEvent sRelease = new KeyboardEvent();
        sRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        sRelease.setKey(KeyboardEvent.KEY_S);

        keyboard.addEventListener(sRelease);

        KeyboardEvent pRelease = new KeyboardEvent();
        pRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        pRelease.setKey(KeyboardEvent.KEY_P);

        keyboard.addEventListener(pRelease);

        KeyboardEvent escRelease = new KeyboardEvent();
        escRelease.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        escRelease.setKey(KeyboardEvent.KEY_ESC);

        keyboard.addEventListener(escRelease);*/

    }

}
