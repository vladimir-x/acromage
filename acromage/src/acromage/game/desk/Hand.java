/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game.desk;

import acromage.game.AppImpl;
import acromage.game.Arcomage;
import acromage.game.interfaсe.Actionable;
import acromage.game.slot.ActiveSlot;
import acromage.game.slot.FlySlot;
import acromage.game.slot.HandSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author elduderino
 */
public class Hand extends Deskzone implements Actionable {

    ArrayList<HandSlot> slots;
    FlySlot selectedSlot;

    ActiveSlot activeSlot;

    public Hand(int zone, ActiveSlot activeSlot) {
        super(zone);
        this.activeSlot = activeSlot;
        slots = new ArrayList<HandSlot>();
        for (int i = 0; i < AppImpl.settings.cardCount; ++i) {
            slots.add(new HandSlot(this, i));
        }
    }

    public int getCount() {
        return slots.size();
    }

    public void selectSlot() {

        //selectedSlot = new FlySlot();
    }

    @Override
    public void update() {
        super.update();

        for (int i = 0; i < slots.size(); ++i) {
            slots.get(i).setPos(i);
            slots.get(i).update();
        }
    }

    @Override
    public void render(ShapeRenderer renderer, SpriteBatch spriteBatch) {
        super.render(renderer, spriteBatch);

        for (HandSlot slot : slots) {
            slot.render(renderer, spriteBatch);
        }
        if (selectedSlot != null) {
            selectedSlot.render(renderer, spriteBatch);
        }
    }

    @Override
    public void action(float delta) {
        if (selectedSlot != null) {
            selectedSlot.action(delta);
            if (selectedSlot.card == null) {
                selectedSlot = null;
            }
        }
    }

    public boolean promptToSelect(float propX, float propY) {
        for (HandSlot handSlot : slots) {
            if (handSlot.contains(propX, propY)) {
                selectedSlot = new FlySlot(handSlot, activeSlot);
                slots.remove(handSlot);
                update();
                return true;
            }
        }
        return false;
    }
}