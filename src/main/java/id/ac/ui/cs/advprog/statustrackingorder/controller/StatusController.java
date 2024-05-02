package id.ac.ui.cs.advprog.statustrackingorder.controller;

import id.ac.ui.cs.advprog.statustrackingorder.model.Status;
import id.ac.ui.cs.advprog.statustrackingorder.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStatusById(@PathVariable("id") Long id) {
        try {
            Status status = statusService.getStatusById(id);
            return ResponseEntity.ok(status);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such status with id: " + id);
        }
    }


    @GetMapping("/orderid/{id}")
    public ResponseEntity<Object> getStatusByOderId(@PathVariable("id") Long orderId) {
        try {
            Status status = statusService.getStatusByOrderId(orderId);
            return ResponseEntity.ok(status);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such status with id: " + orderId);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createStatus(@RequestBody Status status) {
        statusService.createStatus(status);
        return ResponseEntity.ok("Status created successfully");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable("id") Long id, @RequestBody Status updateStatus) {
        Status existingStatus = statusService.getStatusById(id);
        if (existingStatus != null) {
            existingStatus.setOrderStatus(updateStatus.getOrderStatus());
            statusService.updateStatus(existingStatus);
            return ResponseEntity.ok("Status updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
