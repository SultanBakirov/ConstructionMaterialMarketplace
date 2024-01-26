package com.example.constructionmaterialmarketplace.converter.request;

import com.example.constructionmaterialmarketplace.dto.request.AddressRequest;
import com.example.constructionmaterialmarketplace.dto.request.CategoryRequest;
import com.example.constructionmaterialmarketplace.entity.Address;
import com.example.constructionmaterialmarketplace.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class AddressRequestConverter {

    public Address create(AddressRequest addressRequest) {
        if (addressRequest == null) {
            return null;
        }
        Address address = new Address();
        address.setStreet(addressRequest.getStreet());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setZipCode(addressRequest.getZipCode());
        return address;
    }
}
