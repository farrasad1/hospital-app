package id.co.indivara.miniproject.hospital.controller;

import id.co.indivara.miniproject.hospital.entity.Address;
import id.co.indivara.miniproject.hospital.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/addresses")
    Address saveAddress(@RequestBody Address address){
        return addressService.saveData(address);
    }

    @GetMapping("/addresses")
    List<Address> fetchAddressList() {
        return addressService.findAll();
    }

    @GetMapping("/addresses/{id}")
    Address findById (@PathVariable("id") Long addressId) {
        return addressService.findById(addressId);
    }

    @PatchMapping ("/addresses/{id}")
    Address updateAddress(@RequestBody Address address, @PathVariable("id") Long addressId){
        return addressService.updateData(addressId, address);
    }

    @DeleteMapping ("/addresses/{id}")
    public String deleteAddress(@PathVariable("id") Long addressId){
        addressService.deletebyId(addressId);
        return "Delete sukses";
    }
}
