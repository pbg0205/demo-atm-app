package com.cooper.demoatmapp.user.converter;

import com.cooper.demoatmapp.common.utils.Base64Utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserAttributeConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return Base64Utils.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return Base64Utils.decode(dbData);
    }

}
