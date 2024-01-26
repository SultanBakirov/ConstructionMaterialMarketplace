package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.AddressRequest;
import com.example.constructionmaterialmarketplace.dto.response.AddressResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{userId}/save")
    public AddressResponse saveAddress(@PathVariable Long userId, @RequestBody AddressRequest addressRequest) {
        return addressService.saveAddress(userId, addressRequest);
    }

    @GetMapping("/{addressId}")
    public AddressResponse getAddressById(@PathVariable Long addressId) {
        return addressService.getAddressById(addressId);
    }

    @GetMapping("/user/{userId}")
    public List<AddressResponse> getAllAddressesByUserId(@PathVariable Long userId) {
        return addressService.getAllAddressesByUserId(userId);
    }

    @PutMapping("/{addressId}")
    public AddressResponse updateAddressData(@PathVariable Long addressId, @RequestBody AddressRequest addressRequest) {
        return addressService.updateAddress(addressId, addressRequest);
    }

    @DeleteMapping("/{addressId}")
    public SimpleResponse deleteAddressById(@PathVariable Long addressId) {
        return addressService.deleteAddressById(addressId);
    }
}
