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
    public String onReceiveRoverCommand(@RequestParam("text") String slackResponse,
            @RequestParam("user_name") String userName) {

        if (slackResponse.isEmpty()) {
            return userName + " Your command is missing something, please try again. :neutral_face:";
        } else if (!slackResponse.isEmpty()) {
            String[] movementData = slackResponse.split(" ");
            moveRover(movementData[0], Integer.parseInt(movementData[1]));
            return userName + " moving the Rovr " + movementData[0] + " :racing_car:";
        }
        return "something must have went wrong :thinking_face: ";

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
     * Calculates the time the motors should run for
     * @param distance
     * @return
     */

    public int calculateDistance(int distance) {
        double calculatedDistance = (distance / 28.571) * 1000; // 28... is the estimated speed of the motors on full throttle
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
            Thread.sleep(calculateDistance(actualDistance));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}