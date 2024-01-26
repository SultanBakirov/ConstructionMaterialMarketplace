package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.AddressRequest;
import com.example.constructionmaterialmarketplace.dto.response.AddressResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    AddressResponse saveAddress(Long userId, AddressRequest addressRequest);

    AddressResponse getAddressById(Long addressId);

    List<AddressResponse> getAllAddressesByUserId(Long userId);

    AddressResponse updateAddress(Long addressId, AddressRequest addressRequest);

    SimpleResponse deleteAddressById(Long addressId);
}
