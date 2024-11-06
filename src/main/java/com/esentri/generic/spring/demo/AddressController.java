package com.esentri.generic.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id) {
        Optional<Address> addressOptional = addressService.findById(id);
        return addressOptional.map(address -> new ResponseEntity<>(address, HttpStatus.OK))
                              .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address savedAddress = addressService.save(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        Optional<Address> addressOptional = addressService.findById(id);
        if (addressOptional.isPresent()) {
            Address currentAddress = addressOptional.get();

            // Create a new Address instance with updated properties
            Address updatedAddress = Address.builder()
                    .id(currentAddress.getId())
                    .street(address.getStreet() != null ? address.getStreet() : currentAddress.getStreet())
                    .houseNumber(address.getHouseNumber() != null ? address.getHouseNumber() : currentAddress.getHouseNumber())
                    .postalCode(address.getPostalCode() != null ? address.getPostalCode() : currentAddress.getPostalCode())
                    .city(address.getCity() != null ? address.getCity() : currentAddress.getCity())
                    .build();

            // Save the updated address
            Address savedAddress = addressService.save(updatedAddress);

            return new ResponseEntity<>(savedAddress, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        if (addressService.findById(id).isPresent()) {
            addressService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}