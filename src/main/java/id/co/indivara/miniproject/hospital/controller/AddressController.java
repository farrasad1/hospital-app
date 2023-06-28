package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Address;
import id.co.indivara.miniproject.hospital.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class AddressController {
    @Autowired
    private AddressService addressService;

    //SAVE ADDRESS TO DATABASE
    @PostMapping("/addresses")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        return new ResponseEntity<>(addressService.saveData(address), HttpStatus.CREATED);
    }

    //FIND ALL ADDRESS
    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> fetchAddressList() {
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);
    }

    //FIND ADDRESS BY ID
    @GetMapping("/addresses/{id}")
    public ResponseEntity<Address> findById (@PathVariable("id") Long addressId) {
        return new ResponseEntity<>(addressService.findById(addressId), HttpStatus.OK);
    }

    //UPDATE ADDRESS
    @PutMapping ("/addresses/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable("id") Long addressId){
        return new ResponseEntity<>(addressService.updateData(addressId, address), HttpStatus.OK);
    }

    //DELETE ADDRESS
    @DeleteMapping ("/addresses/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") Long addressId){
        addressService.deletebyId(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
