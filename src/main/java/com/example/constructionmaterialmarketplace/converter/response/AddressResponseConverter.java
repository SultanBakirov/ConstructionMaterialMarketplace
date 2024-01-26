package com.example.constructionmaterialmarketplace.converter.response;

import com.example.constructionmaterialmarketplace.dto.response.AddressResponse;
import com.example.constructionmaterialmarketplace.entity.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressResponseConverter {

    public AddressResponse viewAddress(Address address) {
        if (address == null) {
            return null;
        }
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setUserId(address.getUserId());
        addressResponse.setStreet(address.getStreet());
        addressResponse.setCity(address.getCity());
        addressResponse.setState(address.getState());
        addressResponse.setCountry(address.getCountry());
        addressResponse.setZipCode(address.getZipCode());
        return addressResponse;
    }

    public List<AddressResponse> getAllAddresses(List<Address> addresses) {
        List<AddressResponse> addressResponses = new ArrayList<>();
        for (Address address: addresses) {
            addressResponses.add(viewAddress(address));
        }
        return addressResponses;
    }
}
