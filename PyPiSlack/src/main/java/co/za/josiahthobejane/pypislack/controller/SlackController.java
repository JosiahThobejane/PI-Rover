package co.za.josiahthobejane.pypislack.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.za.josiahthobejane.pypislack.controller.Directions;

@RestController
public class SlackController {

    Directions directions = new Directions();

    /**
     * 
     * @return
     */
    @RequestMapping(value = "/rover", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String onReceiveRoverCommand(@RequestParam("text") String movementData,
            @RequestParam("user_name") String userName) {

        if (movementData.isEmpty()) {
            return userName + " yikes... type propely";
        } else if (!movementData.isEmpty()) {
            String[] words = movementData.split(" ");

            moveRover(words[0], Integer.parseInt(words[1]));
        }
        return "";

    }

    /**
     * 
     * @param direction
     * @param distance
     */
    public String moveRover(String direction, int distance) {
        switch (direction.toLowerCase()) {
        case "left":
            Directions.moveLeft();
            stopMovementAfter(distance);
            Directions.brake();
            break;
        case "right":
            Directions.moveRight();
            stopMovementAfter(distance);
            Directions.brake();
            break;
        case "forward":
            Directions.moveForward();
            stopMovementAfter(distance);
            Directions.brake();
            break;
        case "reverse":
            Directions.moveBackward();
            stopMovementAfter(distance);
            Directions.brake();
            break;

        }
        return direction;
    }

    /**
     * 
     * @param distance
     * @return
     */

    public int calculateDistance(int distance) {
        double calculatedDistance = (distance / 28.571) * 1000; // 28... is the estimated speed of the motors on full
                                                                // throttle
        Integer timeLapse = Integer.valueOf((int) Math.round(calculatedDistance)); // Time = Distance / Velocity
        return timeLapse;
    }

    /**
     * 
     * @param actualDistance
     * @throws InterruptedException
     */
    public void stopMovementAfter(int actualDistance) {
        try {
            wait(actualDistance);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}