package by.robots.controller;

import by.robots.model.robot.Robot;
import by.robots.model.task.Task;
import by.robots.model.viewObjects.EventLogViewObject;
import by.robots.service.EventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping(value = "/log")
public class EventLogController {
    @Autowired
    private EventLogService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<ConcurrentLinkedQueue<EventLogViewObject>> listAllEvents() {
        ConcurrentLinkedQueue<EventLogViewObject> events = service.findAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //-------------------Create a Task--------------------------------------------------------

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Void> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
        System.out.println(task.getAssignedRobotId());
        boolean isAdded = service.addTask(task);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/add/{id}").buildAndExpand(task.getTaskId()).toUri());
        return isAdded ? new ResponseEntity<Void>(headers, HttpStatus.CREATED) : new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    //-------------------List robot--------------------------------------------------------
    @RequestMapping(value = "/robots", method = RequestMethod.GET)
    public ResponseEntity<ConcurrentLinkedQueue<Robot>> listAllRobots() {
        ConcurrentLinkedQueue<Robot> robots = service.findAllRobots();
        if (robots.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(robots, HttpStatus.OK);
    }

}
