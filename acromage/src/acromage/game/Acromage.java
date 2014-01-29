/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acromage.game;

import acromage.game.data.Deskzone;
import acromage.game.data.Hand;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author elduderino
 */
public class Acromage {

    private Settings settings;

    Deskzone left, right, center;
    Hand top, bottom;
    
    Deskzone zones[];
    Hand hands[];

    public Acromage(Settings settings) {
        this.settings = settings;

        center = new Deskzone(settings, Deskzone.CENTER);
        right = new Deskzone(settings, Deskzone.EAST);
        left = new Deskzone(settings, Deskzone.WEST);
        top = new Hand(settings, Deskzone.NORTH);
        bottom = new Hand(settings, Deskzone.SOUTH);

        center.setColor(Color.DARK_GRAY);
        right.setColor(Color.LIGHT_GRAY);
        left.setColor(Color.LIGHT_GRAY);
        top.setColor(Color.LIGHT_GRAY);
        bottom.setColor(Color.LIGHT_GRAY);

        zones = new Deskzone[]{center, right, left, bottom, top};
        hands = new Hand[]{top,bottom};

    }

    public void render(ShapeRenderer renderer) {

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Deskzone zone : zones) {
            renderer.setColor(zone.getColor());
            renderer.rect(
                    zone.getRectangle().x,
                    zone.getRectangle().y,
                    zone.getRectangle().width,
                    zone.getRectangle().height
            );
        }

        for (Hand hand : hands) {
            for (Rectangle rect : hand.getRects()) {
                renderer.setColor(Color.GREEN);
                renderer.rect(
                        rect.x,
                        rect.y,
                        rect.width,
                        rect.height
                );
            }
        }

        renderer.end();
    }

}
