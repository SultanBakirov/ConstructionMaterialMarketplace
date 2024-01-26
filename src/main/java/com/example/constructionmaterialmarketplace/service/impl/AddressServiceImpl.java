package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.request.AddressRequestConverter;
import com.example.constructionmaterialmarketplace.converter.response.AddressResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.AddressRequest;
import com.example.constructionmaterialmarketplace.dto.response.AddressResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.Address;
import com.example.constructionmaterialmarketplace.entity.User;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.AddressRepository;
import com.example.constructionmaterialmarketplace.repository.UserRepository;
import com.example.constructionmaterialmarketplace.service.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;

    private final AddressRepository addressRepository;

    private final AddressResponseConverter addressResponseConverter;

    private final AddressRequestConverter addressRequestConverter;

    @Override
    public AddressResponse saveAddress(Long userId, AddressRequest addressRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with id: " + userId)));
        Address address = addressRequestConverter.create(addressRequest);
        address.setUserId(user);
        addressRepository.save(address);
        return addressResponseConverter.viewAddress(address);
    }

    @Override
    public AddressResponse getAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException(String.format("Address with id = %d not found", addressId)));
        return addressResponseConverter.viewAddress(address);
    }

    @Override
    public List<AddressResponse> getAllAddressesByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        List<Address> addresses = addressRepository.findAllByUserId(userId);
        return addressResponseConverter.getAllAddresses(addresses);
    }

    @Override
    public AddressResponse updateAddress(Long addressId, AddressRequest addressRequest) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setZipCode(addressRequest.getZipCode());
        addressRepository.save(address);
        return addressResponseConverter.viewAddress(address);
    }

    @Override
    public SimpleResponse deleteAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new EntityNotFoundException("Address not found with id: " + addressId));
        addressRepository.delete(address);
        return new SimpleResponse("The Address removed!");
    }
}
